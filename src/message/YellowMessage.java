package message;

public final class YellowMessage extends MessageDecorator {
    protected static final String YELLOW = "\u001B[33m";


    public YellowMessage(Message message) {
        super(message);
    }


    @Override
    public String getText() {
        return YELLOW + super.getText() + RESET;
    }
}
