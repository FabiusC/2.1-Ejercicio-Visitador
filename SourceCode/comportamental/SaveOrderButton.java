package comportamental;

import javax.swing.JButton;
import tratamientoColecciones.OrderManager;
import tratamientoColecciones.OrderComposite;
import tratamientoColecciones.Order;
import tratamientoColecciones.OrderComponent;
import tratamientoColecciones.CaliforniaOrder;
import tratamientoColecciones.NonCaliforniaOrder;
import tratamientoColecciones.OverseasOrder;
import tratamientoColecciones.BrazilianOrder;
import creacional.UIBuilder;
import javax.swing.JOptionPane;
import java.util.HashMap;

public class SaveOrderButton extends JButton implements Command {
    private final OrderManager orderManager;
    private final OrderComposite orderComposite;
    private UIBuilder builder;
    private int numOrden = 0;

    public SaveOrderButton(OrderManager orderManager, OrderComposite orderComposite) {
        super(OrderManager.SAVE_ORDER);
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
        try {
            String orderType = orderManager.getOrderType();
            HashMap<String, String> newValues = builder.getValues();

            // Validar que los valores sean números válidos y positivos
            validateValues(newValues);

            Order order = createOrder(orderType, newValues);
            if (order == null) {
                JOptionPane.showMessageDialog(orderManager, "Error al crear la orden", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            order.accept(orderManager.getOrderVisitor());

            try {
                orderComposite.setComponent(numOrden - 1, (OrderComponent) order);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(orderManager, "Error al guardar la orden: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String totalParcialResult = String.valueOf(orderManager.getOrderVisitor().getOrderTotal());
            orderManager.setParcialValue(totalParcialResult);
            orderManager.getGetTotalButton().setEnabled(true);
            orderManager.getCreateOrderButton().setEnabled(true);
            orderManager.getModOrderButton().setEnabled(true);
            orderManager.getSaveOrderButton().setEnabled(false);
            orderManager.getRemoveButton().setEnabled(true);
            orderManager.getOrderTypeCtrl().setEnabled(true);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(orderManager, "Error: Los valores deben ser números válidos",
                    "Error de formato", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(orderManager, "Error: " + e.getMessage(), "Error de validación",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(orderManager, "Error inesperado: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void validateValues(HashMap<String, String> values) {
        for (String key : values.keySet()) {
            String value = values.get(key);
            if (value == null || value.trim().isEmpty()) {
                throw new IllegalArgumentException("El campo '" + key + "' no puede estar vacío");
            }

            try {
                double numValue = Double.parseDouble(value);
                if (numValue < 0) {
                    throw new IllegalArgumentException("El campo '" + key + "' no puede ser negativo");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("El campo '" + key + "' debe ser un número válido");
            }
        }
    }

    private Order createOrder(String orderType, HashMap<String, String> values) {
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
}