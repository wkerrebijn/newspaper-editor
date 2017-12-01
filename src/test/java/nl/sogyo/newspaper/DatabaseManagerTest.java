package nl.sogyo.newspaper;

//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
import com.mongodb.MongoClient;
import nl.sogyo.newspaper.persistence.*;

import org.junit.*;

import java.util.logging.*;

import static org.junit.Assert.*;

public class DatabaseManagerTest {

    private DatabaseManager manager = DatabaseManager.getInstance();

//    @Before
//    public void beforeEachTest() {
//
//    }

//    @After
//    public void afterEachTest() {
//        manager = null;
//    }
//
//    @Test
//    public void clientAddressIsCorrect() {
//        MongoClient client = manager.getClient();
//        assertEquals(client.getAddress().toString(), "localhost:27017");
//    }
    @Test
    public void emptyTest() {
        assertTrue(true);
    }
}
