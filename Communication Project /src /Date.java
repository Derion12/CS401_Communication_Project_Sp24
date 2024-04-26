import java.time.LocalDate;


public class Date {
    private LocalDate currentDate;

    // Constructor to initialize the date to the current date
    public Date() {
        currentDate = LocalDate.now();
    }

    // Constructor to initialize the date with a specific year, month, and day
    public Date(int year, int month, int day) {
        currentDate = LocalDate.of(year, month, day);
    }

    // Method to get the current date
    public LocalDate getCurrentDate() {
        return currentDate;
    }

    // Method to display the current date
    public void displayDate() {
        System.out.println(currentDate);
    }
}
