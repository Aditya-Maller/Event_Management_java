import java.time.LocalDate;

public class Festival extends Event {
    private String festivalName;

    public Festival(String name, LocalDate date, String festivalName) {
        super(name, date);
        this.festivalName = festivalName;
    }

    public String getFestivalName() {
        return festivalName;
    }

    @Override
    public String getReminderMessage() {
        return "Don't forget the " + festivalName + " festival today!";
    }

    @Override
    public boolean isToday() {
        LocalDate today = LocalDate.now();
        return today.getMonth() == getDate().getMonth() && today.getDayOfMonth() == getDate().getDayOfMonth();
    }
}
