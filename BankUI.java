import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BankUI extends JFrame {
    private BankAccount account;
    private JTextField amountField;
    private JLabel balanceLabel;

    public BankUI() {
        account = new BankAccount();

        setTitle(" Simple Bank App");
        setSize(500, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(245, 245, 245));

        JLabel title = new JLabel("Simple Bank Management");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setBounds(120, 20, 300, 30);
        add(title);

        JLabel label = new JLabel("Enter Amount:");
        label.setFont(new Font("SansSerif", Font.PLAIN, 16));
        label.setBounds(50, 80, 150, 25);
        add(label);

        amountField = new JTextField();
        amountField.setBounds(180, 80, 200, 30);
        amountField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(amountField);

        JButton depositButton = new JButton("Deposit");
        styleButton(depositButton, 50, 140);
        depositButton.addActionListener(e -> depositAction());
        add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        styleButton(withdrawButton, 180, 140);
        withdrawButton.addActionListener(e -> withdrawAction());
        add(withdrawButton);

        JButton balanceButton = new JButton("Check Balance");
        styleButton(balanceButton, 310, 140);
        balanceButton.addActionListener(e -> updateBalance());
        add(balanceButton);

        balanceLabel = new JLabel("Balance: ₹0.00");
        balanceLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        balanceLabel.setForeground(new Color(0, 102, 0));
        balanceLabel.setBounds(50, 200, 300, 40);
        add(balanceLabel);

        setVisible(true);
    }

    private void styleButton(JButton button, int x, int y) {
        button.setBounds(x, y, 120, 35);
        button.setBackground(new Color(0, 123, 255));
        button.setForeground(Color.white);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBorder(BorderFactory.createLineBorder(new Color(0, 80, 180), 1));
    }

    private void depositAction() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            account.deposit(amount);
            updateBalance();
            amountField.setText("");
        } catch (NumberFormatException e) {
            showError("Please enter a valid number.");
        }
    }

    private void withdrawAction() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            boolean success = account.withdraw(amount);
            if (!success) showError("Not enough balance!");
            updateBalance();
            amountField.setText("");
        } catch (NumberFormatException e) {
            showError("Please enter a valid number.");
        }
    }

    private void updateBalance() {
        balanceLabel.setText("Balance: ₹" + String.format("%.2f", account.getBalance()));
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
