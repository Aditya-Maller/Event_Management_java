import java.time.LocalDate;

public abstract class Event {  // Abstraction: base class defining common event properties
    private String name; //-->encapsulation
    private LocalDate date;

    public Event(String name,LocalDate date) {
        this.name=name;
        this.date=date;
    }

    public String getName() { return name; }
    public LocalDate getDate() { return date; }

    public abstract String getReminderMessage();  // Polymorphism: method to be overridden
}
