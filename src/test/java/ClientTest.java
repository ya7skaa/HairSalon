
import org.junit.Rule;
import org.junit.*;
import org.sql2o.*;
import static org.junit.Assert.*;


public class ClientTest {
    @Test
    public void savingClient_true(){
        Client client = new Client("Yasmin","F","254",1);
        client.save();
        assertEquals("Yasmin",client.getName());
    }
    //test to find client list
    @Test
    public void  findClient_true(){
        Client client = new Client("Yasmin","F","254",1);
        client.save();
        assertEquals("Yasmin", client.find(client.getId()).getName();
    }
    //test to update client list
    @Test
    public void updatingClient_true() {
        Client client = new Client("Yasmin","F","254",1);
        client.save();
        client.update("Abdi", "M", "254", 1);
        assertEquals("Abdi", Client.find(client.getId()).getName());
        assertEquals("M", Client.find(client.getId()).getGender());
        assertEquals("254", Client.find(client.getId()).getContact());
        assertEquals(2, Client.find(client.getId()).getStylist());
    }
}

