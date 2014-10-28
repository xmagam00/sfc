import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

/**
 * Created by <xmagam00@stud.fit.vutbr.cz> on 20.10.2014.
 */
public class Main {

    private static Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        LOG.info("Main method");

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(1000, 800);
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);




    }
}
