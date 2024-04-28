import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

// Define a LogEntry class to represent individual log entries
class LogEntry {
    private String eventType;
    private String description;
    private Date timestamp;
    // Constructor
    public LogEntry(String timestamp, String eventType, String description) {
        this.timestamp = timestamp;
        this.eventType = eventType;
        this.description = description;
    }

    // Getters
    public LocalTime getTimestamp() {
        return timestamp;
    }

    public String getEventType() {
        return eventType;
    }

    public String getDescription() {
        return description;
    }

    // toString method to represent LogEntry as a string
    @Override
    public String toString() {
        return timestamp + " - " + eventType + ": " + description;
    }
}
