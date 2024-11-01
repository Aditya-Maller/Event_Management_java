import java.time.LocalDate;
import java.util.Scanner;

public class EventTrackerApp {
    public static void main(String[] args) {
        EventManager eventManager = new EventManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you want to add a new event? (yes/no)");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.print("Enter event type (Birthday/Anniversary/Festival/General): ");
            String type = scanner.nextLine();
            System.out.print("Enter event name: ");
            String name = scanner.nextLine();
            System.out.print("Enter event date (YYYY-MM-DD): ");
            LocalDate date = LocalDate.parse(scanner.nextLine());

            Event event = null;

            switch (type.toLowerCase()) {
                case "birthday":
                    System.out.print("Enter age: ");
                    int age = Integer.parseInt(scanner.nextLine());
                    event = new Birthday(name, date, age);
                    break;
                case "anniversary":
                    System.out.print("Enter celebration type: ");
                    String celebrationType = scanner.nextLine();
                    event = new Anniversary(name, date, celebrationType);
                    break;
                case "festival":
                    System.out.print("Enter festival name: ");
                    String festivalName = scanner.nextLine();
                    event = new Festival(name, date, festivalName);
                    break;
                case "general":
                    System.out.print("Enter event type: ");
                    String eventType = scanner.nextLine();
                    event = new GeneralEvent(name, date, eventType);
                    break;
                default:
                    System.out.println("Invalid event type.");
            }

            if (event != null) {
                eventManager.addEvent(event);
                System.out.println("Event added successfully!");
            }
        }

        // Display reminders for today's events
        eventManager.displayReminders();
        scanner.close();
    }
}
