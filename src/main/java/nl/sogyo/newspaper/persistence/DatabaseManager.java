package nl.sogyo.newspaper.model;

import com.mongodb.MongoCredential;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;

import javax.swing.text.Document;
import java.util.Iterator;

public class Editor {

    private static MongoClient client;
    private static MongoCredential credentials;
    private static MongoDatabase database;

    public static void main( String[] args ) {
        // TODO Refactor Package Structure into nl.sogyo.Application.Persistence, nl.sogyo.Application.Domain etc
        createConnection("localhost", 27017);
        createDatabase("The_Daily_Planet");
        getDatabase("The_Daily_Planet");
        createCredentials("Clark Kent", "superman");
        createTable("Metropolis_Unsolved_Cases");
        getTable("Metropolis_Unsolved_Cases");
        getTables();
        deleteTable("Metropolis_Unsolved_Cases");
        getTables();
        deleteDatabase("The_Daily_Planet");
        getDatabase("KITTENS");
        getTables();
    }

    private static void createConnection(String host, int port) {
        try {
            client = new MongoClient(host, port);
            System.out.println("Created client: " + client);
        } catch(com.mongodb.MongoSocketOpenException msoe) {
            System.out.println("Cannot connect to " + host + " on port " + port + "!");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void createDatabase(String name) {
        try {
            database = client.getDatabase(name);
            System.out.println("Created database: " + database.getName());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void createCredentials(String name, String password) {
        try {
            credentials = MongoCredential.createMongoCRCredential(name, database.getName(), password.toCharArray());
            System.out.println("Created credentials: " + credentials);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void createTable(String name) {
        try {
            database.createCollection(name);
            System.out.println("Created table: " + name);
        } catch(com.mongodb.MongoCommandException mce) {
            System.out.println("A table with the name " + name + " already exists!");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void getDatabase(String name) {
        try {
            MongoDatabase db = client.getDatabase(name);
            System.out.println("Database " + db.getName() + " retrieved!");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void getTable(String name) {
        try {
            System.out.println("Table retrieved: " + database.getCollection(name).getNamespace());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void getTables() {
        try {
            Iterator iterator = database.listCollectionNames().iterator();
            System.out.println("Tables retrieved:");
            while(iterator.hasNext()) {
                Object name = iterator.next();
                System.out.println(name);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void deleteDatabase(String name) {
        try {
            database.drop();
            System.out.println("Database " + name + " deleted!");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void deleteTable(String name) {
        try {
            database.getCollection(name).drop();
            System.out.println("Table " + name + " deleted!");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
