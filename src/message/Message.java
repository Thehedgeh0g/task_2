package message;

public class Message {
    protected static final String BOLD_FONT = "\u001B[1m";
    protected static final String RESET = "\u001B[0m";

    private final String text;
    private final String author;


    public Message(String text, String author) {
        this.text = text;
        this.author = author;
    }


    public String getText() {
        return text;
    }

    public String getAuthor() {
        return BOLD_FONT + author + RESET;
    }

    public String print() {
        return getAuthor() + ": " + getText();
    }
}
