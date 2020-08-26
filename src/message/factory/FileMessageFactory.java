package message.factory;

import encryptor.RandomEncryption;
import message.BlueMessage;
import message.Message;
import message.RedMessage;
import message.YellowMessage;
import patch.Hurt;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class FileMessageFactory implements MessageFactory {
    private final String pathToFile;


    public FileMessageFactory(String pathToFile) {
        this.pathToFile = pathToFile;
    }


    @Override
    public List<Message> createMessages() {
        List<Message> messages = new ArrayList<>();

        InputStream inputResource = this.getClass().getClassLoader().getResourceAsStream(pathToFile);

        if (inputResource == null) {
            System.err.println("No such file: " + pathToFile);
            System.exit(1);
        }

        Scanner scanner = new Scanner(inputResource);
        String line;
        while (scanner.hasNext()) {
            line = scanner.nextLine();
            if (!line.contains(":")) {
                continue;
            }

            String author = line.split(":")[0];
            String text = RandomEncryption.encrypt(line.split(":")[1]);
            text = Hurt.chooser(text);

            if (author.equals("Ярик")) {
                messages.add(new RedMessage(new Message(text, author)));
                continue;
            }

            if (author.equals("Леха")) {
                messages.add(new YellowMessage(new Message(text, author)));
                continue;
            }

            messages.add(new BlueMessage(new Message(text, author)));
        }

        return messages;
    }
}
