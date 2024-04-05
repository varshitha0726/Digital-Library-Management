import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Fine {
    private LocalDate dueDate;
    private LocalDate currDate;

    public Fine(LocalDate dueDate, LocalDate currDate) {
        this.dueDate = dueDate;
        this.currDate = currDate;
    }

    public long fine() {
        long days = ChronoUnit.DAYS.between(dueDate, currDate);
        return days * 2;
    }
}
