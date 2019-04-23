import org.junit.Rule;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;


public class StylistTest {

    @Before
    public void setUp() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
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

}
