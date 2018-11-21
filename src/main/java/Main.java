
import client.ClientException;
import client.TcpClient;
import email_system.EmailClient;

public class Main {

    public static void main(String... args) throws ClientException {

        TcpClient client = new TcpClient("localhost", 7070, true);
        System.out.println("Client is connected!");

        new EmailClient(client).start();
    }

}
