package nl.sogyo.newspaper.model;

import nl.sogyo.newspaper.persistence.DatabaseManager;

import java.util.logging.*;

public class Runner {
//    private static final Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
    private static final DatabaseManager dbm = DatabaseManager.getInstance();

    public static void main(String[] args) {
//        disableMongoLoggingToConsole(true);

//        dbm.useDatabase("Gotham_Crime_Statistics");
        dbm.getTables();
//        dbm.deleteTable("Documents");
        dbm.createTable("Articles");
//        dbm.createDocumentInTable("Justice","Articles");
//        dbm.createDocumentInTable("{ \"_id\" : \"1\", \"title\" : \"kittens\"}", "Articles");
        dbm.getDocumentsFromTable("Articles");
        dbm.getDocumentByIdFromTable("1", "Articles");
        dbm.deleteDocumentByIdFromTable("1", "Articles");
        dbm.getDocumentByIdFromTable("1", "Articles");
//        dbm.deleteDatabase("The_Daily_Planet");
    }

//    private static void disableMongoLoggingToConsole(boolean disable) {
//        if (disable) {
//            mongoLogger.setLevel(Level.OFF);
//        }
//    }
}
