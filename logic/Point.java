package logic;


public class Point extends java.awt.Point{
    private int value;

    public Point(int x,int y,int value) {
        super(x,y);
        this.setValue(value);
    }

    public Point(java.awt.Point point,int value) {
        super(point);
        this.setValue(value);
    }

    public int getValue() {
        return value;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setValue(int value) {
        this.value = value;
    }
}
