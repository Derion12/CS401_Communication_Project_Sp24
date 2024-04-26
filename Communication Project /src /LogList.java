import java.util.ArrayList;
import java.util.List;

public class LogList {
  private List<LogEntry> logs;

    // Constructor
    public LogList() {
        logs = new ArrayList<>();
    }

    // Method to add a log entry to the list
    public void addLog(String timestamp, String eventType, String description) {
        LogEntry logEntry = new LogEntry(timestamp, eventType, description);
        logs.add(logEntry);
    }

    // Method to get all log entries
    public List<LogEntry> getAllLogs() {
        return logs;
    }

    // Method to clear all log entries
    public void clearLogs() {
        logs.clear();
    }

}
