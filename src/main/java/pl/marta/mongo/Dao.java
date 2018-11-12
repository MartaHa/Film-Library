package pl.marta.mongo;

import com.mongodb.*;

import java.time.LocalDate;
import java.util.*;

public class Dao {

    
    /* Add film */

    public String addFilm(String title, String yearOfRelease, String director, String category, LocalDate created) {

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

        String reply = "Your film has been added";
        return reply;
    }


    //viewing all films

    public List showAll() {

        /* Creating a connection with Mongo */
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        /* fetching/creating a table */

        DB database = mongoClient.getDB("films");
        DBCollection table = database.getCollection("film");

        /*getting all documents from the collection*/

        List<DBObject> allFilms = table.find().toArray();
        return allFilms;
    }


    /* viewing a film */


    public List showFilm(String title) {

        /* Creating a connection with Mongo */
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        /* fetching/creating a table */

        DB database = mongoClient.getDB("films");
        DBCollection table = database.getCollection("film");

        //displaying the data

        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("title", title);

        DBCursor cursor = table.find(searchQuery);

        List filmAtributes = new ArrayList();
        while (cursor.hasNext()) {
            filmAtributes.add(cursor.next());
        }
        return filmAtributes;
    }


    public String updateFilm(String title, String toChange, String changed) {

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
        return "The film" + title + " has been updated succesfully";
    }

    public String delete(String title) {

        /* Creating a connection with Mongo */
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        /* fetching/creating a table */

        DB database = mongoClient.getDB("films");
        DBCollection table = database.getCollection("film");

        //search document where title=?

        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("title", title);

        table.remove(searchQuery);
        return "Film: " + title + " has been deleted";

    }

}