	// Define a LogEntry class to represent individual log entries
	class LogEntry {
	    private ChatMsg text;
	    private Date timestamp;
	    private Chatroom room;
	    // Constructor
	    public LogEntry(Date timestamp, ChatMsg text, Chatroom room) {
	        this.timestamp = timestamp;
	        this.text = text;
	        this.room = room;
	    }
	
	    // Getters
	    public Date getTimestamp() {
	        return timestamp;
	    }
	
	    public ChatMsg getText() {
	        return text;
	    }
	    
	    public Chatroom getRoom() {
	        return room;
	    }
	
	    // toString method to represent LogEntry as a string
	    @Override
	    public String toString() {
	        return timestamp + " - " + text.getSender() + ": " + text.getMessageContent();
	    }
	}
