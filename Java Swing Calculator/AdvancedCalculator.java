import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdvancedCalculator extends JFrame implements ActionListener {
    private JTextField textField;
    private JTextArea historyArea;
    private double num1, num2, result;
    private char operator;
    private boolean isDarkMode = true; // Default theme

    public AdvancedCalculator() {
        setTitle("Advanced Calculator");
        setSize(400, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create UI Components
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.BOLD, 24));
        textField.setHorizontalAlignment(JTextField.RIGHT);

        historyArea = new JTextArea();
        historyArea.setFont(new Font("Arial", Font.PLAIN, 14));
        historyArea.setEditable(false);
        JScrollPane historyScroll = new JScrollPane(historyArea);
        historyScroll.setPreferredSize(new Dimension(400, 100));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 4, 5, 5));

        String[] buttons = {
                "7", "8", "9", "/", "√",
                "4", "5", "6", "*", "%",
                "1", "2", "3", "-", "Del",
                "0", ".", "=", "+", "C",
                "Light/Dark Mode"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(this);
            panel.add(button);
        }

        add(textField, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(historyScroll, BorderLayout.SOUTH);

        applyDarkMode(); // Apply default theme
        setVisible(true);
    }

    private void applyDarkMode() {
        Color bgColor = isDarkMode ? new Color(45, 45, 45) : Color.WHITE;
        Color btnColor = isDarkMode ? new Color(70, 70, 70) : new Color(220, 220, 220);
        Color textColor = isDarkMode ? Color.WHITE : Color.BLACK;

        textField.setBackground(bgColor);
        textField.setForeground(textColor);
        historyArea.setBackground(bgColor);
        historyArea.setForeground(textColor);

        for (Component c : getContentPane().getComponents()) {
            if (c instanceof JPanel) {
                c.setBackground(bgColor);
                for (Component btn : ((JPanel) c).getComponents()) {
                    if (btn instanceof JButton) {
                        btn.setBackground(btnColor);
                        btn.setForeground(textColor);
                    }
                }
            }
        }

        getContentPane().setBackground(bgColor);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9.]")) {
            textField.setText(textField.getText() + command);
        } else if (command.equals("C")) {
            textField.setText("");
        } else if (command.equals("Del")) {
            String text = textField.getText();
            if (!text.isEmpty()) {
                textField.setText(text.substring(0, text.length() - 1));
            }
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(textField.getText());
            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/': result = num2 != 0 ? num1 / num2 : Double.NaN; break;
            }
            textField.setText(String.valueOf(result));
            historyArea.append(num1 + " " + operator + " " + num2 + " = " + result + "\n");
        } else if (command.equals("√")) {
            num1 = Double.parseDouble(textField.getText());
            textField.setText(String.valueOf(Math.sqrt(num1)));
            historyArea.append("√" + num1 + " = " + textField.getText() + "\n");
        } else if (command.equals("%")) {
            num1 = Double.parseDouble(textField.getText());
            textField.setText(String.valueOf(num1 / 100));
            historyArea.append(num1 + "% = " + textField.getText() + "\n");
        } else if (command.equals("Light/Dark Mode")) {
            isDarkMode = !isDarkMode;
            applyDarkMode();
        } else {
            num1 = Double.parseDouble(textField.getText());
            operator = command.charAt(0);
            textField.setText("");
        }
    }

    public static void main(String[] args) {
        new AdvancedCalculator();
    }
}





           

           

