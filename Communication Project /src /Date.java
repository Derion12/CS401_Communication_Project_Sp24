import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Date implements Serializable {
    private LocalDateTime currentDate;
    private String formattedDate;

    // Constructor to initialize the date to the current date
    public Date() {
        currentDate = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        formattedDate = currentDate.format(myFormatObj);
    }
    public Date(String logDate) {	//if the date already exists
        formattedDate = logDate;
    }

    // Method to get the current date
    public String getCurrentDate() {
        return formattedDate;
    }
//
//    // Method to display the current date
//    public void displayDate() {
//        System.out.println(currentDate);
//    }
}
