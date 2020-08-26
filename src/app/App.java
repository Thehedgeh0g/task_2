package app;

import message.Message;
import message.factory.FileMessageFactory;
import message.factory.MessageFactory;
import patch.Hurt;
import java.util.List;

public class App {
    public static void main(String[] args) {
        MessageFactory messageFactory = new FileMessageFactory("messages.txt");
        List<Message> messages = messageFactory.createMessages();


        for (Message message : messages) {

            System.out.println(message.getAuthor() + ":" + message.getText());

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
