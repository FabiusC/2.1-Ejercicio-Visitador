package comportamental;

import javax.swing.JButton;
import tratamientoColecciones.OrderManager;
import java.awt.event.KeyEvent;

public class ExitButton extends JButton implements Command {
    public ExitButton() {
        super(OrderManager.EXIT);
        setMnemonic(KeyEvent.VK_X);
    }

    @Override
    public void processEvent() {
        System.exit(1);
    }
}