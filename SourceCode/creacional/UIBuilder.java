package creacional;

import java.util.HashMap;

import javax.swing.*;

/**
 *
 * @author Fabio - Diego
 */
public abstract class UIBuilder {

    protected JPanel searchUI;

    public abstract void addUIControls();

    public abstract void initialize();

    public abstract HashMap<String, String> getValues();

    public abstract void setValues(HashMap<String, String> values);

    public JPanel getSearchUI() {
        return searchUI;
    }
}
