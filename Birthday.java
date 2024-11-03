// Location: Desktop/projects/Event_Tracker/src/Birthday.java

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
        return "Don't forget " + getName() + "'s birthday on " + getDate() + "! They will be " + age + " years old.";
    }
}
