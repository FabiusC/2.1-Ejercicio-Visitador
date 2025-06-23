package tratamientoColecciones;

/**
 * 
 * @author Fabio - Diego
 */
public class CaliforniaOrder extends OrderComponent implements Order {

    private double orderAmount;
    private double additionalTax;
    private String name = "California";

    public CaliforniaOrder(double inp_orderAmount, double inp_additionalTax) {
        orderAmount = inp_orderAmount;
        additionalTax = inp_additionalTax;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public double getAdditionalTax() {
        return additionalTax;
    }

    @Override
    public void accept(OrderVisitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        return "\n " + name + " Order \n Amount: " + orderAmount + "\n Tax: " + additionalTax + "\n Total:"
                + getTotal();
    }
}
