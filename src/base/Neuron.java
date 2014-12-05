package base;

import java.awt.*;

import static constants.RceConstants.*;

/**
 * Created by <xmagam00@stud.fit.vutbr.cz> on 23.11.2014.
 */
public class Neuron {

    private int x;
    private int y;
    private int clazz = NOT_CLASIFIED;
    private double radius = NOT_ASSIGNED_RADIUS;

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

    private Color clazzColor;
    private Color circleColor;

    public Neuron() {
    }

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


}
