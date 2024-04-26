import java.util.Date;

public class ChatMsg {
    private int messageId;
    private User sender;
    private User receiver;
    private String messageContent;
    private Date timestamp;

    public ChatMsg(int messageId, User sender, User receiver, String messageContent) {
        this.messageId = messageId;
        this.sender = sender;
        this.receiver = receiver;
        this.messageContent = messageContent;
        this.timestamp = new Date(); // Set current timestamp
    }

    public int getMessageId() {
        return messageId;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public Date getcurrentDate() {
        return timestamp;
    }

   
}
