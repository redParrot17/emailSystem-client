
import client.ClientException;
import client.TcpClient;
import client.listener_references.Email;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashSet;

public class Main {

    public static void main(String... args) throws ClientException {

        //MainWindow mw = new MainWindow();

        TcpClient client = new TcpClient("localhost", 7070, true);
        System.out.println("Client is connected!");

        client.sendCommand("delete-emails", "John");

        client.sendEmail(new Email("Eco", new String[] {"John"}, "Cats", "I love cats!!!!!"));
        client.sendEmail(new Email("Eco", new String[] {"John"}, "Dogs", "I love dogs too!!!!!"));

        client.sendCommand("get-emails", "John");

        client.addCommandListener(command -> {
            System.out.println(command.getCommand());
            Type type = new TypeToken<HashSet<Email>>(){}.getType();
            HashSet<Email> emails = new Gson().fromJson(command.getArguments(), type);
            emails.forEach(email -> {
                System.out.println(
                        "To: " + Arrays.toString(email.getRecipients()) +
                                "\nFrom: " + email.getAuthor() +
                                "\nSubject: " + email.getSubject() +
                                "\nMessage: " + email.getMessage() +
                                "\nHas read: " + email.hasOpened() +
                                "\n");
            });
        });

    }

}
