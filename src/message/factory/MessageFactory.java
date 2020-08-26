package message.factory;

import message.Message;

import java.util.List;

public interface MessageFactory {
    List<Message> createMessages();
}
