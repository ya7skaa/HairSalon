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

    // testing save method
    @Test
    public void savingStylist_true() {
        Stylist stylist = new Stylist("Alpha","M","500");
        stylist.save();
        assertEquals("Alpha", stylist.getName());
    }
}
