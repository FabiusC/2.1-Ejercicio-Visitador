package tratamientoColecciones;

import java.util.*;

/**
 *
 * @author Fabio - Diego
 */
public class IteratorOrders implements Iterator {

    private Enumeration ec;
    private Order nextOrder;

    public IteratorOrders(OrderComposite c) {
        ec = c.getAllOrders();
    }

    @Override
    public boolean hasNext() {
        boolean matchFound = false;
        while (ec.hasMoreElements()) {
            matchFound = true;
            nextOrder = (Order) ec.nextElement();
            break;
        }
        if (matchFound == true) {
        } else {
            nextOrder = null;
        }
        return matchFound;
    }

    @Override
    public Object next() {
        if (nextOrder == null) {
            throw new NoSuchElementException();
        } else {
            return nextOrder;
        }
    }

}
