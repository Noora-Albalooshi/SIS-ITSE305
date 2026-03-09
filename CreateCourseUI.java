import javax.swing.*;
import java.awt.*;

// Presentation layer class responsible for the user interface
public class CreateCourseUI extends JFrame {
    private JTextField idField;
    private JTextField nameField;
    private JTextField creditField;
    private JTextField prerequisiteField;
    private JTextArea outputArea;
    private JButton addButton;

    // Connects the UI to the business layer
    private CourseService service = new CourseService();

    public CreateCourseUI() {
        setTitle("Create Course");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Form panel using GridBagLayout for better alignment
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Course ID:"), gbc);

        gbc.gridx = 1;
        idField = new JTextField(20);
        formPanel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Course Name:"), gbc);

        gbc.gridx = 1;
        nameField = new JTextField(20);
        formPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Credit Hours:"), gbc);

        gbc.gridx = 1;
        creditField = new JTextField(20);
        formPanel.add(creditField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Prerequisite:"), gbc);

        gbc.gridx = 1;
        prerequisiteField = new JTextField(20);
        formPanel.add(prerequisiteField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        addButton = new JButton("Add Course");
        formPanel.add(addButton, gbc);

        // Output area to show confirmation and course details
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setMargin(new Insets(10, 10, 10, 10));

        add(formPanel, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Reads input from UI and sends it to the business layer
        addButton.addActionListener(e -> {
            try {
                String id = idField.getText();
                String name = nameField.getText();
                int credit = Integer.parseInt(creditField.getText());
                String prerequisite = prerequisiteField.getText();

                Course course = service.addCourse(id, name, credit, prerequisite);

                outputArea.setText("Course added successfully!\n\n" + course.getCourseDetails());

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a valid number for credit hours.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this,
                        ex.getMessage(),
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // Starts the Swing application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CreateCourseUI().setVisible(true));
    }
}