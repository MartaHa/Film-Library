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
    //search document where title=?

//        BasicDBObject query = new BasicDBObject();
//        query.put("title", title);
//
//        BasicDBObject newDocument = new BasicDBObject();
//        newDocument.put("name", "mkyong-updated");
//
//        BasicDBObject updateObj = new BasicDBObject();
//        updateObj.put("$set", newDocument);
//
//        table.update(query, updateObj);


}