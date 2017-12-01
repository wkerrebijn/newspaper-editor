package nl.sogyo.newspaper.persistence;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.client.model.Filters;
import nl.sogyo.newspaper.model.Article;
import org.bson.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.*;

public class DatabaseManager {

    private static DatabaseManager instance;
    private Gson gson = new Gson();

    private Logger mongoLogger;
    private MongoClient client;
    private MongoCredential credentials;
    private MongoDatabase database;

//    public static void main( String[] args ) {
//        // TODO Refactor Package Structure into nl.sogyo.Application.Persistence, nl.sogyo.Application.Domain etc
//        // TODO Database Tests against connected MongoDB instance in Jenkins
//    }

    public static DatabaseManager getInstance() {
        if(instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    private DatabaseManager() {
        mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.OFF);
        createClient("localhost", 27017);
        useDatabase("The_Daily_Planet");
//        createTable("Articles");
    }

    public void createClient(String host, int port) {
        try {
            client = new MongoClient(host, port);
//            System.out.println("Created client: " + client);
        } catch(com.mongodb.MongoSocketOpenException msoe) {
            System.out.println("Could not create client on " + host + " with port " + port + "!");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void useDatabase(String name) {
        try {
            database = client.getDatabase(name);
 //           System.out.println("Created database: \"" + database.getName() + "\"");
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
 //           System.out.println("Created credentials: " + credentials);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void createTable(String name) {
        try {
            database.getCollection(name);
 //           System.out.println("Created table: " + name);
        } catch(com.mongodb.MongoCommandException mce) {
            System.out.println("A table with the name " + name + " already exists!");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void createDocumentInTable(String articleJson, String tableName) {
        try {
            MongoCollection table = database.getCollection(tableName);

            Document document = gson.fromJson(articleJson, Document.class);

            table.insertOne(document);
            System.out.println("Document " + document.get("_id") + " inserted in table " + tableName + "!");
            System.out.println("JSON format: " + document.toJson());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getDocumentsFromTable(String tableName) {
        ArrayList<String> documents = new ArrayList<String>();
        try {
            Iterator iterator = database.getCollection(tableName).find().iterator();
            while(iterator.hasNext()) {
                Object documentObject = iterator.next();
                String documentJson = ((Document)documentObject).toJson();
                documents.add(documentJson);
 //               System.out.println("Document " + documentJson + " retrieved from table " + tableName + "!");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return documents;
    }

    public ArrayList<String> getDocumentByIdFromTable(String id, String tableName) {
        ArrayList<String> documents = new ArrayList<String>();
        try {
            Iterator iterator = database.getCollection(tableName).find(Filters.eq("_id", id)).iterator();
            while(iterator.hasNext()) {
                Object documentObject = iterator.next();
                String documentJson = ((Document)documentObject).toJson();
                documents.add(documentJson);
                System.out.println("Document " + documentJson + " retrieved from table " + tableName + "!");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return documents;
    }

    public void deleteDocumentByIdFromTable(String id, String tableName) {
        try {
            MongoCollection table = database.getCollection(tableName);
            BasicDBObject query = new BasicDBObject();
            query.append("_id", id);
            table.findOneAndDelete(query);
            System.out.println("Document with ID " + id + " deleted from table " + tableName + "!");
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

    public void getTable(String name) {
        try {
 //           System.out.println("Table retrieved: " + database.getCollection(name).getNamespace());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void getTables() {
        try {
            Iterator iterator = database.listCollectionNames().iterator();
//            System.out.println("Tables retrieved:");
            while(iterator.hasNext()) {
                Object name = iterator.next();
//                System.out.println(name);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteDatabase(String name) {
        try {
            database.drop();
//            System.out.println("Database " + name + " deleted!");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTable(String name) {
        try {
            database.getCollection(name).drop();
 //           System.out.println("Table " + name + " deleted!");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
