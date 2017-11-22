package nl.sogyo.newspaper.persistence;

import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import org.bson.*;
import jdk.nashorn.internal.objects.annotations.Getter;

//import javax.swing.text.Document;
import java.util.Iterator;

public class DatabaseManager {

    private MongoClient client;
    private MongoCredential credentials;
    private MongoDatabase database;

//    public static void main( String[] args ) {
//        // TODO Refactor Package Structure into nl.sogyo.Application.Persistence, nl.sogyo.Application.Domain etc
//        // TODO Database Tests against connected MongoDB instance
//        createClient("localhost", 27017);
//        createDatabase("The_Daily_Planet");
//        retrieveDatabase("The_Daily_Planet");
//        createCredentials("Clark Kent", "superman");
//        createTable("Metropolis_Unsolved_Cases");
//        getTable("Metropolis_Unsolved_Cases");
//        getTables();
//        deleteTable("Metropolis_Unsolved_Cases");
//        getTables();
//        deleteDatabase("The_Daily_Planet");
//        retrieveDatabase("KITTENS");
//        getTables();
//    }

    public void createClient(String host, int port) {
        try {
            client = new MongoClient(host, port);
            System.out.println("Created client: " + client);
        } catch(com.mongodb.MongoSocketOpenException msoe) {
            System.out.println("Could not create client on " + host + " with port " + port + "!");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void createDatabase(String name) {
        try {
            database = client.getDatabase(name);
            System.out.println("Created database: \"" + database.getName() + "\"");
        } catch(java.lang.IllegalArgumentException iae) {
            System.out.println("Database names cannot contain whitespaces, so database \"" + name + "\" was NOT created!");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void createCredentials(String name, String password) {
        try {
            credentials = MongoCredential.createMongoCRCredential(name, database.getName(), password.toCharArray());
            System.out.println("Created credentials: " + credentials);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void createTable(String name) {
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

    public void createDocumentInTable(String documentName, String tableName) {
        try {
            MongoCollection table = database.getCollection(tableName);

            Document document = new Document("_id", "1").append("Title", "The Dark Knight").append("Profession", "Vigilante");

            table.insertOne(document);
            System.out.println("Document " + documentName + " inserted in table " + tableName + "!");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void getDocumentsFromTable(String tableName) {
        try {
            Iterator iterator = database.getCollection(tableName).find().iterator();
            while(iterator.hasNext()) {
                Object name = iterator.next();
                System.out.println(name);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public MongoClient getClient() {
        return client;
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void retrieveDatabase(String name) {
        try {
            MongoDatabase db = client.getDatabase(name);
            System.out.println("Database " + db.getName() + " retrieved!");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void getTable(String name) {
        try {
            System.out.println("Table retrieved: " + database.getCollection(name).getNamespace());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void getTables() {
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

    public void deleteDatabase(String name) {
        try {
            database.drop();
            System.out.println("Database " + name + " deleted!");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTable(String name) {
        try {
            database.getCollection(name).drop();
            System.out.println("Table " + name + " deleted!");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
