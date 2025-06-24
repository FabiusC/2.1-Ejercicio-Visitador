package creacional;

import java.awt.*;
import java.util.HashMap;

import javax.swing.*;

/**
 *
 * @author Fabio - Diego
 */
public class CANADABuilder extends UIBuilder {

    private JTextField txtOrderAmount;

    @Override
    public void addUIControls() {
        searchUI = new JPanel();
        JLabel lblOrderAmount = new JLabel("Order Amount:");
        txtOrderAmount = new JTextField(10);

        GridBagLayout gridbag = new GridBagLayout();

        searchUI.setLayout(gridbag);
        GridBagConstraints gbc = new GridBagConstraints();

        searchUI.add(lblOrderAmount);

        searchUI.add(txtOrderAmount);

        gbc.anchor = GridBagConstraints.WEST;

        gbc.insets.top = 5;
        gbc.insets.bottom = 5;
        gbc.insets.left = 5;
        gbc.insets.right = 5;

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 0;

        gridbag.setConstraints(txtOrderAmount, gbc);


    }

    @Override
    public void initialize() {
        txtOrderAmount.setText("Enter Amount");

    }

    @Override
    public HashMap<String, String> getValues() {
        HashMap<String, String> values = new HashMap<String, String>();
        String strOrderAmount = txtOrderAmount.getText();


        values.put("orderAmount", strOrderAmount);


        return values;
    }

    @Override
    public void setValues(HashMap<String, String> values) {
        txtOrderAmount.setText(values.get("orderAmount"));

    }
}
