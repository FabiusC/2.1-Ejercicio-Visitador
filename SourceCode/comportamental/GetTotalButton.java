package comportamental;

import javax.swing.JButton;
import tratamientoColecciones.OrderManager;
import tratamientoColecciones.OrderComposite;
import javax.swing.JOptionPane;

public class GetTotalButton extends JButton implements Command {
    private final OrderManager orderManager;
    private final OrderComposite orderComposite;

    public GetTotalButton(OrderManager orderManager, OrderComposite orderComposite) {
        super(OrderManager.GET_TOTAL);
        this.orderManager = orderManager;
        this.orderComposite = orderComposite;
        setMnemonic('G');
        setEnabled(false);
    }

    @Override
    public void processEvent() {
        String total = String.valueOf(orderComposite.getTotal());
        orderManager.setTotalValue(total);
        JOptionPane.showMessageDialog(null, orderComposite.getInfo(), "Información", JOptionPane.INFORMATION_MESSAGE);
    }
}