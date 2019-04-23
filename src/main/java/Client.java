import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;
public class Client {

    private int id;
    private String name;
    private String gender;
    private String contact;
    private  int stylist_id;

  public Client(String name, String gender,String contact, int stylist_id);{
      this.name = name;
      this.gender = gender;
      this.contact = contact ;
      this.stylist_id = stylist_id;
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


    //method to return stylist Id
    public Stylist getStylist(){
      int id_check = stylist_id;
      Stylist stylist;
      if(id_check=0){
          stylist - new Stylist("","","");
      }else {
          try(Connection con = DB.sql2o.open()){
              String sql = "SELECT * FROM stylists where id=:id";
              stylist = con.createQuery(sql)
                      .addParameter("id",id)
                      .executeAndFET
          }
      }

    }

}
