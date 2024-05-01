import java.io.Serializable;
import java.util.ArrayList;

public class Chatroom implements Serializable{
  private ArrayList<LogEntry> logs;
  private String name;
  private int id = 0;
    // Constructor
    public Chatroom(ArrayList<LogEntry> roomLogs, String name, int id) {
    	this.name = name;
        logs = new ArrayList<LogEntry>();
        logs = roomLogs;
        this.id = id;
    }

    public String getName() {
    	return name;
    }
    
    public int getID() {
    	return id;
    }
    
    public ArrayList<String> printLogs(){
    	ArrayList<String> logsToPrint = new ArrayList<String>();
    	for(int i = 0; i<logs.size(); i++) {
    		logsToPrint.add(logs.get(i).toString());
    	}
    	
    	return logsToPrint;
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
