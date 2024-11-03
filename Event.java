// Location: Desktop/projects/Event_Tracker/src/Event.java

import java.time.LocalDate;

public abstract class Event {
    private String name;
    private LocalDate date;

    public Event(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }
    
    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    // Abstract method to get reminder message
    public abstract String getReminderMessage();
}
