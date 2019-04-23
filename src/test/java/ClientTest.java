
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
        assertEquals(1, Client.find(client.getId()).getStylist());
    }
    //delete client test
    @Test
    public void delete_deletesClient_true() {
        Client client = new Client("Yasmin","F","254",1);
        client.save();
        int clientId = client.getId();
        client.delete();
        assertEquals(null, Client.find(clientId));
    }

}

