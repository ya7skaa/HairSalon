import org.junit.Rule;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;


public class StylistTest {

    @Before
    public void setUp() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", "abdirahmanosman", "yasmin");
    }

    @After
    public void tearDown() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM clients *;";
            con.createQuery(sql).executeUpdate();
        }
    }

    // testing the save method
    @Test
    public void savingStylist_true() {
        Stylist stylist = new Stylist("Zak","F","49");
        stylist.save();
        assertEquals("Sum", stylist.getName());
    }

    // testing for update method
    @Test
    public void updatingStylist_true() {
        Stylist stylist = new Stylist("Yasmin","F","254");
        stylist.save();
        stylist.update("Sam", "M", "67");
        assertEquals("Sam", Stylist.find(stylist.getId()).getName());
        assertEquals("M", Stylist.find(stylist.getId()).getGender());
        assertEquals("67", Stylist.find(stylist.getId()).getContact());
    }
    // test to find stylist
    @Test
    public void findStylist_true() {
        Stylist stylist = new Stylist("Zak","F","49");
        stylist.save();
        assertEquals("Zak", Stylist.find(stylist.getId()).getName());
    }
    // test to delete stylist
    @Test
    public void delete_deletesStylist_true() {
        Stylist stylist = new Stylist("Zak","F","49");
        stylist.save();
        int stylistId = stylist.getId();
        stylist.delete();
        assertEquals(null, Stylist.find(stylistId));
    }
    @Test
    public void getTasks_retrievesALlTasksFromDatabase_tasksList() {
        Stylist myStylist = new Stylist("Yasmin","F","254");
        myStylist.save();
        Client firstClient = new Client("Yasmin","F","254",myStylist.getId());
        firstClient.save();
        Client secondClient = new Client("Zak","F","254",myStylist.getId());
        secondClient.save();
        Client[] clients = new Client[] { firstClient, secondClient };
        assertTrue(myStylist.getClients().containsAll(Arrays.asList(clients)));
    }

}
