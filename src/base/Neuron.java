package base;

import java.awt.*;

import static constants.RceConstants.*;

/**
 * Created by <xmagam00@stud.fit.vutbr.cz> on 23.11.2014.
 */

/**
 * Trieda, ktora reprezentuje dany neuron
 */
public class Neuron {

    //x-ova suradnica
    private int x;
    //y-ova suradnica
    private int y;
    //trieda, do ktorej je zaradeny dany neuron, pricom
    //ma priradenu hodnotu -1, ktora hovori ze neuron nie je klasifikovany do triedy
    private int clazz = NOT_CLASIFIED;
    //polomer pre dany neuron
    private double radius = NOT_ASSIGNED_RADIUS;
    //farba pre danu triedu bodu
    private Color clazzColor;
    //farba pre danu triedu kruznice
    private Color circleColor;

    public Neuron() {
    }

    //konstruktor
    public Neuron(int x, int y, int clazz, int radius, Color clazzColor, Color circleColor) {
        this.x = x;
        this.y = y;
        this.clazz = clazz;
        this.radius = radius;
        this.clazzColor = clazzColor;
        this.circleColor = circleColor;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getClazz() {
        return clazz;
    }

    public void setClazz(int clazz) {
        this.clazz = clazz;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Color getClazzColor() {
        return clazzColor;
    }

    public void setClazzColor(Color clazzColor) {
        this.clazzColor = clazzColor;
    }

    public Color getCircleColor() {
        return circleColor;
    }

    public void setCircleColor(Color circleColor) {
        this.circleColor = circleColor;
    }


}
