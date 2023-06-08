/*
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.*;

public class CurrencyConveter extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private JLabel amountLabel;
    private JTextField amountTextField;
    private JLabel currencyLabel;
    private JComboBox<String> currencyComboBox;
    private JButton convertButton;
    private JLabel resultLabel;

    private double USDToEUR = 0.83;
    private double USDToJPY = 109.61;
    private double USDToGBP = 0.72;

    public CurrencyConveter() {
        super("Currency Converter");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        amountLabel = new JLabel("Amount in USD:");
        panel.add(amountLabel);

        amountTextField = new JTextField();
        panel.add(amountTextField);

        currencyLabel = new JLabel("Currency:");
        panel.add(currencyLabel);

        String[] currencies = { "EUR", "JPY", "GBP" };
        currencyComboBox = new JComboBox<String>(currencies);
        panel.add(currencyComboBox);

        convertButton = new JButton("Convert");
        convertButton.addActionListener(this);
        panel.add(convertButton);

        resultLabel = new JLabel("");
        panel.add(resultLabel);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String usdAmountString = amountTextField.getText();
        String currency = (String) currencyComboBox.getSelectedItem();

        if (usdAmountString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an amount in USD.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double usdAmount = Double.parseDouble(usdAmountString);
        double convertedAmount = 0.0;

        if (currency.equalsIgnoreCase("EUR")) {
            convertedAmount = usdAmount * USDToEUR;
        } else if (currency.equalsIgnoreCase("JPY")) {
            convertedAmount = usdAmount * USDToJPY;
        } else if (currency.equalsIgnoreCase("GBP")) {
            convertedAmount = usdAmount * USDToGBP;
        } else {
            JOptionPane.showMessageDialog(this, "Invalid currency entered.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String formattedAmount = NumberFormat.getCurrencyInstance().format(convertedAmount);
        resultLabel.setText(formattedAmount);
    }

    public static void main(String[] args) {
        new CurrencyConveter();
    }
} */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrencyConveter extends JFrame {
    private JLabel amountLabel;
    private JTextField amountTextField;
    private JLabel fromLabel;
    private JComboBox<String> fromComboBox;
    private JLabel toLabel;
    private JComboBox<String> toComboBox;
    private JButton convertButton;
    private JLabel resultLabel;

    private static final String[] currencies = {"USD", "EUR", "GBP", "JPY"};

    public CurrencyConveter() {
        setTitle("Currency Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        amountLabel = new JLabel("Amount (USD):");
        amountTextField = new JTextField(10);
        fromLabel = new JLabel("From Currency:");
        fromComboBox = new JComboBox<>(currencies);
        toLabel = new JLabel("To Currency:");
        toComboBox = new JComboBox<>(currencies);
        convertButton = new JButton("Convert");
        resultLabel = new JLabel();

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountTextField.getText());
                String fromCurrency = (String) fromComboBox.getSelectedItem();
                String toCurrency = (String) toComboBox.getSelectedItem();

                double convertedAmount = convertCurrency(amount, fromCurrency, toCurrency);

                resultLabel.setText(String.format("%.2f %s = %.2f %s", amount, fromCurrency, convertedAmount, toCurrency));
            }
        });

        add(amountLabel);
        add(amountTextField);
        add(fromLabel);
        add(fromComboBox);
        add(toLabel);
        add(toComboBox);
        add(convertButton);
        add(resultLabel);

        pack();
        setLocationRelativeTo(null);
    }

    private double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        double usdToEur = 0.85;
        double usdToGbp = 0.72;
        double usdToJpy = 109.67;

        double rateFrom;
        double rateTo;

        switch (fromCurrency) {
            case "USD":
                rateFrom = 1.0;
                break;
            case "EUR":
                rateFrom = usdToEur;
                break;
            case "GBP":
                rateFrom = usdToGbp;
                break;
            case "JPY":
                rateFrom = usdToJpy;
                break;
            default:
                rateFrom = 1.0;
        }

        switch (toCurrency) {
            case "USD":
                rateTo = 1.0;
                break;
            case "EUR":
                rateTo = usdToEur;
                break;
            case "GBP":
                rateTo = usdToGbp;
                break;
            case "JPY":
                rateTo = usdToJpy;
                break;
            default:
                rateTo = 1.0;
        }

        return (amount / rateFrom) * rateTo;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CurrencyConveter().setVisible(true);
            }
        });
    }
}
