package logic;

import java.util.ArrayList;

public class Player {
    public String name;
    protected ArrayList<Point> points;
    private int playerValue;
    private Node node;
    
    public Player(String name,ArrayList<Point> points,int playerValue) {
        this.name=name;
        this.setPoints(points);
        this.setPlayerValue(playerValue);
    }

    public Player(String name,int playerValue) {
        this.name=name;
        this.setPlayerValue(playerValue);
    }

   
    public void setPlayerValue(int playerValue) {
        this.playerValue = playerValue;
    }
    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    
    public int getPlayerValue() {
        return playerValue;
    }
    public ArrayList<Point> getPoints() {
        return points;
    } 

    public Node getNode() {
        return node;
    }
    public void setNode(Node node) {
        this.node = node;
    }

    public void insertPoint(Point point) {
        this.getPoints().add(point);
        System.out.println(this.getPoints().size());
    }

    public void insertPoint(int x,int y) {
        Point point=new Point(x,y,this.getPlayerValue());
        this.getPoints().add(point);
        System.out.println(this.getPoints().size());
    }
}