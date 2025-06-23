package comportamental;

import javax.swing.JComboBox;
import tratamientoColecciones.OrderManager;
import creacional.BuilderFactory;
import creacional.UIDirector;
import creacional.UIBuilder;
import javax.swing.JPanel;

public class OrderTypeComboBox extends JComboBox<String> implements Command {
    private final OrderManager orderManager;
    private UIBuilder builder;

    public OrderTypeComboBox(OrderManager orderManager) {
        super();
        this.orderManager = orderManager;
        addItem(OrderManager.NULL);
        addItem(OrderManager.CA_ORDER);
        addItem(OrderManager.NON_CA_ORDER);
        addItem(OrderManager.OVERSEAS_ORDER);
        addItem(OrderManager.BZ_ORDER);
    }

    public void setBuilder(UIBuilder builder) {
        this.builder = builder;
    }

    @Override
    public void processEvent() {
        String selection = orderManager.getOrderType();
        if (!selection.equals(OrderManager.NULL)) {
            setPanelTypeOrder(selection);
            orderManager.getGetTotalButton().setEnabled(true);
            orderManager.getCreateOrderButton().setEnabled(true);
            orderManager.getModOrderButton().setEnabled(true);
            orderManager.getRemoveButton().setEnabled(true);
        } else {
            orderManager.getGetTotalButton().setEnabled(false);
            orderManager.getCreateOrderButton().setEnabled(false);
            orderManager.getModOrderButton().setEnabled(false);
            orderManager.getRemoveButton().setEnabled(false);
            orderManager.displayNewUI(new JPanel());
        }
    }

    private void setPanelTypeOrder(String selection) {
        BuilderFactory factory = new BuilderFactory();
        builder = factory.getUIBuilder(selection);
        UIDirector director = new UIDirector(builder);
        director.build();
        JPanel UIObj = builder.getSearchUI();
        orderManager.displayNewUI(UIObj);
        orderManager.configureButtons(builder);
    }
}