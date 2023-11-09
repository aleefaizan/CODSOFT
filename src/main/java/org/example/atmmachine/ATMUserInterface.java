package org.example.atmmachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMUserInterface extends JFrame {
    private ATM atm;
    private JTextField amountField;
    ImageIcon image = new ImageIcon("ATMImage.png");
    public ATMUserInterface() {
        BankAccount userAccount = new BankAccount(1000); // Initialize BankAccount with an initial balance
        atm = new ATM(userAccount);

        setTitle("ATM Machine");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(image.getImage());

        // Create buttons
        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton checkBalanceButton = new JButton("Check Balance");

        // Create a text field for user input
        amountField = new JTextField(10);

        // Add buttons, text field to the frame
        JPanel panel = new JPanel();
        panel.add(withdrawButton);
        panel.add(depositButton);
        panel.add(checkBalanceButton);
        panel.add(new JLabel("Amount:"));
        panel.add(amountField);

        add(panel);

        // Create action listeners for buttons
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amountToWithdraw = Double.parseDouble(amountField.getText());
                    atm.withdraw(amountToWithdraw);
                    JOptionPane.showMessageDialog(null, "Withdrawal successful.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid amount.");
                } catch (InsufficientFundsException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amountToDeposit = Double.parseDouble(amountField.getText());
                    atm.deposit(amountToDeposit);
                    JOptionPane.showMessageDialog(null, "Deposit successful.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid amount.");
                }
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double balance = atm.checkBalance();
                JOptionPane.showMessageDialog(null, "Your balance is: $" + balance);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ATMUserInterface atmUI = new ATMUserInterface();
            atmUI.setVisible(true);
        });
    }
}
