package tratamientoColecciones;

/**
 *
 * @author Fabio - Diego
 */
public class BrazilianOrder extends OrderComponent implements Order {

    private double orderAmount;
    private double additionalSH;
    private String name = "Brazil";

    public BrazilianOrder(double inp_orderAmount, double inp_additionalSH) {
        orderAmount = inp_orderAmount;
        additionalSH = inp_additionalSH;
    }

    @Override
    public void accept(OrderVisitor v) {
        v.visit(this);
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public double getAdditionalSH() {
        return additionalSH;

    }

    @Override
    public String toString() {
        return "\n " + name + " Order \n Amount: " + orderAmount + "\n S & H: " + additionalSH + "\n Total:"
                + getTotal();
    }
}
