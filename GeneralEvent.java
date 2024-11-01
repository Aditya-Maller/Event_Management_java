import java.time.LocalDate;

public class GeneralEvent extends Event {
    private String eventType;

    public GeneralEvent(String name, LocalDate date, String eventType) {
        super(name, date);
        this.eventType = eventType;
    }

    public String getEventType() {
        return eventType;
    }

    @Override
    public String getReminderMessage() {
        return "Remember the " + eventType + " event for " + getName() + " today.";
    }

    @Override
    public boolean isToday() {
        return LocalDate.now().isEqual(getDate());
    }
}
