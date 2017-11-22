package nl.sogyo.newspaper.model;

import nl.sogyo.newspaper.persistence.DatabaseManager;

import java.util.logging.*;

public class Runner {
    static DatabaseManager dbm;
    static Logger mongoLogger;

    public static void main(String[] args) {
        disableMongoLoggingToConsole(true);

        dbm = new DatabaseManager();
        dbm.createClient("localhost", 27017);

        dbm.createDatabase("Gotham_Crime_Statistics");
        dbm.getTables();
        dbm.deleteTable("Murders");
        dbm.createTable("Murders");
        dbm.createDocumentInTable("Justice","Murders");
        dbm.getDocumentsFromTable("Murders_2");
        dbm.deleteDatabase("Gotham_Crime_Statistics");
    }

    private static void disableMongoLoggingToConsole(boolean disable) {
        if (disable) {
            mongoLogger = Logger.getLogger("org.mongodb.driver");
            mongoLogger.setLevel(Level.OFF);
        }
    }
}
