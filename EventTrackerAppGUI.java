import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EventTrackerAppGUI extends JFrame {
    private EventManager eventManager; // Instance of EventManager
    private JTextField nameField, dateField, typeField, ageField; // Input fields
    private JTextArea reminderArea; // Area to display reminders
    private JComboBox<String> eventTypeBox; // Dropdown for event types

    public EventTrackerAppGUI() {
        eventManager = new EventManager(); // Create EventManager instance

        // Set up main frame
        setTitle("Event Tracker");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input panel for event details
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2));

        // Event type selection
        inputPanel.add(new JLabel("Event Type:"));
        eventTypeBox = new JComboBox<>(new String[]{"Birthday", "Anniversary", "General"});
        inputPanel.add(eventTypeBox);

        // Name field
        inputPanel.add(new JLabel("Event Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        // Date field
        inputPanel.add(new JLabel("Event Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        inputPanel.add(dateField);

        // Optional fields based on event type
        inputPanel.add(new JLabel("Age (if Birthday):"));
        ageField = new JTextField();
        inputPanel.add(ageField);

        inputPanel.add(new JLabel("Celebration/Type:"));
        typeField = new JTextField();
        inputPanel.add(typeField);

        // Add event button
        JButton addButton = new JButton("Add Event");
        addButton.addActionListener(new AddEventButtonListener());
        inputPanel.add(addButton);

        // Display reminders button
        JButton displayButton = new JButton("Display Today's Reminders");
        displayButton.addActionListener(new DisplayRemindersButtonListener());
        inputPanel.add(displayButton);

        // Reminder display area
        reminderArea = new JTextArea(10, 30);
        reminderArea.setEditable(false);

        // Add components to main frame
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(reminderArea), BorderLayout.CENTER);

        loadReminders(); // Load reminders on startup
    }

    private void loadReminders() {
        reminderArea.setText("Today's Reminders:\n");
        eventManager.displayReminders(reminderArea); // Display reminders from loaded events
    }

    private class AddEventButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = nameField.getText();
                LocalDate date = LocalDate.parse(dateField.getText());
                String selectedType = (String) eventTypeBox.getSelectedItem();

                // Create event based on selected type
                if (selectedType.equals("Birthday")) {
                    int age = Integer.parseInt(ageField.getText());
                    eventManager.addEvent(new Birthday(name, date, age));
                } else if (selectedType.equals("Anniversary")) {
                    String celebrationType = typeField.getText();
                    eventManager.addEvent(new Anniversary(name, date, celebrationType));
                } else if (selectedType.equals("General")) {
                    String eventType = typeField.getText();
                    eventManager.addEvent(new GeneralEvent(name, date, eventType));
                }

                // Clear fields after adding
                nameField.setText("");
                dateField.setText("");
                ageField.setText("");
                typeField.setText("");

                JOptionPane.showMessageDialog(null, "Event added successfully!");

            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid date (YYYY-MM-DD).");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid age (only numbers).");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
            }
        }
    }

    private class DisplayRemindersButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            reminderArea.setText("Today's Reminders:\n");
            eventManager.displayReminders(reminderArea); // Refresh reminders
        }
    }

    public static void main(String[] args) {
        EventTrackerAppGUI app = new EventTrackerAppGUI();
        app.setVisible(true);
    }
}
