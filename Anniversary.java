import java.time.LocalDate;

public class Anniversary extends Event {
    private String celebrationType; // Type of anniversary celebration

    public Anniversary(String name, LocalDate date, String celebrationType) {
        super(name, date); // Call constructor of the superclass (Event)
        this.celebrationType = celebrationType; // Set the celebration type
    }

    public String getCelebrationType() {
        return celebrationType;
    }

    @Override
    public String getReminderMessage() {//polymorphism
        // Provide a specific reminder message for anniversaries
        return "Celebrate " + getName() + "'s " + celebrationType + " anniversary on " + getDate() + "!";
    }
}
