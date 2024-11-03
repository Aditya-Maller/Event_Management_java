// // Location: Desktop/projects/Event_Tracker/src/EventManager.java

// import java.util.ArrayList;
// import java.util.List;
// import java.time.LocalDate;

// public class EventManager {
//     private List<Event> events;
 
//     public EventManager() {
//         events = new ArrayList<>();
//     }

//     public void addEvent(Event event) {
//         events.add(event);
//     }

//     public void displayReminders() {
//         System.out.println("Checking reminders..."); // Add this line to confirm method execution
//         LocalDate today = LocalDate.now();
//         for (Event event : events) {
//             System.out.println("Event Date: " + event.getDate()); // Print event dates
//             if (event.getDate().isEqual(today)) {
//                 System.out.println(event.getReminderMessage());
//                 System.out.println("YOOOO");
//             }
//         }
//     }
    
// }


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import javax.swing.*;

public class EventManager {
    private List<Event> events;

    public EventManager() {
        events = new ArrayList<>();
        loadEventsFromFile(); // Load events from file when initialized
    }

    public void addEvent(Event event) {
        events.add(event);
        saveEventsToFile(); // Save events every time a new event is added
    }

    public void displayReminders(JTextArea outputArea) {
        LocalDate today = LocalDate.now();
        for (Event event : events) {
            if (event.getDate().isEqual(today)) {
                outputArea.append(event.getReminderMessage() + "\n");
            }
        }
    }

    // Save events to a CSV file
    private void saveEventsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("events.csv"))) {
            for (Event event : events) {
                if (event instanceof Birthday) {
                    Birthday birthday = (Birthday) event;
                    writer.write("Birthday," + birthday.getName() + "," + birthday.getDate() + "," + birthday.getAge());
                } else if (event instanceof Anniversary) {
                    Anniversary anniversary = (Anniversary) event;
                    writer.write("Anniversary," + anniversary.getName() + "," + anniversary.getDate() + "," + anniversary.getCelebrationType());
                } else if (event instanceof GeneralEvent) {
                    GeneralEvent generalEvent = (GeneralEvent) event;
                    writer.write("General," + generalEvent.getName() + "," + generalEvent.getDate() + "," + generalEvent.getEventType());
                }
                writer.newLine(); // Move to the next line for the next event
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
    }

    // Load events from a CSV file
    private void loadEventsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("events.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String eventType = parts[0];
                String name = parts[1];
                LocalDate date = LocalDate.parse(parts[2]);

                switch (eventType) {
                    case "Birthday":
                        int age = Integer.parseInt(parts[3]);
                        events.add(new Birthday(name, date, age));
                        break;
                    case "Anniversary":
                        String celebrationType = parts[3];
                        events.add(new Anniversary(name, date, celebrationType));
                        break;
                    case "General":
                        String generalEventType = parts[3];
                        events.add(new GeneralEvent(name, date, generalEventType));
                        break;
                }
            }
        } catch (IOException e) {
            // If the file does not exist yet, that's okay
        }
    }
}
