
import client.ClientException;
import client.TcpClient;
import client.listener_references.Email;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import email_system.EmailClient;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashSet;

public class Main {

    public static void main(String... args) throws ClientException {

        TcpClient client = new TcpClient("localhost", 7070, true);
        System.out.println("Client is connected!");

        new EmailClient(client).start();

    }

}
