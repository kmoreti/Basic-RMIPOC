import javax.swing.*;
import java.io.*;

/**
 * Interface Service
 * A common Interface for Server and Client application
 */
public interface Service extends Serializable {
    public JPanel getGuiPanel();
}
