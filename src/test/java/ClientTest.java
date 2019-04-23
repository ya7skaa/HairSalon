
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
    pulic void  findClient_true(){
        Client client = new Client("Yasmin","F","254",1);
        client.save();
        assertEquals("Yasmin", client.find(client.getId()).getName();
    }
    //test to update client list
}

