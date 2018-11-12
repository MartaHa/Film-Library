package pl.marta.mongo;

import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws InputMismatchException, UnknownHostException {


        Scanner scanner = new Scanner(System.in);
        final Dao dao = new Dao();


        /* Making the program work */
        while (true) {
        System.out.println("Hello. What do you want to do:" +
                "\n Add a film : ADD" +
                "\n Show all films : ALL" +
                "\n Show a film : SHOW" +///
                "\n update a film : UPDATE" +
                "\n delete a film : DELETE" +
                "\n delete all database : DELETEALL" +
                "\n quit : Q");


            String answer = "";
            try {
                answer = scanner.next();
            } catch (InputMismatchException e) {
                System.out.println("Not a proper format. Try again");
            }
            /* ADDING A FILM TO DATABASE */

            if (answer.equals("ADD")) {

                String title = "";
                String yearOfRelease = "";
                String director = "";
                String category = "";


                /* getting data from user and adding the film to MongoDB */
                try {
                    System.out.println("Enter the film title:");
                    title = scanner.next();
                    System.out.println("Enter the year of release:");
                    yearOfRelease = scanner.next();
                    System.out.println("Enter the director:");
                    director = scanner.next();
                    System.out.println("Enter the category");
                    category = scanner.next();

                } catch (InputMismatchException e) {
                    System.out.println("Wrong value, try again");
                }
                /* adding a  film to database */

                LocalDate created = LocalDate.now();
                System.out.println(dao.addFilm(title, yearOfRelease, director, category, created));
            }

            /*SHOW ALL LIST OF FILMS */

            else if (answer.equals("ALL")) {
                List filmList = dao.showAll();
                filmList.forEach(System.out::println);



                /* SHOW ONE FILM  */

            } else if (answer.equals("SHOW")) {

                // getting the name of the film to show
                String title = "";
                System.out.println("Please enetr the title of the film you want to see");
                try {
                    title = scanner.next();
                } catch (InputMismatchException e) {
                    System.out.println("That is not a proper value try again");
                }


                /* showing one film */

                List showFilm = dao.showFilm(title);
                showFilm.forEach(System.out::println);


                /* UPDATE METHOD */


            } else if (answer.equals("UPDATE")) {

                // getting the name of the film to update
                String title = "";
                System.out.println("Please enetr the title of the film you want to update");
                try {
                    title = scanner.next();
                } catch (InputMismatchException e) {
                    System.out.println("That is not a proper format. Try again!");
                }

                /* updating */

                System.out.println("Enter the data for updating");

                while (scanner.next() != "q") {
                    String toChange = "";
                    String changed = "";

                    System.out.println("What do you want to change ? // for exiting type 'q':");
                    try {
                        toChange = scanner.next();
                    } catch (InputMismatchException e) {
                        System.out.println("That is not a proper format. Try again!");
                    }
                    System.out.println("What do you want to type instead:");
                    try {
                        changed = scanner.next();
                    } catch (InputMismatchException e) {
                        System.out.println("That is not a proper format. Try again!");

                    }
                    dao.updateFilm(title, toChange, changed);

                }



                /* DELETE METHOD */

            } else if (answer.equals("DELETE")) {

                String title = "";
                System.out.println("Enter the title of the film to delete:");
                try {
                    title = scanner.next();
                    dao.delete(title);
                } catch (InputMismatchException e) {
                    System.out.println("That is not a proper value Try again");

                }
            } else if (answer.equals("DELETEALL")) {
                dao.deleteAll();

            } else if (answer.equals("Q")) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("It's not a proper command try again");

            }


            scanner.close();
        }
    }
}

