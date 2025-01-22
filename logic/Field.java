package logic;

public class Field {
    protected int length;
    protected int width;

    public Field(int length,int width) {
        this.setLength(length);
        this.setWidth(width);
    }

    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
}
