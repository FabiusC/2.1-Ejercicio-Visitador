package comportamental;

import javax.swing.JButton;
import tratamientoColecciones.OrderManager;
import tratamientoColecciones.OrderComposite;
import tratamientoColecciones.Order;
import tratamientoColecciones.CaliforniaOrder;
import tratamientoColecciones.NonCaliforniaOrder;
import tratamientoColecciones.OverseasOrder;
import tratamientoColecciones.BrazilianOrder;
import creacional.UIBuilder;
import creacional.BuilderFactory;
import creacional.UIDirector;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.HashMap;
import java.util.Iterator;

public class ModifyOrderButton extends JButton implements Command {
    private final OrderManager orderManager;
    private final OrderComposite orderComposite;
    private UIBuilder builder;
    private int numOrden = 0;

    public ModifyOrderButton(OrderManager orderManager, OrderComposite orderComposite) {
        super(OrderManager.MODIFY_ORDER);
        this.orderManager = orderManager;
        this.orderComposite = orderComposite;
        setEnabled(false);
    }

    public void setBuilder(UIBuilder builder) {
        this.builder = builder;
    }

    public void setNumOrden(int numOrden) {
        this.numOrden = numOrden;
    }

    @Override
    public void processEvent() {
        String op = JOptionPane.showInputDialog(orderManager, "Ingrese el ID de orden");
        if (op == null) {
            return;
        }
        numOrden = Integer.parseInt(op);
        Order orderToEdit = null;
        Iterator iterator = orderComposite.getItOrders();
        for (int i = 0; i < numOrden; i++) {
            if (iterator.hasNext()) {
                orderToEdit = (Order) iterator.next();
            } else {
                JOptionPane.showMessageDialog(null, "No existe el elemento", "Error", JOptionPane.ERROR_MESSAGE);
                orderToEdit = null;
            }
        }
        if (orderToEdit != null) {
            HashMap<String, String> values = new HashMap<>();
            if (orderToEdit.getClass().equals(CaliforniaOrder.class)) {
                setPanelTypeOrder(orderManager.CA_ORDER);
                orderManager.getOrderTypeCtrl().setSelectedItem(orderManager.CA_ORDER);
                CaliforniaOrder o = (CaliforniaOrder) orderToEdit;
                values.put("orderAmount", String.valueOf(o.getOrderAmount()));
                values.put("additionalTax", String.valueOf(o.getAdditionalTax()));
            }
            if (orderToEdit.getClass().equals(NonCaliforniaOrder.class)) {
                setPanelTypeOrder(orderManager.NON_CA_ORDER);
                orderManager.getOrderTypeCtrl().setSelectedItem(orderManager.NON_CA_ORDER);
                NonCaliforniaOrder o = (NonCaliforniaOrder) orderToEdit;
                values.put("orderAmount", String.valueOf(o.getOrderAmount()));
            }
            if (orderToEdit.getClass().equals(OverseasOrder.class)) {
                orderManager.getOrderTypeCtrl().setSelectedItem(orderManager.OVERSEAS_ORDER);
                setPanelTypeOrder(orderManager.OVERSEAS_ORDER);
                OverseasOrder o = (OverseasOrder) orderToEdit;
                values.put("orderAmount", String.valueOf(o.getOrderAmount()));
                values.put("additionalSH", String.valueOf(o.getAdditionalSH()));
            }
            if (orderToEdit.getClass().equals(BrazilianOrder.class)) {
                orderManager.getOrderTypeCtrl().setSelectedItem(orderManager.BZ_ORDER);
                setPanelTypeOrder(orderManager.BZ_ORDER);
                BrazilianOrder o = (BrazilianOrder) orderToEdit;
                values.put("orderAmount", String.valueOf(o.getOrderAmount()));
                values.put("additionalSH", String.valueOf(o.getAdditionalSH()));
            }
            builder.setValues(values);
            orderManager.getGetTotalButton().setEnabled(false);
            orderManager.getCreateOrderButton().setEnabled(false);
            orderManager.getModOrderButton().setEnabled(false);
            orderManager.getSaveOrderButton().setEnabled(true);
            orderManager.getRemoveButton().setEnabled(false);
            orderManager.getOrderTypeCtrl().setEnabled(false);
            orderManager.setSaveOrderNum(numOrden);
        }
    }

    private void setPanelTypeOrder(String selection) {
        BuilderFactory factory = new BuilderFactory();
        builder = factory.getUIBuilder(selection);
        UIDirector director = new UIDirector(builder);
        director.build();
        JPanel UIObj = builder.getSearchUI();
        orderManager.displayNewUI(UIObj);
    }
}