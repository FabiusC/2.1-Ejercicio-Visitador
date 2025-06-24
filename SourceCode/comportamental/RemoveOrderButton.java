package comportamental;

import javax.swing.*;

import tratamientoColecciones.OrderManager;
import tratamientoColecciones.OrderComposite;
import tratamientoColecciones.Order;

import java.awt.*;
import java.util.Iterator;

public class RemoveOrderButton extends JButton implements Command {
    private final OrderManager orderManager;
    private final OrderComposite orderComposite;

    public RemoveOrderButton(OrderManager orderManager, OrderComposite orderComposite) {
        super(OrderManager.REMOVE_ORDER);
        this.orderManager = orderManager;
        this.orderComposite = orderComposite;
        setEnabled(false);
    }

    @Override
    public void processEvent() {

        JLabel label = new JLabel("<html><pre>" +
                orderComposite.getInfo().replace("\n", "<br/>") +
                "</pre></html>");

        JScrollPane scrollPane = new JScrollPane(label);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        JOptionPane.showMessageDialog(
                null,
                scrollPane,
                "Información",
                JOptionPane.INFORMATION_MESSAGE
        );

        String op = JOptionPane.showInputDialog(orderManager, "Ingrese el ID de orden");
        if (op == null) {
            return;
        }
        int numOrden = Integer.parseInt(op);
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
            try {
                orderComposite.removeComponent(numOrden - 1);
            } catch (Exception ex) {
                System.out.println("Error");
            }
        }
    }
}