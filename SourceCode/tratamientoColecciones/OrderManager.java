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

    private JComboBox cmbOrderType;
    private JPanel pSearchCriteria;

    private JButton getTotalButton, createOrderButton;
    private JButton modOrderButton, saveOrderButton, exitButton, removeButton;

    private JLabel lblOrderType;
    private JLabel lblParcial, lblParcialValue;
    private JLabel lblTotal, lblTotalValue;

    private OrderVisitor objVisitor;

    public OrderManager() {
        super("Visitor Pattern Puntual Exercise");

        objVisitor = new OrderVisitor();

        cmbOrderType = new JComboBox();
        cmbOrderType.addItem(OrderManager.NULL);
        cmbOrderType.addItem(OrderManager.CA_ORDER);
        cmbOrderType.addItem(OrderManager.NON_CA_ORDER);
        cmbOrderType.addItem(OrderManager.OVERSEAS_ORDER);
        cmbOrderType.addItem(OrderManager.BZ_ORDER);

        pSearchCriteria = new JPanel();

        lblOrderType = new JLabel("Order Type:");

        lblParcial = new JLabel("Result Parcial:");
        lblParcialValue = new JLabel("Click Create Order Button");

        lblTotal = new JLabel("Result Total:");
        lblTotalValue = new JLabel("Click GetTotal Button");

        // Create the open button
        getTotalButton = new JButton(OrderManager.GET_TOTAL);
        getTotalButton.setMnemonic(KeyEvent.VK_G);
        getTotalButton.setEnabled(false);

        createOrderButton = new JButton(OrderManager.CREATE_ORDER);
        createOrderButton.setMnemonic(KeyEvent.VK_C);// getTotal?
        createOrderButton.setEnabled(false);

        modOrderButton = new JButton(OrderManager.MODIFY_ORDER);
        modOrderButton.setEnabled(false);

        removeButton = new JButton(OrderManager.REMOVE_ORDER);
        removeButton.setEnabled(false);

        saveOrderButton = new JButton(OrderManager.SAVE_ORDER);
        saveOrderButton.setEnabled(false);

        exitButton = new JButton(OrderManager.EXIT);
        exitButton.setMnemonic(KeyEvent.VK_X);

        ButtonHandler objButtonHandler = new ButtonHandler(this);

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

    public JComboBox getOrderTypeCtrl() {
        return cmbOrderType;
    }

    public void displayNewUI(JPanel panel) {
        pSearchCriteria.removeAll();
        pSearchCriteria.add(panel);
        pSearchCriteria.validate();
        validate();
    }

    public JButton getGetTotalButton() {
        return getTotalButton;
    }

    public JButton getCreateOrderButton() {
        return createOrderButton;
    }

    public JButton getModOrderButton() {
        return modOrderButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public JButton getSaveOrderButton() {
        return saveOrderButton;
    }

}// End of class OrderManager

class ButtonHandler implements ActionListener {

    OrderManager objOrderManager;
    UIBuilder builder;
    JPanel UIObj;
    OrderComposite objOrderComp = new OrderComposite();
    int numOrden = 0;

    public void actionPerformed(ActionEvent e) {
        String totalParcialResult = null;

        if (e.getActionCommand().equals(OrderManager.EXIT)) {
            System.exit(1);
        }
        // ****************************************************
        if (e.getSource() == objOrderManager.getOrderTypeCtrl()) {
            String selection = objOrderManager.getOrderType();
            if (selection.equals(OrderManager.NULL) == false) {
                setPanelTypeOrder(selection);
                objOrderManager.getGetTotalButton().setEnabled(true);
                objOrderManager.getCreateOrderButton().setEnabled(true);
                objOrderManager.getModOrderButton().setEnabled(true);
                objOrderManager.getRemoveButton().setEnabled(true);
            } else {
                objOrderManager.getGetTotalButton().setEnabled(false);
                objOrderManager.getCreateOrderButton().setEnabled(false);
                objOrderManager.getModOrderButton().setEnabled(false);
                objOrderManager.getRemoveButton().setEnabled(false);
                objOrderManager.displayNewUI(new JPanel());
            }

        }
        // ****************************************************
        if (e.getActionCommand().equals(OrderManager.CREATE_ORDER)) {

            String orderType = objOrderManager.getOrderType();

            // get input values
            HashMap values = builder.getValues();
            Order order = createOrder(orderType, values);

            // Get the Visitor (instantiate)
            OrderVisitor visitor = objOrderManager.getOrderVisitor();

            // accept the visitor instance
            order.accept(visitor);
            try {
                objOrderComp.addComponent((OrderComponent) order);
            } catch (Exception ex) {
                System.out.println("Error AddComponent" + ex);
            }

            totalParcialResult = String.valueOf(visitor.getOrderTotal());
            objOrderManager.setParcialValue(totalParcialResult);

        }

        if (e.getActionCommand().equals(OrderManager.REMOVE_ORDER)) {
            String op = JOptionPane.showInputDialog(objOrderManager, "Ingrese el ID de orden");

            if (op == null) {
            } else {
                numOrden = Integer.parseInt(op);
                Order orderToEdit = null;

                Iterator iterator = objOrderComp.getItOrders();

                for (int i = 0; i < numOrden; i++) {
                    if (iterator.hasNext()) {

                        orderToEdit = (Order) iterator.next();

                    } else {
                        JOptionPane.showMessageDialog(null, "No existe el elemento", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        orderToEdit = null;
                    }
                }

                if (orderToEdit != null) {
                    try {
                        objOrderComp.removeComponent(numOrden - 1);
                    } catch (Exception ex) {
                        System.out.println("Error");
                    }
                }
            }
        }

        // ****************************************************
        if (e.getActionCommand().equals(OrderManager.MODIFY_ORDER)) {

            String op = JOptionPane.showInputDialog(objOrderManager, "Ingrese el ID de orden");

            if (op == null) {
            } else {
                numOrden = Integer.parseInt(op);
                Order orderToEdit = null;

                Iterator iterator = objOrderComp.getItOrders();

                for (int i = 0; i < numOrden; i++) {
                    if (iterator.hasNext()) {

                        orderToEdit = (Order) iterator.next();

                    } else {
                        JOptionPane.showMessageDialog(null, "No existe el elemento", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        orderToEdit = null;
                    }
                }

                if (orderToEdit != null) {
                    HashMap<String, String> values = new HashMap<>();
                    if (orderToEdit.getClass().equals(CaliforniaOrder.class)) {
                        setPanelTypeOrder(objOrderManager.CA_ORDER);
                        objOrderManager.getOrderTypeCtrl().setSelectedItem(objOrderManager.CA_ORDER);

                        CaliforniaOrder o = (CaliforniaOrder) orderToEdit;

                        values.put("orderAmount", String.valueOf(o.getOrderAmount()));
                        values.put("additionalTax", String.valueOf(o.getAdditionalTax()));

                    }
                    if (orderToEdit.getClass().equals(NonCaliforniaOrder.class)) {
                        setPanelTypeOrder(objOrderManager.NON_CA_ORDER);
                        objOrderManager.getOrderTypeCtrl().setSelectedItem(objOrderManager.NON_CA_ORDER);

                        NonCaliforniaOrder o = (NonCaliforniaOrder) orderToEdit;

                        values.put("orderAmount", String.valueOf(o.getOrderAmount()));

                    }
                    if (orderToEdit.getClass().equals(OverseasOrder.class)) {
                        objOrderManager.getOrderTypeCtrl().setSelectedItem(objOrderManager.OVERSEAS_ORDER);
                        setPanelTypeOrder(objOrderManager.OVERSEAS_ORDER);

                        OverseasOrder o = (OverseasOrder) orderToEdit;

                        values.put("orderAmount", String.valueOf(o.getOrderAmount()));
                        values.put("additionalSH", String.valueOf(o.getAdditionalSH()));
                    }
                    if (orderToEdit.getClass().equals(BrazilianOrder.class)) {
                        objOrderManager.getOrderTypeCtrl().setSelectedItem(objOrderManager.BZ_ORDER);
                        setPanelTypeOrder(objOrderManager.BZ_ORDER);

                        BrazilianOrder o = (BrazilianOrder) orderToEdit;

                        values.put("orderAmount", String.valueOf(o.getOrderAmount()));
                        values.put("additionalSH", String.valueOf(o.getAdditionalSH()));
                    }
                    builder.setValues(values);

                    objOrderManager.getGetTotalButton().setEnabled(false);
                    objOrderManager.getCreateOrderButton().setEnabled(false);
                    objOrderManager.getModOrderButton().setEnabled(false);
                    objOrderManager.getSaveOrderButton().setEnabled(true);
                    objOrderManager.getRemoveButton().setEnabled(false);
                    objOrderManager.getOrderTypeCtrl().setEnabled(false);
                }

            }
        }

        // ****************************************************
        if (e.getActionCommand().equals(OrderManager.SAVE_ORDER)) {

            String orderType = objOrderManager.getOrderType();
            HashMap newValues = builder.getValues();
            Order order = createOrder(orderType, newValues);

            OrderVisitor visitor = objOrderManager.getOrderVisitor();
            order.accept(visitor);

            try {
                objOrderComp.setComponent(numOrden - 1, (OrderComponent) order);
            } catch (Exception ex) {
                System.out.println("Error");
            }

            totalParcialResult = String.valueOf(visitor.getOrderTotal());

            objOrderManager.setParcialValue(totalParcialResult);
            objOrderManager.getGetTotalButton().setEnabled(true);
            objOrderManager.getCreateOrderButton().setEnabled(true);
            objOrderManager.getModOrderButton().setEnabled(true);
            objOrderManager.getSaveOrderButton().setEnabled(false);
            objOrderManager.getRemoveButton().setEnabled(true);
            objOrderManager.getOrderTypeCtrl().setEnabled(true);
        }

        // ****************************************************
        if (e.getActionCommand().equals(OrderManager.GET_TOTAL)) {
            String total = String.valueOf(objOrderComp.getTotal());
            objOrderManager.setTotalValue(total);
            JOptionPane.showMessageDialog(null, objOrderComp.getInfo(), "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void setPanelTypeOrder(String selection) {
        BuilderFactory factory = new BuilderFactory();

        builder = factory.getUIBuilder(selection);

        UIDirector director = new UIDirector(builder);

        director.build();

        UIObj = builder.getSearchUI();
        objOrderManager.displayNewUI(UIObj);
    }

    public Order createOrder(String orderType, HashMap<String, String> values) {
        if (orderType.equalsIgnoreCase(OrderManager.CA_ORDER)) {
            Double orderAmount = Double.parseDouble(values.get("orderAmount"));
            Double Tax = Double.parseDouble(values.get("additionalTax"));
            return new CaliforniaOrder(orderAmount, Tax);
        }
        if (orderType.equalsIgnoreCase(OrderManager.NON_CA_ORDER)) {
            Double orderAmount = Double.parseDouble(values.get("orderAmount"));
            return new NonCaliforniaOrder(orderAmount);
        }
        if (orderType.equalsIgnoreCase(OrderManager.OVERSEAS_ORDER)) {
            Double orderAmount = Double.parseDouble(values.get("orderAmount"));
            Double SH = Double.parseDouble(values.get("additionalSH"));
            return new OverseasOrder(orderAmount, SH);
        }
        if (orderType.equalsIgnoreCase(OrderManager.BZ_ORDER)) {
            Double orderAmount = Double.parseDouble(values.get("orderAmount"));
            Double SH = Double.parseDouble(values.get("additionalSH"));
            return new BrazilianOrder(orderAmount, SH);
        }
        return null;
    }

    public ButtonHandler() {
    }

    public ButtonHandler(OrderManager inObjOrderManager) {
        objOrderManager = inObjOrderManager;
    }
}
