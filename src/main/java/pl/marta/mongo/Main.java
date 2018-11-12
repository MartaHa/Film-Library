package pl.marta.mongo;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws InputMismatchException {


        Scanner scanner = new Scanner(System.in);
        final Dao dao = new Dao();


        /* Making the program work */

        System.out.println("Hello. What do you want to do:" +
                "\n Add a film : ADD" +
                "\n Show all films : ALL" +
                "\n Show a film : SHOW" +///
                "\n update a film : UPDATE" +
                "\n delete a film : DELETE" +
                "\n quit : Q");

        while (true) {
            String answer = scanner.next();
            if (answer.equals("ADD")) {

                /* getting data from user and adding the film to MongoDB */

                System.out.println("Enter the film title:");
                String title = scanner.next();
                System.out.println("Enter the year of release:");
                String yearOfRelease = scanner.next();
                System.out.println("Enter the director:");
                String director = scanner.next();
                System.out.println("Enter the category");
                String category = scanner.next();
                LocalDate created = LocalDate.now();

                /* adding a  film to database */

                System.out.println(dao.addFilm(title, yearOfRelease, director, category, created));
            }

            /*show all list of films */

            else if (answer.equals("ALL")) {
                List filmList = dao.showAll();
                filmList.forEach(System.out::println);

            } else if (answer.equals("SHOW")) {

                // getting the name of the film to show

                System.out.println("Please enetr the title of the film you want to see");
                String title = scanner.next();


                /* showing one film */

                List showFilm = dao.showFilm(title);
                showFilm.forEach(System.out::println);


            } else if (answer.equals("UPDATE")) {

                // getting the name of the film to update

                System.out.println("Please enetr the title of the film you want to update");
                String title = scanner.next();


                /* updating */

                System.out.println("Enter the data for updating");

                while (scanner.next() != "q") {
                    System.out.println("What do you want to change ? // for exiting type 'q':");
                    String toChange = scanner.next();
                    System.out.println("What do you want to type instead:");
                    String changed = scanner.next();
                    dao.updateFilm(title, toChange, changed);

                }

                /* delete the film */

            } else if (answer.equals("DELETE")) {

                System.out.println("Enter the title of the film to delete:");
                String title = scanner.next();
                dao.delete(title);
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

