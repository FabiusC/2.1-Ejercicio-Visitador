package creacional;

import tratamientoColecciones.OrderManager;

/**
 *
 * @author Fabio - Diego
 */
public class BuilderFactory {
    public UIBuilder getUIBuilder(String str) {
        UIBuilder builder = null;
        if (str.equals(OrderManager.CA_ORDER)) {
            builder = new CABuilder();
        } else if (str.equals(OrderManager.NON_CA_ORDER)) {
            builder = new NONCABuilder();
        } else if (str.equals(OrderManager.OVERSEAS_ORDER)) {
            builder = new OverseasBuilder();
        } else if (str.equals(OrderManager.BZ_ORDER)) {
            builder = new BZBuilder();
        }
        return builder;
    }
}
