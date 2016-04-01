package com.conor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by conor on 3/31/16.
 *
 */
public class CCValidator extends JFrame{
    private JPanel rootPanel;
    private JTextField creditCardNumberTextField;
    private JButton validateButton;
    private JLabel validMessageLabel;
    private JButton quitButton;

    public CCValidator() {
        super("Credit Card Validator");
        setContentPane(rootPanel);
        validMessageLabel.setVisible(false);
        setPreferredSize(new Dimension(400, 300)); //Set size of panel
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validMessageLabel.setVisible(true);
                String ccNumber = creditCardNumberTextField.getText();
                boolean valid = isVisaCreditCardNumberValid(ccNumber); //Check to see if card is valid from method
                if (valid) {
                    validMessageLabel.setText("Credit card number is valid");
                } else {
                    validMessageLabel.setText("Credit card number is NOT valid!");
                }
            }
        });
        //Close the GUI
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
        //Method to check if the card is valid
        private static boolean isVisaCreditCardNumberValid(String ccNumber) {

            if (!ccNumber.startsWith("4") || (ccNumber.length() != 16)){
                System.out.println("Doesnt start with 4 or length wrong");
                return false;
            }

            int sum = 0;

            for (int i = 0; i < 16 ; i++ ) {
                int thisDigit = Integer.parseInt((ccNumber.substring(i, i+1)));
                if (i % 2 == 1) {
                    sum = sum + thisDigit;
                } else {
                    int doubled = thisDigit * 2;
                    if (doubled > 9 ) {
                        int toAdd = 1 + (doubled % 10);
                        sum = sum + toAdd;
                    } else {
                        sum = sum + (thisDigit * 2);
                    }
                }
            }

            if (sum % 10 == 0) {
                return true;
            }

            return false;

        }


}
