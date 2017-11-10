package com.newspaper;

import com.mongodb.MongoCredential;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;

/**
 * Hello world!
 *
 */
public class Editor
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        MongoClient client = new MongoClient("localhost", 27017);
        System.out.println("Created client " + client);

        MongoCredential credentials;

        credentials = MongoCredential.createMongoCRCredential("user", "myDb","password".toCharArray());

        MongoDatabase db = client.getDatabase("myDb");

        System.out.println(db.getName());

        System.out.println(credentials);
    }
}
