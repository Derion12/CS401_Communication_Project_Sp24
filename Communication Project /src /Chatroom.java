import java.util.ArrayList;

public class Chatroom {
  private ArrayList<LogList> logs;
  private int id;
    // Constructor
    public Chatroom(ArrayList<LogList> roomLogs) {
        logs = new ArrayList<LogList>();
        logs = roomLogs;
    }

    public int getID() {
    	return id;
    }
    
//    // Method to send a message to the chat room
//    public void sendMessage(String sender, String content) {
//        
//    }
//
//    // Method to retrieve all messages in the chat room
//    public List<Message> getAllMessages() {
//        return messages;
//    }
//
//    // Method to clear all messages in the chat room
//    public void clearChat() {
//        messages.clear();
//    }

}
