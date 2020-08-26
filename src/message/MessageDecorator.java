package message;

public abstract class MessageDecorator extends Message {
    protected final Message message;


    public MessageDecorator(Message message) {
        super(message.getText(), message.getAuthor());
        this.message = message;
    }
}
