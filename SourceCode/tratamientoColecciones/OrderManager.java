package tratamientoColecciones;

import creacional.NONCABuilder;
import creacional.UIBuilder;
import creacional.OverseasBuilder;
import creacional.BZBuilder;
import creacional.BuilderFactory;
import creacional.CABuilder;
import creacional.UIDirector;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;

import javax.swing.*;

import java.util.HashMap;

import comportamental.GetTotalButton;
import comportamental.CreateOrderButton;
import comportamental.ModifyOrderButton;
import comportamental.RemoveOrderButton;
import comportamental.SaveOrderButton;
import comportamental.ExitButton;
import comportamental.OrderTypeComboBox;
import comportamental.Command;

/**
 *
 * @author Fabio - Diego
 */
public class OrderManager extends JFrame {

    public static final String NULL = "";
    public static final String CA_ORDER = "California Order";
    public static final String NON_CA_ORDER = "Non-California Order";
    public static final String OVERSEAS_ORDER = "Overseas Order";
    public static final String BZ_ORDER = "Brazilian Order";

    public static final String GET_TOTAL = "Get Total";
    public static final String CREATE_ORDER = "Create Order";
    public static final String MODIFY_ORDER = "Modify Order";
    public static final String SAVE_ORDER = "Save Order";
    public static final String REMOVE_ORDER = "Remove Order";
    public static final String EXIT = "Exit";

    private OrderTypeComboBox cmbOrderType;
    private JPanel pSearchCriteria;

    private GetTotalButton getTotalButton;
    private CreateOrderButton createOrderButton;
    private ModifyOrderButton modOrderButton;
    private SaveOrderButton saveOrderButton;
    private ExitButton exitButton;
    private RemoveOrderButton removeButton;

    private JLabel lblOrderType;
    private JLabel lblParcial, lblParcialValue;
    private JLabel lblTotal, lblTotalValue;

    private OrderVisitor objVisitor;
    private OrderComposite objOrderComp = new OrderComposite();

    public OrderManager() {
        super("Visitor Pattern Puntual Exercise");

        objVisitor = new OrderVisitor();

        cmbOrderType = new OrderTypeComboBox(this);

        pSearchCriteria = new JPanel();

        lblOrderType = new JLabel("Order Type:");

        lblParcial = new JLabel("Result Parcial:");
        lblParcialValue = new JLabel("Click Create Order Button");

        lblTotal = new JLabel("Result Total:");
        lblTotalValue = new JLabel("Click GetTotal Button");

        getTotalButton = new GetTotalButton(this, objOrderComp);
        createOrderButton = new CreateOrderButton(this, objOrderComp);
        modOrderButton = new ModifyOrderButton(this, objOrderComp);
        removeButton = new RemoveOrderButton(this, objOrderComp);
        saveOrderButton = new SaveOrderButton(this, objOrderComp);
        exitButton = new ExitButton();

        ButtonHandler objButtonHandler = new ButtonHandler();

        getTotalButton.addActionListener(objButtonHandler);
        createOrderButton.addActionListener(objButtonHandler);
        modOrderButton.addActionListener(objButtonHandler);
        removeButton.addActionListener(objButtonHandler);
        saveOrderButton.addActionListener(objButtonHandler);
        cmbOrderType.addActionListener(objButtonHandler);
        exitButton.addActionListener(objButtonHandler);

        // For layout purposes, put the buttons in a separate panel
        JPanel panel = new JPanel();

        GridBagLayout gridbag2 = new GridBagLayout();
        panel.setLayout(gridbag2);

        GridBagConstraints gbc2 = new GridBagConstraints();

        panel.add(getTotalButton);
        panel.add(createOrderButton);
        panel.add(modOrderButton);
        panel.add(removeButton);
        panel.add(saveOrderButton);
        panel.add(exitButton);

        gbc2.anchor = GridBagConstraints.CENTER;
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gridbag2.setConstraints(createOrderButton, gbc2);
        gbc2.gridx = 1;
        gbc2.gridy = 0;
        gridbag2.setConstraints(modOrderButton, gbc2);
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gridbag2.setConstraints(getTotalButton, gbc2);
        gbc2.gridx = 1;
        gbc2.gridy = 1;
        gridbag2.setConstraints(saveOrderButton, gbc2);
        gbc2.gridx = 0;
        gbc2.gridy = 2;
        gridbag2.setConstraints(exitButton, gbc2);

        // ****************************************************
        JPanel buttonPanel = new JPanel();
        GridBagLayout gridbag = new GridBagLayout();
        buttonPanel.setLayout(gridbag);
        GridBagConstraints gbc = new GridBagConstraints();
        buttonPanel.add(lblOrderType);
        buttonPanel.add(cmbOrderType);
        buttonPanel.add(pSearchCriteria);

        buttonPanel.add(lblParcial);
        buttonPanel.add(lblParcialValue);
        buttonPanel.add(lblTotal);
        buttonPanel.add(lblTotalValue);

        gbc.insets.top = 5;
        gbc.insets.bottom = 5;
        gbc.insets.left = 5;
        gbc.insets.right = 5;

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gridbag.setConstraints(lblOrderType, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gridbag.setConstraints(cmbOrderType, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gridbag.setConstraints(pSearchCriteria, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gridbag.setConstraints(lblParcial, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gridbag.setConstraints(lblParcialValue, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gridbag.setConstraints(lblTotal, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 3;
        gridbag.setConstraints(lblTotalValue, gbc);

        gbc.insets.left = 2;
        gbc.insets.right = 2;
        gbc.insets.top = 40;

        // ****************************************************
        // Add the buttons and the log to the frame
        Container contentPane = getContentPane();

        contentPane.add(buttonPanel, BorderLayout.NORTH);
        contentPane.add(panel, BorderLayout.CENTER);
        try {
            // UIManager.setLookAndFeel(new WindowsLookAndFeel());
            SwingUtilities.updateComponentTreeUI(OrderManager.this);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new OrderManager();

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // frame.pack();
        frame.setSize(500, 400);
        frame.setVisible(true);
    }

    public void setTotalValue(String msg) {
        lblTotalValue.setText(msg);
    }

    public void setParcialValue(String msg) {
        lblParcialValue.setText(msg);
    }

    public OrderVisitor getOrderVisitor() {
        return objVisitor;
    }

    public String getOrderType() {
        return (String) cmbOrderType.getSelectedItem();
    }

    public OrderTypeComboBox getOrderTypeCtrl() {
        return cmbOrderType;
    }

    public void displayNewUI(JPanel panel) {
        pSearchCriteria.removeAll();
        pSearchCriteria.add(panel);
        pSearchCriteria.validate();
        validate();
    }

    public GetTotalButton getGetTotalButton() {
        return getTotalButton;
    }

    public CreateOrderButton getCreateOrderButton() {
        return createOrderButton;
    }

    public ModifyOrderButton getModOrderButton() {
        return modOrderButton;
    }

    public RemoveOrderButton getRemoveButton() {
        return removeButton;
    }

    public SaveOrderButton getSaveOrderButton() {
        return saveOrderButton;
    }

    public void configureButtons(UIBuilder builder) {
        createOrderButton.setBuilder(builder);
        modOrderButton.setBuilder(builder);
        saveOrderButton.setBuilder(builder);
        cmbOrderType.setBuilder(builder);
    }

    public void setSaveOrderNum(int numOrden) {
        saveOrderButton.setNumOrden(numOrden);
    }

    public void setModifyOrderNum(int numOrden) {
        modOrderButton.setNumOrden(numOrden);
    }

}// End of class OrderManager

class ButtonHandler implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        Command commandObj = (Command) e.getSource();
        commandObj.processEvent();
    }
}
