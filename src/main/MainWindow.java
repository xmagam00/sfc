package main;

import base.Neuron;
import base.Vector;
import components.PaintCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.logging.Logger;

import static constants.RceConstants.*;

/**
 * Created by <xmagam00@stud.fit.vutbr.cz> on 10.11.2014.
 */


public class MainWindow extends JFrame {
    private static Logger LOG = Logger.getLogger(MainWindow.class.getName());

    JTextField textField = new JTextField("10", 20);

    private PaintCanvas canvas;
    final JButton buttonReset = new JButton("Reinicializovať");
    JButton button = new JButton("Spustiť algoritmus");
    JButton buttonForward = new JButton("Krok dopredu");
    JButton buttonBack = new JButton("Krok dozadu");


    public MainWindow() {
        LOG.info("Create main window");
        canvas = new PaintCanvas();
        canvas.setClazz(1);
        initUI();


    }

    private void initUI() {


        this.add(getButtonPanel(), BorderLayout.EAST);

        setTitle("Demonštrácia algoritmu RCE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(canvas, BorderLayout.CENTER);
        this.pack();

        setSize(350, 250);

    }

    private JComponent getButtonPanel() {

        final JRadioButton radioClass1Button = new JRadioButton("Trieda 1", true);
        final JRadioButton radioClass2Button = new JRadioButton("Trieda 2");
        final JRadioButton radioClass3Button = new JRadioButton("Trieda 3");

        radioClass1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                canvas.setClazz(1);
                if (!radioClass1Button.isSelected()) {
                    canvas.setClazz(1);
                    radioClass1Button.setSelected(true);

                }
                if (radioClass2Button.isSelected()) {
                    radioClass2Button.setSelected(false);
                }

                if (radioClass3Button.isSelected()) {
                    radioClass3Button.setSelected(false);
                }
            }
        });

        radioClass2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                canvas.setClazz(2);
                if (!radioClass2Button.isSelected()) {
                    LOG.info("Change to class 2");
                    canvas.setClazz(2);
                    radioClass2Button.setSelected(true);

                }

                if (radioClass1Button.isSelected()) {
                    radioClass1Button.setSelected(false);
                }

                if (radioClass3Button.isSelected()) {
                    radioClass3Button.setSelected(false);
                }
            }
        });

        radioClass3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                canvas.setClazz(3);
                if (!radioClass3Button.isSelected()) {
                    LOG.info("Change to class 3");
                    canvas.setClazz(3);

                }

                if (radioClass2Button.isSelected()) {
                    radioClass2Button.setSelected(false);
                }

                if (radioClass1Button.isSelected()) {
                    radioClass1Button.setSelected(false);
                }
            }
        });

        radioClass1Button.setVisible(true);
        radioClass2Button.setVisible(true);
        radioClass3Button.setVisible(true);


        buttonReset.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                LOG.info("Reinicializacia algoritmu");
                canvas.setAlgorithmRun(false);
                canvas.setVectorList(new ArrayList<Vector>());
                canvas.paintNeuros = new ArrayList<Neuron>();
                canvas.setBackground(Color.DARK_GRAY);
                canvas.repaint();
            }
        });


        buttonReset.setVisible(true);
        JPanel inner = new JPanel();

        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                canvas.setAlgorithmRun(true);
                buttonReset.setEnabled(false);
                button.setEnabled(false);

                LOG.info("Initialization RCE algorithm");

                //RCE constants
                boolean modif = false;
                boolean hit = false;
                double distance = 0;

                java.util.List<Neuron> neurons = convertVectorToNeuron(canvas.getVectorList(), Double.parseDouble(textField.getText()));
                canvas.paintNeuros = new ArrayList<Neuron>();

                canvas.setAlgorithmRun(true);
                java.util.List<Neuron> hiddenNeuron = new ArrayList<Neuron>();
                Neuron hitNeuron = new Neuron();

                do {
                    modif = false;
                    distance = 0;
                    for (Neuron neuron : neurons) {
                        hitNeuron = neuron;
                        hit = false;
                        for (Neuron neuron1 : hiddenNeuron) {


                            distance = Math.sqrt(Math.pow((neuron1.getX() - neuron.getX()), 2) +
                                    Math.pow((neuron1.getY() - neuron.getY()), 2));
                            LOG.info("Vzdialenost = " + distance);
                            if (Double.compare(distance, neuron1.getRadius()) <= 0) {
                                if (neuron1.getClazz() == neuron.getClazz()) {
                                    hit = true;
                                } else {

                                    for (Neuron paintNeuron : canvas.paintNeuros) {
                                        if (neuron1.getRadius() == paintNeuron.getRadius() && neuron1.getClazz() == paintNeuron.getClazz()
                                                && neuron1.getY() == paintNeuron.getY() && neuron1.getX() == paintNeuron.getX()) {
                                            LOG.info("Polomer" + neuron1.getRadius() / 2);
                                            LOG.info("Polomer" + paintNeuron.getRadius() / 2);
                                            neuron1.setRadius(neuron1.getRadius() / 2);
                                            paintNeuron.setRadius(paintNeuron.getRadius() / 2);
                                            break;
                                        }


                                    }
                                    LOG.info("Modifikujem neuron");
                                    canvas.invalidate();
                                    canvas.repaint();
                                    canvas.revalidate();
                                    modif = true;
                                }
                            }
                        }

                        if (!hit) {
                            LOG.info("Vytvaram novy skryty neuron triedy=" + hitNeuron.getClazz() + " s polomerom=" + hitNeuron.getRadius());
                            canvas.paintNeuros.add(hitNeuron);
                            hiddenNeuron.add(hitNeuron);
                            canvas.invalidate();
                            canvas.repaint();
                            canvas.revalidate();


                            modif = true;
                        }

                    }
                } while (modif);
                canvas.setAlgorithmRun(false);
                LOG.info("Koniec RCE algoritmu");


                buttonReset.setEnabled(true);
                button.setEnabled(true);


            }
        });
        button.setVisible(true);

        inner.setLayout(new GridLayout(8, 1, 3, 3));
        inner.add(getTextField());
        inner.add(button);
        inner.add(buttonReset);
        inner.add(buttonForward);
        inner.add(buttonBack);
        inner.add(radioClass1Button);
        inner.add(radioClass2Button);
        inner.add(radioClass3Button);


        return inner;
    }

    private JComponent getTextField() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Polomer:");

        label.setVisible(true);

        textField.setVisible(true);
        panel.setLayout(new FlowLayout());
        panel.add(label, BorderLayout.LINE_END);
        panel.add(textField, BorderLayout.LINE_START);

        return panel;
    }

    private void algorithmRce(java.util.List<Vector> vectors, double maxRadius) {


    }

    private java.util.List<Neuron> convertVectorToNeuron(java.util.List<Vector> vectors, double maxRadius) {
        java.util.List<Neuron> neurons = new ArrayList<Neuron>();

        for (Vector vector : vectors) {
            Neuron neuron = new Neuron();
            neuron.setX(vector.getX());
            neuron.setY(vector.getY());
            neuron.setRadius(maxRadius);
            neuron.setCircleColor(vector.getCircleColor());
            neuron.setClazzColor(vector.getClazzColor());
            neuron.setClazz(vector.getClazz());
            neurons.add(neuron);
        }

        return neurons;
    }


}
