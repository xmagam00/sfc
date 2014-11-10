package main;

import components.PaintCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

/**
 * Created by <xmagam00@stud.fit.vutbr.cz> on 10.11.2014.
 */
public class MainWindow extends JFrame {
    private static Logger LOG = Logger.getLogger(MainWindow.class.getName());

    private PaintCanvas canvas;

    public MainWindow() {
        LOG.info("Create main window");
        canvas = new PaintCanvas();
        initUI();


    }

    private void initUI() {


        this.add(getButtonPanel(), BorderLayout.LINE_END);

        setTitle("Points");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(canvas, BorderLayout.CENTER);
        this.pack();

        setSize(350, 250);

    }

    private JComponent getButtonPanel() {
        JPanel inner = new JPanel();
        JButton button = new JButton("Spustiť algoritmus");
        button.setVisible(true);
        inner.add(button, new GridLayout(0, 2));
        inner.add(getTextField());


        return inner;
    }

    private JComponent getTextField() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Polomer:");
        label.setVisible(true);
        JTextField textField = new JTextField("Zadajte polomer", 20);
        textField.setVisible(true);
        panel.add(textField, BorderLayout.LINE_END);
        panel.add(label, BorderLayout.LINE_START);
        return panel;
    }
}
