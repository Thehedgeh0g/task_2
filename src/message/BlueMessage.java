package message;

public final class BlueMessage extends MessageDecorator {
    protected static final String BLUE = "\u001B[34m";


    public BlueMessage(Message message) {
        super(message);
    }


    @Override
    public String getText() {
        return BLUE + super.getText() + RESET;
    }
}
