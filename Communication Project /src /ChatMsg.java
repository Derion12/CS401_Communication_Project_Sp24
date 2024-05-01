public class ChatMsg {
    private User sender;
    private Chatroom room;
    private String messageContent;

    public ChatMsg(User sender, Chatroom room, String messageContent) {
        this.sender = sender;
        this.room = room;
        this.messageContent = messageContent;
    }

    public User getSender() {
        return sender;
    }

    public Chatroom getRoom() {
        return room;
    }

    public String getMessageContent() {
        return messageContent;
    }
   
}
