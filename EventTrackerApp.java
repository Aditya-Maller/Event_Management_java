// import java.time.LocalDate;
// import java.time.format.DateTimeParseException;
// import java.util.Scanner;

// public class EventTrackerApp {
//     public static void main(String[] args) {
//         EventManager eventManager = new EventManager();
//         Scanner scanner = new Scanner(System.in);

//         System.out.println("Welcome to the Event Tracker!");

//         while (true) {
//             try {
//                 System.out.println("\nEnter event type (birthday, anniversary, general) or 'exit' to finish:");
//                 String eventType = scanner.nextLine().toLowerCase();

//                 if (eventType.equals("exit")) {
//                     break;
//                 }
//                 System.out.print("Enter event name: ");
//                 String name = scanner.nextLine();

//                 System.out.print("Enter event date (YYYY-MM-DD): ");
//                 LocalDate date;
//                 try {
//                     date = LocalDate.parse(scanner.nextLine());
//                 } catch (DateTimeParseException e) {
//                     System.out.println("Invalid date format. Please use YYYY-MM-DD.");
//                     continue;
//                 }

//                 switch (eventType) {
//                     case "birthday":
//                         System.out.print("Enter age: ");
//                         int age;
//                         try {
//                             age = Integer.parseInt(scanner.nextLine());
//                         } catch (NumberFormatException e) {
//                             System.out.println("Invalid age. Please enter a valid number.");
//                             continue;
//                         }
//                         eventManager.addEvent(new Birthday(name, date, age));
//                         break;

//                     case "anniversary":
//                         System.out.print("Enter celebration type (e.g., wedding): ");
//                         String celebrationType = scanner.nextLine();
//                         eventManager.addEvent(new Anniversary(name, date, celebrationType));
//                         break;

//                     case "general":
//                         System.out.print("Enter event type (e.g., work, personal): ");
//                         String type = scanner.nextLine();
//                         eventManager.addEvent(new GeneralEvent(name, date, type));
//                         break;

//                     default:
//                         System.out.println("Unknown event type. Please enter 'birthday', 'anniversary', or 'general'.");
//                 }

//             } catch (Exception e) {
//                 System.out.println("An unexpected error occurred: " + e.getMessage());
//             }
//         }

//         System.out.println("\nToday's Reminders:");
//         eventManager.displayReminders();
//         scanner.close();
//     }
// }


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class EventTrackerApp {
    public static void main(String[] args) {
        EventManager eventManager = new EventManager(); // Create EventManager

        // Create the GUI
        JFrame frame = new JFrame("Event Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel, eventManager);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel, EventManager eventManager) {
        panel.setLayout(null);

        JLabel nameLabel = new JLabel("Event Name:");
        nameLabel.setBounds(10, 20, 80, 25);
        panel.add(nameLabel);

        JTextField nameText = new JTextField(20);
        nameText.setBounds(150, 20, 165, 25);
        panel.add(nameText);

        JLabel dateLabel = new JLabel("Event Date:");
        dateLabel.setBounds(10, 50, 80, 25);
        panel.add(dateLabel);

        JTextField dateText = new JTextField(10);
        dateText.setBounds(150, 50, 165, 25);
        panel.add(dateText);

        JLabel ageLabel = new JLabel("Age (if Birthday):");
        ageLabel.setBounds(10, 80, 150, 25);
        panel.add(ageLabel);

        JTextField ageText = new JTextField(3);
        ageText.setBounds(150, 80, 165, 25);
        panel.add(ageText);

        JLabel celebrationLabel = new JLabel("Celebration Type:");
        celebrationLabel.setBounds(10, 110, 150, 25);
        panel.add(celebrationLabel);

        JTextField celebrationText = new JTextField(20);
        celebrationText.setBounds(150, 110, 165, 25);
        panel.add(celebrationText);

        JButton addButton = new JButton("Add Event");
        addButton.setBounds(10, 140, 150, 25);
        panel.add(addButton);

        JTextArea outputArea = new JTextArea();
        outputArea.setBounds(10, 180, 350, 100);
        outputArea.setEditable(false);
        panel.add(outputArea);

        // Action Listener for the Add Event button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameText.getText();
                LocalDate date = LocalDate.parse(dateText.getText());
                String celebrationType = celebrationText.getText();
                int age = ageText.getText().isEmpty() ? 0 : Integer.parseInt(ageText.getText());
                
                // Determine the event type and add accordingly
                if (!celebrationType.isEmpty()) {
                    eventManager.addEvent(new Anniversary(name, date, celebrationType));
                } else if (age > 0) {
                    eventManager.addEvent(new Birthday(name, date, age));
                } else {
                    eventManager.addEvent(new GeneralEvent(name, date, "General"));
                }

                outputArea.setText(""); // Clear previous outputs
                eventManager.displayReminders(outputArea); // Display reminders
            }
        });
    }
}
