package pl.marta.mongo;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.*;

public class Dao {


    /* Add film */

    public String addFilm(String title, String yearOfRelease, String director, String category, LocalDate created) throws UnknownHostException {
        try {

            /* Creating a connection with Mongo */
            MongoClient mongoClient = new MongoClient("localhost", 27017);


            /* fetching/creating a table */

            DB database = mongoClient.getDB("films");
            DBCollection table = database.getCollection("film");

            BasicDBObject document = new BasicDBObject();
            document.put("title", title);
            document.put("yearOfRelease", yearOfRelease);
            document.put("director", director);
            document.put("category", category);
            document.put("created", created);
            table.insert(document);

        } catch (MongoException e) {
            e.printStackTrace();
        }
        String reply = "Your film has been added";
        return reply;
    }


    //viewing all films

    public List showAll() throws UnknownHostException {
        List<DBObject> allFilms = new ArrayList<>();
        try {

            /* Creating a connection with Mongo */
            MongoClient mongoClient = new MongoClient("localhost", 27017);

            /* fetching/creating a table */

            DB database = mongoClient.getDB("films");
            DBCollection table = database.getCollection("film");

            /*getting all documents from the collection*/

            allFilms = table.find().toArray();
        } catch (MongoException e) {
            e.printStackTrace();
        }
        return allFilms;
    }


    /* viewing a film */


    public List showFilm(String title) throws UnknownHostException{
        List filmAtributes = new ArrayList();
        try {
            /* Creating a connection with Mongo */
            MongoClient mongoClient = new MongoClient("localhost", 27017);

            /* fetching/creating a table */

            DB database = mongoClient.getDB("films");
            DBCollection table = database.getCollection("film");

            //displaying the data

            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("title", title);

            DBCursor cursor = table.find(searchQuery);


            while (cursor.hasNext()) {
                filmAtributes.add(cursor.next());
            }
        } catch (MongoException e) {
            e.printStackTrace();
        }
        return filmAtributes;
    }


    public String updateFilm(String title, String toChange, String changed) throws UnknownHostException {

        try {
            /* Creating a connection with Mongo */
            MongoClient mongoClient = new MongoClient("localhost", 27017);

            /* fetching/creating a table */

            DB database = mongoClient.getDB("films");
            DBCollection table = database.getCollection("film");


            // creating a document with updated data

            BasicDBObject updatedDocument = new BasicDBObject();
            updatedDocument.put(toChange, changed);


            //search document where title=?

            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("title", title);

            table.update(searchQuery, updatedDocument);
        } catch (MongoException e) {
            e.printStackTrace();
        }
        return "The film" + title + " has been updated succesfully";
    }


    public String delete(String title) throws UnknownHostException {

        try {
            /* Creating a connection with Mongo */
            MongoClient mongoClient = new MongoClient("localhost", 27017);

            /* fetching/creating a table */

            DB database = mongoClient.getDB("films");
            DBCollection table = database.getCollection("film");

            //search document where title=?

            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("title", title);

            table.remove(searchQuery);
        } catch (MongoException e) {
            e.printStackTrace();
        }
        return "Film: " + title + " has been deleted";

    }

}