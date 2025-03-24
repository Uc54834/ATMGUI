import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMGUI extends JFrame {
    private double balance = 1000.0; // Initial balance
    private JTextField amountField;
    private JTextArea displayArea;

    public ATMGUI() {
        setTitle("ATM Machine");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField(10);
        JButton checkBalanceButton = new JButton("Check Balance");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);

        // Set layout
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Add components to the panel
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(checkBalanceButton);
        panel.add(depositButton);
        panel.add(withdrawButton);

        // Add panel and display area to the frame
        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Add action listeners
        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });
    }

    private void checkBalance() {
        displayArea.setText("Your current balance is: $" + balance);
    }

    private void deposit() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount > 0) {
                balance += amount;
                displayArea.setText("$" + amount + " deposited successfully.\nNew balance: $" + balance);
            } else {
                displayArea.setText("Invalid amount. Please enter a positive value.");
            }
        } catch (NumberFormatException ex) {
            displayArea.setText("Invalid input. Please enter a numeric value.");
        }
    }

    private void withdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                displayArea.setText("$" + amount + " withdrawn successfully.\nNew balance: $" + balance);
            } else if (amount > balance) {
                displayArea.setText("Insufficient funds. Your balance is $" + balance);
            } else {
                displayArea.setText("Invalid amount. Please enter a positive value.");
            }
        } catch (NumberFormatException ex) {
            displayArea.setText("Invalid input. Please enter a numeric value.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ATMGUI().setVisible(true);
            }
        });
    }
}