package main;


import java.awt.*;
import java.util.logging.Logger;

/**
 * Created by <xmagam00@stud.fit.vutbr.cz> on 20.10.2014.
 */
public class Main {

    private static Logger LOG = Logger.getLogger(Main.class.getName());

    //hlavna metoda, z ktorej sa spusta aplikacia
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow window = new MainWindow();
                window.setVisible(true);
                window.setSize(1000, 800);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                window.setLocation(dim.width / 2 - window.getSize().width / 2, dim.height / 2 - window.getSize().height / 2);
                window.setVisible(true);
            }
        });


    }
}
