package tratamientoColecciones;

/**
 *
 * @author Fabio - Diego
 */
public class OrderVisitor implements VisitorInterface {

    private double orderTotal;

    public OrderVisitor() {
    }

    public void visit(CaliforniaOrder inp_order) {
        orderTotal = inp_order.getOrderAmount() + inp_order.getAdditionalTax();
        inp_order.setTotal(getOrderTotal());
    }

    public void visit(NonCaliforniaOrder inp_order) {
        orderTotal = inp_order.getOrderAmount();
        inp_order.setTotal(getOrderTotal());

    }

    public void visit(OverseasOrder inp_order) {
        orderTotal = inp_order.getOrderAmount() + inp_order.getAdditionalSH();
        inp_order.setTotal(getOrderTotal());
    }

    public void visit(CanadianOrder inp_order) {
        orderTotal = inp_order.getOrderAmount();
        inp_order.setTotal(getOrderTotal());
    }

    public double getOrderTotal() {
        return orderTotal;
    }

}
