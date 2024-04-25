
public class Chatroom {
  private List<Message> messages;

    // Constructor
    public ChatRoom() {
        messages = new ArrayList<>();
    }

    // Method to send a message to the chat room
    public void sendMessage(String sender, String content) {
        Message message = new Message(sender, content);
        messages.add(message);
    }

    // Method to retrieve all messages in the chat room
    public List<Message> getAllMessages() {
        return messages;
    }

    // Method to clear all messages in the chat room
    public void clearChat() {
        messages.clear();
    }

}
