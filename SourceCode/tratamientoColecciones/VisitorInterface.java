package tratamientoColecciones;

/**
 *
 * @author Fabio - Diego
 */
public interface VisitorInterface {

    public void visit(NonCaliforniaOrder nco);

    public void visit(CaliforniaOrder co);

    public void visit(OverseasOrder oo);

    public void visit(BrazilianOrder bzo);
}
