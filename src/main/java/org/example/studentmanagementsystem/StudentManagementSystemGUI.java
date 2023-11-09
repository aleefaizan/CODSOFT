package org.example.studentmanagementsystem;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class StudentManagementSystemGUI extends JFrame {
    private List<Student> students = new ArrayList<>();
    private DefaultTableModel tableModel;
    private JTextField nameField, rollNumberField, gradeField;
    private JComboBox<String> studentComboBox;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentManagementSystemGUI gui = new StudentManagementSystemGUI();
            gui.setVisible(true);
        });
    }

    public StudentManagementSystemGUI() {
        setTitle("Student Management System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        nameField = new JTextField(15);
        rollNumberField = new JTextField(10);
        gradeField = new JTextField(5);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        JButton editButton = new JButton("Edit Student");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editStudent();
            }
        });

        JButton searchButton = new JButton("Search Student");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchStudent();
            }
        });

        JButton getAllStudentsButton = new JButton("Get All Students");
        getAllStudentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTable();
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // ComboBox for selecting students
        studentComboBox = new JComboBox<>();
        studentComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateFields();
            }
        });

        // Table model and table
        String[] columnNames = {"Name", "Roll Number", "Grade"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable studentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(studentTable);

        // Layout
        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Roll Number:"));
        panel.add(rollNumberField);
        panel.add(new JLabel("Grade:"));
        panel.add(gradeField);
        panel.add(new JLabel());
        panel.add(addButton);
        panel.add(new JLabel("Select Student:"));
        panel.add(studentComboBox);
        panel.add(editButton);
        panel.add(searchButton);
        panel.add(getAllStudentsButton);
        panel.add(exitButton);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(panel, BorderLayout.NORTH);
        container.add(scrollPane, BorderLayout.CENTER);

        // Initialize the ComboBox
        populateComboBox();
    }

    private void addStudent() {
        try {
            String name = nameField.getText();
            int rollNumber = Integer.parseInt(rollNumberField.getText());
            char grade = gradeField.getText().charAt(0);

            Student newStudent = new Student(name, rollNumber, grade);
            students.add(newStudent);

            // Update the table
            Object[] rowData = {name, rollNumber, grade};
            tableModel.addRow(rowData);

            // Update the ComboBox
            populateComboBox();

            // Clear the fields
            nameField.setText("");
            rollNumberField.setText("");
            gradeField.setText("");
        } catch (NumberFormatException | StringIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid data.");
        }
    }

    private void editStudent() {
        int selectedIndex = studentComboBox.getSelectedIndex();
        if (selectedIndex >= 0) {
            try {
                String name = nameField.getText();
                int rollNumber = Integer.parseInt(rollNumberField.getText());
                char grade = gradeField.getText().charAt(0);

                Student editedStudent = new Student(name, rollNumber, grade);
                students.set(selectedIndex, editedStudent);

                // Update the table
                tableModel.setValueAt(name, selectedIndex, 0);
                tableModel.setValueAt(rollNumber, selectedIndex, 1);
                tableModel.setValueAt(grade, selectedIndex, 2);

                JOptionPane.showMessageDialog(this, "Student information updated successfully.");

                // Clear the fields
                nameField.setText("");
                rollNumberField.setText("");
                gradeField.setText("");
            } catch (NumberFormatException | StringIndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid data.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a student to edit.");
        }
    }

    private void searchStudent() {
        String rollNumberStr = JOptionPane.showInputDialog(this, "Enter Roll Number to search:");
        if (rollNumberStr != null && !rollNumberStr.isEmpty()) {
            try {
                int rollNumber = Integer.parseInt(rollNumberStr);

                for (Student student : students) {
                    if (student.getRollNumber() == rollNumber) {
                        // Display the student's information
                        nameField.setText(student.getName());
                        rollNumberField.setText(String.valueOf(student.getRollNumber()));
                        gradeField.setText(String.valueOf(student.getGrade()));

                        // Select the student in the ComboBox
                        studentComboBox.setSelectedItem(student.getName());

                        return;
                    }
                }

                JOptionPane.showMessageDialog(this, "Student not found.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid Roll Number.");
            }
        }
    }

    private void refreshTable() {
        // Clear existing data
        tableModel.setRowCount(0);

        // Add all students to the table
        for (Student student : students) {
            Object[] rowData = {student.getName(), student.getRollNumber(), student.getGrade()};
            tableModel.addRow(rowData);
        }

        // Update the ComboBox
        populateComboBox();

        // Clear the fields
        nameField.setText("");
        rollNumberField.setText("");
        gradeField.setText("");
    }

    private void populateComboBox() {
        studentComboBox.removeAllItems();
        for (Student student : students) {
            studentComboBox.addItem(student.getName());
        }
    }

    private void populateFields() {
        int selectedIndex = studentComboBox.getSelectedIndex();
        if (selectedIndex >= 0) {
            Student selectedStudent = students.get(selectedIndex);
            nameField.setText(selectedStudent.getName());
            rollNumberField.setText(String.valueOf(selectedStudent.getRollNumber()));
            gradeField.setText(String.valueOf(selectedStudent.getGrade()));
        }
    }
}

