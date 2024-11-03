// Location: Desktop/projects/Event_Tracker/src/Anniversary.java

import java.time.LocalDate;

public class Anniversary extends Event {
    private String celebrationType;

    public Anniversary(String name, LocalDate date, String celebrationType) {
        super(name, date);
        this.celebrationType = celebrationType;
    }

    public String getCelebrationType() {
        return celebrationType;
    }

    @Override
    public String getReminderMessage() {
        return "Celebrate " + getName() + "'s " + celebrationType + " anniversary on " + getDate() + "!";
    }
}
