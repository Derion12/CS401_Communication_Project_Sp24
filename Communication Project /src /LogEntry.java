
import java.io.Serializable;
// Define a LogEntry class to represent individual log entries
	class LogEntry  implements Serializable{
	    private String text;
	    private Date timestamp;
	    private int roomID;
	    private String userID;
	    // Constructor
	    public LogEntry(Date timestamp, String text, int roomID, String userID) {
	        this.timestamp = timestamp;
	        this.text = text;
	        this.roomID = roomID;
	        this.userID = userID;
	    }
	
	    // Getters
	    public Date getTimestamp() {
	        return timestamp;
	    }
	
	    public String getText() {
	        return text;
	    }
	    
	    public int getRoomID() {
	        return roomID;
	    }
	    public String getUserID() {
	        return userID;
	    }
	
	    
	    // toString method to represent LogEntry as a string

	    public String toString() {
	        return Integer.toString(roomID) + "-" + timestamp.getCurrentDate() + "-" + userID + "-" + text;
	    }
	}
