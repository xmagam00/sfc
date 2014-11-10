package base;

/**
 * Created by <xmagam00@stud.fit.vutbr.cz> on 10.11.2014.
 */
public class Vector {

    private int x = 0;
    private int y = 0;

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
}
