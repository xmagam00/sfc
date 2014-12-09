package main;

import base.Neuron;
import base.Vector;
import components.PaintCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.logging.Logger;


/**
 * Created by <xmagam00@stud.fit.vutbr.cz> on 10.11.2014.
 */

/**
 * Hlavna trieda odkial sa spusta vypocet
 */
public class MainWindow extends JFrame {

    //Logger pre logovanie
    private static Logger LOG = Logger.getLogger(MainWindow.class.getName());
    //vstupne textove pole
    JTextField textField = new JTextField("20", 20);
    //kresliace platno
    private PaintCanvas canvas;
    //labe pre cas behu algoritmu
    JLabel timeLabel = new JLabel("Dĺžka behu: 0 milisek.");
    //tlacidlo
    final JButton buttonReset = new JButton("Reinicializovať");
    //tlacidlo pre spustenie algoritmu
    JButton button = new JButton("Spustiť algoritmus");
    //tlacidlo pre krokovanie dopredu
    JButton buttonForward = new JButton("Krok dopredu");
    //tlacidlo pre krokovanie dozadu
    JButton buttonBack = new JButton("Krok dozadu");

    //hlavny konstruktor
    public MainWindow() {
        canvas = new PaintCanvas();
        canvas.setClazz(1);
        initUI();
    }

    //metoda pre inicializaciu uzivatelskeho rozhrania
    private void initUI() {

        //vytvorim zakladny layout
        this.add(getButtonPanel(), BorderLayout.EAST);
        //nastavim titulok pre vytvorene okno
        setTitle("Demonštrácia algoritmu RCE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(canvas, BorderLayout.CENTER);
        this.pack();
        //nastavit velkost okna
        setSize(350, 250);
    }

    //metoda pre vytvorenie dalsieho layoutu
    private JComponent getButtonPanel() {
        //tlacidla pre zadanie vstupnych tried
        final JRadioButton radioClass1Button = new JRadioButton("Trieda 1", true);
        final JRadioButton radioClass2Button = new JRadioButton("Trieda 2");
        final JRadioButton radioClass3Button = new JRadioButton("Trieda 3");

        //nastavenie listenera pre tlacidlo 1
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

        //nastavenie tlacidla
        radioClass2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                canvas.setClazz(2);
                if (!radioClass2Button.isSelected()) {
                    LOG.info("Nastavena trieda 2");
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

        //nastavenie listenera pre zadanie vstupnej triedy
        radioClass3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                canvas.setClazz(3);
                if (!radioClass3Button.isSelected()) {
                    LOG.info("Nastavena trieda 3");
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

        //nastavim viditelnost jednotlivych tlacidiel
        radioClass1Button.setVisible(true);
        radioClass2Button.setVisible(true);
        radioClass3Button.setVisible(true);

        //nastavim pre restovacie tlacitko actionListener
        buttonReset.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                buttonBack.setEnabled(false);
                buttonForward.setEnabled(false);
                canvas.backRun = false;
                canvas.forwardRun = false;
                canvas.backNeuros = new ArrayList<Neuron>();
                canvas.allNeuros = new ArrayList<Neuron>();
                LOG.info("Reinicializacia algoritmu");
                timeLabel.setText("Čas behu 0 milisek.");
                canvas.allNeuros = new ArrayList<Neuron>();
                canvas.setAlgorithmRun(false);
                canvas.setVectorList(new ArrayList<Vector>());
                canvas.paintNeuros = new ArrayList<Neuron>();
                canvas.setBackground(Color.DARK_GRAY);
                canvas.repaint();
            }
        });

        buttonBack.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                LOG.info("Krok spat");
                canvas.forwardRun = false;
                canvas.backRun = true;
                if (canvas.backNeuros.size() > 0) {
                    canvas.backNeuros.remove(canvas.backNeuros.size() - 1);
                }
                if (canvas.backNeuros.size() == 0) {
                    buttonBack.setEnabled(false);
                    canvas.forwardNeuros = new ArrayList<Neuron>();
                }

                if (canvas.backNeuros.size() != canvas.allNeuros.size()) {
                    buttonForward.setEnabled(true);
                }


                canvas.repaint();
            }
        });

        //priradim actionListener pre tlacidlo dopredu
        buttonForward.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                LOG.info("Krok dopredu");
                canvas.forwardRun = true;
                canvas.backRun = false;
                canvas.setAlgorithmRun(false);

                //pokial som nedosiel na koniec

                if (canvas.backNeuros.size() != canvas.allNeuros.size()) {
                    for (Neuron tmpNeuron : canvas.backNeuros) {
                        canvas.forwardNeuros.add(tmpNeuron);
                    }

                    try {
                        //pridaj dalsi krok
                        canvas.forwardNeuros.add(canvas.allNeuros.get(canvas.forwardNeuros.size()));
                    } catch (Exception ex) {
                        canvas.forwardNeuros.remove(canvas.forwardNeuros.size() - 1);
                        try {
                            canvas.forwardNeuros.add(canvas.allNeuros.get(canvas.forwardNeuros.size()));
                        } catch (Exception ex2) {
                            canvas.forwardNeuros.remove(canvas.forwardNeuros.size() - 2);
                            canvas.forwardNeuros = new ArrayList<Neuron>();
                            for (Neuron tmpBack : canvas.backNeuros) {
                                canvas.forwardNeuros.add(tmpBack);
                            }
                            canvas.forwardNeuros.add(canvas.allNeuros.get(canvas.forwardNeuros.size()));
                        }
                    }

                    canvas.backNeuros = new ArrayList<Neuron>();
                    for (Neuron tmpNeuron : canvas.forwardNeuros) {
                        canvas.backNeuros.add(tmpNeuron);
                    }

                    if (canvas.forwardNeuros.size() == canvas.allNeuros.size()) {
                        buttonForward.setEnabled(false);
                        buttonBack.setEnabled(true);

                    }
                    if (canvas.forwardNeuros.size() > 0) {
                        buttonBack.setEnabled(true);
                    }
                    //prekresli
                    canvas.repaint();
                }

            }


        });
        buttonReset.setVisible(true);
        JPanel inner = new JPanel();

        //nastavim actionListner pre tlacidlo na spustenie algoritmu
        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                canvas.setAlgorithmRun(true);
                buttonReset.setEnabled(false);
                button.setEnabled(false);

                //RCE constants
                boolean modif = false;
                boolean hit = false;
                double distance = 0;
                java.util.List<Neuron> neurons = null;
                //premen vstupne vektory na neurony
                try {
                    if (canvas.getVectorList().size() < 1) {
                        JDialog dialog = new JDialog();
                        dialog.setVisible(true);
                        dialog.setSize(300, 200);
                        JLabel errorLabel = new JLabel("Chyba: Neboli zadané vstupné vektory");
                        dialog.add(errorLabel);
                        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                        dialog.setLocation(dim.width / 2 - dialog.getSize().width / 2, dim.height / 2 - dialog.getSize().height / 2);
                        LOG.info("Neboli zadane vstupne vektory");
                        buttonReset.setEnabled(true);
                        button.setEnabled(true);
                        canvas.setAlgorithmRun(false);
                        return;
                    }
                    neurons = convertVectorToNeuron(canvas.getVectorList(), Double.parseDouble(textField.getText()));
                } catch (Exception ex) {
                    JDialog dialog = new JDialog();
                    dialog.setVisible(true);
                    dialog.setSize(300, 100);
                    JLabel errorLabel = new JLabel("Chybná hodnota polomeru");
                    dialog.add(errorLabel);
                    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                    dialog.setLocation(dim.width / 2 - dialog.getSize().width / 2, dim.height / 2 - dialog.getSize().height / 2);
                    buttonReset.setEnabled(true);
                    button.setEnabled(true);
                    canvas.setAlgorithmRun(false);
                    LOG.info("Chyba vo vstupnych datach");
                    return;
                }
                if (Double.parseDouble(textField.getText()) <= 10) {
                    JDialog dialog = new JDialog();
                    dialog.setVisible(true);
                    dialog.setSize(300, 100);
                    JLabel errorLabel = new JLabel("Chybná hodnota polomeru");
                    dialog.add(errorLabel);
                    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                    dialog.setLocation(dim.width / 2 - dialog.getSize().width / 2, dim.height / 2 - dialog.getSize().height / 2);
                    buttonReset.setEnabled(true);
                    button.setEnabled(true);
                    canvas.setAlgorithmRun(false);
                    LOG.info("Chyba vo vstupnych datach");
                    return;
                }

                canvas.paintNeuros = new ArrayList<Neuron>();
                //indikuj spustenie algoritmu
                canvas.setAlgorithmRun(true);
                //vytvorim prazdnu vrstvu skrytych neuronov
                java.util.List<Neuron> hiddenNeuron = new ArrayList<Neuron>();
                Neuron hitNeuron = new Neuron();

                final long startTime = System.nanoTime();
                //implementacia RCE agoritmus
                do {
                    modif = false;
                    distance = 0;
                    //prechadzam cez vsetky neurony
                    for (Neuron neuron : neurons) {
                        hitNeuron = neuron;
                        hit = false;
                        //prechadzam cez vsetky skryte neurony
                        for (Neuron neuron1 : hiddenNeuron) {

                            //spocitam vzdialenost skrytych neuronov a vstupnych
                            distance = Math.round(Math.sqrt(Math.pow((neuron1.getX() - neuron.getX()), 2) +
                                    Math.pow((neuron1.getY() - neuron.getY()), 2)));
                            LOG.info("Vzdialenost = " + distance + " medzi bodom so suradnicami " +
                                    "x =" + neuron1.getX() + " y=" + neuron1.getY() + " a bodom" +
                                    " so suradnicami x=" + neuron.getX() + " y=" + neuron.getY());
                            //pokial sa bod nachadza v danej hyperguly

                            if (Double.compare(distance, neuron1.getRadius()) <= 0) {
                                //porovnaj triedy
                                if (neuron1.getClazz() == neuron.getClazz()) {
                                    hit = true;
                                } else {
                                    //zmensi polomer hypergule


                                    for (Neuron paintNeuron : canvas.paintNeuros) {
                                        if (neuron1.getRadius() == paintNeuron.getRadius() && neuron1.getClazz() == paintNeuron.getClazz()
                                                && neuron1.getY() == paintNeuron.getY() && neuron1.getX() == paintNeuron.getX()) {
                                            LOG.info("Modifikujem polomer neuronu so suradnicami x=" +
                                                    neuron1.getX() + " y=" + neuron1.getY() +
                                                    " z hodnoty=" + neuron1.getRadius() + " na hodnotu=" +
                                                    neuron1.getRadius() / 2);
                                            neuron1.setRadius(neuron1.getRadius() / 2);
                                            paintNeuron.setRadius(paintNeuron.getRadius() / 2);
                                            canvas.allNeuros.add(paintNeuron);
                                            break;
                                        }
                                    }
                                    //prekresli neuron
                                    canvas.invalidate();
                                    canvas.repaint();
                                    canvas.revalidate();
                                    modif = true;
                                }
                            }
                        }

                        if (!hit) {
                            LOG.info("Vytvaram novy skryty neuron triedy=" + hitNeuron.getClazz() + " s polomerom=" + hitNeuron.getRadius() +
                                    " suradnicami x=" + hitNeuron.getX() + " y=" + hitNeuron.getY());
                            canvas.paintNeuros.add(hitNeuron);
                            canvas.allNeuros.add(hitNeuron);
                            hiddenNeuron.add(hitNeuron);
                            //prekresli
                            canvas.invalidate();
                            canvas.repaint();
                            canvas.revalidate();
                            modif = true;
                        }

                    }
                } while (modif);
                final long duration = System.nanoTime() - startTime;
                timeLabel.setText("Čas behu " + Double.toString(Math.round((double) duration / 1000000.0)) + " milisek.");
                //prirad neurony
                for (Neuron backNeuron : canvas.allNeuros) {
                    canvas.backNeuros.add(backNeuron);
                }
                canvas.setAlgorithmRun(false);
                LOG.info("Koniec RCE algoritmu");
                buttonReset.setEnabled(true);
                button.setEnabled(true);
                buttonBack.setEnabled(true);
                buttonForward.setEnabled(false);


            }
        });
        buttonBack.setEnabled(false);
        buttonForward.setEnabled(false);
        button.setVisible(true);
        //nastav layout pre tlacidla
        inner.setLayout(new GridLayout(10, 1, 3, 3));
        inner.add(getTextField());
        inner.add(button);
        inner.add(buttonReset);
        inner.add(buttonForward);
        inner.add(buttonBack);
        inner.add(radioClass1Button);
        inner.add(radioClass2Button);
        inner.add(radioClass3Button);
        inner.add(timeLabel);
        return inner;
    }

    //metoda pre vytvorenie komponty na zadanie polomeru
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

    /**
     * Metóda, ktorá zkontertuje zoznam vektorov na zoznam Neuronov
     *
     * @param vectors
     * @param maxRadius
     * @return
     */
    private java.util.List<Neuron> convertVectorToNeuron(java.util.List<Vector> vectors, double maxRadius) {
        java.util.List<Neuron> neurons = new ArrayList<Neuron>();

        //iterujem cez vsetky vektory
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
