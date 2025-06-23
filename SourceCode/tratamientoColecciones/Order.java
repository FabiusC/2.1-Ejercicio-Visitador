package tratamientoColecciones;

/**
 *
 * @author Fabio - Diego
 */
public interface Order {

    public void accept(OrderVisitor v);
}
