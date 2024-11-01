import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private List<Event> events;

    public EventManager() {
        events = new ArrayList<>();
        loadEventsFromTextFiles();
    }

    public void addEvent(Event event) {
        events.add(event);
        saveEventToTextFile(event);
    }

    public void displayReminders() {
        boolean hasEventToday = false;

        for (Event event : events) {
            if (event.isToday()) {
                System.out.println(event.getReminderMessage());
                hasEventToday = true;
            }
        }

        if (!hasEventToday) {
            System.out.println("No Events Today");
        }
    }

    private void saveEventToTextFile(Event event) {
        String filename = "event_" + event.getName() + "_" + event.getDate() + ".txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("Event Type: " + event.getClass().getSimpleName());
            writer.println("Name: " + event.getName());
            writer.println("Date: " + event.getDate());
            
            if (event instanceof Birthday) {
                writer.println("Age: " + ((Birthday) event).getAge());
            } else if (event instanceof Anniversary) {
                writer.println("Celebration Type: " + ((Anniversary) event).getCelebrationType());
            } else if (event instanceof GeneralEvent) {
                writer.println("Event Type: " + ((GeneralEvent) event).getEventType());
            } else if (event instanceof Festival) {
                writer.println("Festival Name: " + ((Festival) event).getFestivalName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadEventsFromTextFiles() {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("."), "event_*.txt")) {
            for (Path entry : stream) {
                List<String> lines = Files.readAllLines(entry);

                // Ensure there are at least three lines for type, name, and date
                if (lines.size() < 3) {
                    System.out.println("Skipping file " + entry.getFileName() + ": Insufficient data.");
                    continue;
                }

                String name = lines.get(1).split(": ")[1];
                LocalDate date = LocalDate.parse(lines.get(2).split(": ")[1]);
                Event event = null;

                switch (lines.get(0).split(": ")[1]) {
                    case "Birthday":
                        if (lines.size() > 3) { // Check if age line is available
                            int age = Integer.parseInt(lines.get(3).split(": ")[1]);
                            event = new Birthday(name, date, age);
                        } else {
                            System.out.println("Skipping incomplete Birthday event in file " + entry.getFileName());
                        }
                        break;
                    case "Anniversary":
                        if (lines.size() > 3) { // Check if celebration type line is available
                            String celebrationType = lines.get(3).split(": ")[1];
                            event = new Anniversary(name, date, celebrationType);
                        } else {
                            System.out.println("Skipping incomplete Anniversary event in file " + entry.getFileName());
                        }
                        break;
                    case "GeneralEvent":
                        if (lines.size() > 3) { // Check if event type line is available
                            String eventType = lines.get(3).split(": ")[1];
                            event = new GeneralEvent(name, date, eventType);
                        } else {
                            System.out.println("Skipping incomplete GeneralEvent in file " + entry.getFileName());
                        }
                        break;
                    case "Festival":
                        if (lines.size() > 3) { // Check if festival name line is available
                            String festivalName = lines.get(3).split(": ")[1];
                            event = new Festival(name, date, festivalName);
                        } else {
                            System.out.println("Skipping incomplete Festival event in file " + entry.getFileName());
                        }
                        break;
                    default:
                        System.out.println("Unknown event type in file: " + entry.getFileName());
                }

                if (event != null) {
                    events.add(event);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
