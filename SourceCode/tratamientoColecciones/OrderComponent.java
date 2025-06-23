package tratamientoColecciones;

/**
 *
 * @author Fabio - Diego
 */
public abstract class OrderComponent {

    private double total;

    public OrderComponent() {
        total = 0;
    }

    public void setTotal(double t) {
        total = t;
    }

    public double getTotal() {
        return total;
    }

    public void addComponent(OrderComponent component) throws Exception {
        System.out.println("Invalid Operation. Not Supported add component");
    }

    public OrderComponent getComponent(int componentNum) throws Exception {
        System.out.println("Invalid Operation. Not Supported get component");
        return null;
    }

    public void setComponent(int componentNum, OrderComponent component) throws Exception {
        System.out.println("Invalid Operation. Not Supported set component");
    }

    public void removeComponent(int componentNum) throws Exception {
        System.out.println("Invalid Operation. Not Supported remove component");
    }

    public abstract String toString();

}
