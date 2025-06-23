package tratamientoColecciones;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author Fabio - Diego
 */
public class OrderComposite extends OrderComponent {

    Vector orderCol = new Vector();

    public OrderComposite() {

    }

    public void addComponent(OrderComponent component) throws Exception {
        orderCol.add(component);
    }

    public OrderComponent getComponent(int componentNum) throws Exception {
        return (OrderComponent) orderCol.elementAt(componentNum);
    }

    public void setComponent(int componentNum, OrderComponent o) throws Exception {
        orderCol.set(componentNum, o);
    }

    public void removeComponent(int componentNum) throws Exception {
        orderCol.remove(componentNum);
    }

    @Override
    public double getTotal() {
        double totalOrders = 0;
        Enumeration e = orderCol.elements();
        int i = 1;
        while (e.hasMoreElements()) {
            OrderComponent component = (OrderComponent) e.nextElement();
            totalOrders = totalOrders + (component.getTotal());
            i++;
        }
        return totalOrders;
    }

    public String getInfo() {
        Enumeration e = orderCol.elements();
        int i = 1;
        String info = "Orden: \n";
        while (e.hasMoreElements()) {
            OrderComponent component = (OrderComponent) e.nextElement();
            info = info + "ID: " + i + component + "\n\n";
            i++;
        }
        return info;
    }

    @Override
    public String toString() {
        return "Pedido " + getTotal();
    }

    // uso para el patron iterador externo
    public Iterator getItOrders() {
        return new IteratorOrders(this);
    }

    public Enumeration getAllOrders() {
        return orderCol.elements();
    }

}
