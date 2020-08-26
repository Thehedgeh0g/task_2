package message;

public final class RedMessage extends MessageDecorator {
    private static final String RED = "\u001B[31m";


    public RedMessage(Message message) {
        super(message);
    }


    @Override
    public String getText() {
        return RED + super.getText() + RESET;
    }
}
