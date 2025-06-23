package creacional;

import java.awt.*;
import javax.swing.*;

import java.util.*;

/**
 *
 * @author Fabio - Diego
 */
public class OverseasBuilder extends UIBuilder {

    private JTextField txtOrderAmount;
    private JTextField txtAdditionalSH;

    @Override
    public void addUIControls() {
        searchUI = new JPanel();
        JLabel lblOrderAmount = new JLabel("Order Amount:");
        txtOrderAmount = new JTextField(10);
        JLabel lblAdditionalSH = new JLabel("Additional S & H:");
        txtAdditionalSH = new JTextField(10);

        GridBagLayout gridbag = new GridBagLayout();
        searchUI.setLayout(gridbag);
        GridBagConstraints gbc = new GridBagConstraints();

        searchUI.add(lblOrderAmount);
        searchUI.add(txtOrderAmount);
        searchUI.add(lblAdditionalSH);
        searchUI.add(txtAdditionalSH);

        gbc.anchor = GridBagConstraints.WEST;

        gbc.insets.top = 5;
        gbc.insets.bottom = 5;
        gbc.insets.left = 5;
        gbc.insets.right = 5;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gridbag.setConstraints(lblOrderAmount, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gridbag.setConstraints(lblAdditionalSH, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gridbag.setConstraints(txtOrderAmount, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gridbag.setConstraints(txtAdditionalSH, gbc);

    }

    @Override
    public void initialize() {
        txtOrderAmount.setText("Enter Amount");
        txtAdditionalSH.setText("Enter S & H");
    }

    @Override
    public HashMap<String, String> getValues() {
        HashMap<String, String> values = new HashMap<String, String>();
        String strOrderAmount = txtOrderAmount.getText();
        String strSH = txtAdditionalSH.getText();

        values.put("orderAmount", strOrderAmount);
        values.put("additionalSH", strSH);

        return values;
    }

    @Override
    public void setValues(HashMap<String, String> values) {
        txtOrderAmount.setText(values.get("orderAmount"));
        txtAdditionalSH.setText(values.get("additionalSH"));
    }
}
