import java.time.LocalDate;

public class Birthday extends Event {
    private int age;

    public Birthday(String name, LocalDate date, int age) {
        super(name, date);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String getReminderMessage() {
        return "Don't forget " + getName() + "'s birthday today! They will be " + age + " years old.";
    }

    @Override
    public boolean isToday() {
        LocalDate today = LocalDate.now();
        return today.getMonth() == getDate().getMonth() && today.getDayOfMonth() == getDate().getDayOfMonth();
    }
}
