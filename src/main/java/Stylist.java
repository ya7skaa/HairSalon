import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Stylist {
    private int id;
    private String name;
    private String gender;
    private String contact;
    private List<Client> clients;

    public Stylist(String name, String gender, String contact) {
        this.name = name;
        this.gender = gender;
        this.contact = contact;
        clients = new ArrayList<>();
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getGender(){
        return gender;
    }
    public String getContact(){
        return contact;
    }
    //method to return a Stylist object.
    public static Stylist find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM stylists where id=:id";
            Stylist stylist = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Stylist.class);
            return stylist;
        }
    }
    // method to return all Stylist information from our stylist database table in the all() method:
    public static List<Stylist> all() {
        String sql = "SELECT id, name, gender, contact FROM stylists ORDER BY id";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Stylist.class);
        }
    }
    // method for saving new object to the database && assign the object the same id as its data in the database:
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO stylists(name, gender, contact) VALUES (:name, :gender, :contact)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("gender", this.gender)
                    .addParameter("contact", this.contact)
                    .executeUpdate()
                    .getKey();
        }
    }
//    // method for getting clients from stylist object
//    public List<Client> getClients() {
//        try(Connection con = DB.sql2o.open()) {
//            String sql = "SELECT * FROM clients where stylist_id=:id ORDER BY name";
//            return con.createQuery(sql)
//                    .addParameter("id", this.id)
//                    .executeAndFetch(Client.class);
//        }
//    }
    //method for updating the stylist object
    public void update(String name, String gender, String contact) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "UPDATE stylists SET name = :name, gender = :gender, contact = :contact WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("gender", gender)
                    .addParameter("contact", contact)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
    //deleting stylist object
    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM stylists WHERE id = :id;";
            con.createQuery(sql).addParameter("id", id).executeUpdate();
        }
        //Assign client_id to 0 for Clients allocated to the deleted Stylist
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE clients SET stylist_id = 0 WHERE stylist_id = :id;";
            con.createQuery(sql).addParameter("id", id).executeUpdate();
        }
    }
    public List<Client> getClients() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM clients where stylist_id=:id";
            return con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeAndFetch(Client.class);
        }
    }

}




