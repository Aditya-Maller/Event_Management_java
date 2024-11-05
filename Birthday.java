import java.time.LocalDate; 

public class Birthday extends Event { // Birthday event class inherits from Event
    private int age; // Encapsulation: private field for age

    public Birthday(String name, LocalDate date, int age) { // Constructor
        super(name, date); // Call constructor of superclass
        this.age = age; // Initialize age
    }

    public int getAge() { return age; } // Getter for age

    @Override
    public String getReminderMessage() { // Polymorphism: overriding the method for specific reminder message
        return "Don't forget " + getName() + "'s birthday on " + getDate() + "! They will be " + age + " years old."; // Return reminder
    }
}
