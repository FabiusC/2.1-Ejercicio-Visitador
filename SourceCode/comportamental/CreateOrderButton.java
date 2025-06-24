package comportamental;

import javax.swing.JButton;
import tratamientoColecciones.OrderManager;
import tratamientoColecciones.OrderComposite;
import tratamientoColecciones.Order;
import tratamientoColecciones.OrderVisitor;
import tratamientoColecciones.OrderComponent;
import tratamientoColecciones.CaliforniaOrder;
import tratamientoColecciones.NonCaliforniaOrder;
import tratamientoColecciones.OverseasOrder;
import tratamientoColecciones.CanadianOrder;
import creacional.UIBuilder;
import javax.swing.JOptionPane;
import java.util.HashMap;

public class CreateOrderButton extends JButton implements Command {
    private final OrderManager orderManager;
    private final OrderComposite orderComposite;
    private UIBuilder builder;

    public CreateOrderButton(OrderManager orderManager, OrderComposite orderComposite) {
        super(OrderManager.CREATE_ORDER);
        this.orderManager = orderManager;
        this.orderComposite = orderComposite;
        setMnemonic('C');
        setEnabled(false);
    }

    public void setBuilder(UIBuilder builder) {
        this.builder = builder;
    }

    @Override
    public void processEvent() {
        try {
            String orderType = orderManager.getOrderType();
            HashMap<String, String> values = builder.getValues();

            // Validar que los valores sean números válidos y positivos
            validateValues(values);

            Order order = createOrder(orderType, values);
            if (order == null) {
                JOptionPane.showMessageDialog(orderManager, "Error al crear la orden", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            OrderVisitor visitor = orderManager.getOrderVisitor();
            order.accept(visitor);
            try {
                orderComposite.addComponent((OrderComponent) order);
            } catch (Exception ex) {
                System.out.println("Error AddComponent" + ex);
            }
            String totalParcialResult = String.valueOf(visitor.getOrderTotal());
            orderManager.setParcialValue(totalParcialResult);

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
        if (orderType.equalsIgnoreCase(OrderManager.CANADA_ORDER)) {
            Double orderAmount = Double.parseDouble(values.get("orderAmount"));
            return new CanadianOrder(orderAmount);
        }
        return null;
    }
}