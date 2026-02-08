package chatbox.main;
//Inheritance of Task
import java.time.LocalDateTime;
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = DateParser.parseDateTime(from);
        this.to = DateParser.parseDateTime(to);
        if (this.from == null || this.to == null) {
            throw new IllegalArgumentException("You key in the format that I don't understand! Please Use d/M/yyyy HHmm (e.g., 2/12/2019 1800)");
        }
    }
    @Override
    public String toString() {
            return "[E]" + super.toString() + " (from: " + DateParser.format(from) + " to: " + DateParser.format(to) + ")";
    }
}