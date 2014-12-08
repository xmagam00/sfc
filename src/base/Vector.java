package base;

/**
 * Created by <xmagam00@stud.fit.vutbr.cz> on 10.11.2014.
 */

import java.awt.*;

import static constants.RceConstants.*;

/**
 * Trieda, ktora reprezentuje dany vstupny vektor
 */
public class Vector {

    //x-ova suradnica
    private int x = 0;
    //y-ova suradnice
    private int y = 0;
    //farba pre danu triedu vektoru
    private Color clazzColor;
    //farba pre danu triedu hypegule (kruznice)
    private Color circleColor;
    private int clazz;

    public Vector() {

    }

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

    public int getClazz() {
        return clazz;
    }

    public void setClazz(int clazz) {
        this.clazz = clazz;
    }
}
