package tratamientoColecciones;

/**
 *
 * @author Fabio - Diego
 */
public class OverseasOrder extends OrderComponent implements Order {

    private double orderAmount;
    private double additionalSH;
    private String name = "Overseas";

    public OverseasOrder(double inp_orderAmount, double inp_additionalSH) {
        orderAmount = inp_orderAmount;
        additionalSH = inp_additionalSH;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public double getAdditionalSH() {
        return additionalSH;
    }

    public void accept(OrderVisitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        return "\n " + name + " Order \n Amount: " + orderAmount + "\n SH: " + additionalSH + "\n Total:" + getTotal();
    }
}
