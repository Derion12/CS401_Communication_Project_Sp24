import java.util.ArrayList;
import java.util.List;

public class LogList {
  private ArrayList<LogEntry> logs;
    // Constructor
    public LogList() {
        logs = new ArrayList<LogEntry>();
    }

    // Method to add a log entry to the list
    public void addLog(Date timestamp, ChatMsg text, Chatroom room) {
        LogEntry logEntry = new LogEntry(timestamp, text, room);
        logs.add(logEntry);
    }

    // Method to get all log entries
    public ArrayList<LogEntry> getAllLogs() {
        return logs;
    }

    public ArrayList<LogEntry> getRoomLogs(int roomID){
    	ArrayList<LogEntry> roomLogs = new ArrayList<LogEntry>();
    	
    	for(int i=0; i<logs.size(); i++) {
    		if(logs.get(i).getRoom().getID() == roomID) {
    			roomLogs.add(logs.get(i));
    		}
    	}
    	
    	return roomLogs;
    }
    
//    // Method to clear all log entries
//    public void clearLogs() {
//        logs.clear();
//    }

}
