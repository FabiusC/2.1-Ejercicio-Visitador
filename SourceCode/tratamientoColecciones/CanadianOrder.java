package tratamientoColecciones;

/**
 *
 * @author Fabio - Diego
 */
public class CanadianOrder extends OrderComponent implements Order {

    private double orderAmount;
    private String name = "Canada";

    public CanadianOrder(double inp_orderAmount) {
        orderAmount = inp_orderAmount;
    }

    @Override
    public void accept(OrderVisitor v) {
        v.visit(this);
    }

    public double getOrderAmount() {
        return orderAmount;
    }


    @Override
    public String toString() {
        return "\n " + name + " Order \n Amount: " + orderAmount + "\n Total:"
                + getTotal();
    }
}
