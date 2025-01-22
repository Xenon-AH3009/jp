package logic;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Player player=new Player("player1",1);
        Point A=new Point(2,1,player.getPlayerValue());
        Point B=new Point(3,2,player.getPlayerValue());
        Point C=new Point(4,3,player.getPlayerValue());
        Point D=new Point(5,4,player.getPlayerValue());
        Point E=new Point(6,5,player.getPlayerValue());
        ArrayList<Point> listPoints=new ArrayList<Point>();
        listPoints.add(A);
        listPoints.add(B);
        listPoints.add(C);
        listPoints.add(D);
        listPoints.add(E);
        player.setPoints(listPoints);
        Node node=new Node(player, 3);
        //boolean[] bool=node.checkAllHeritage(player);
        ArrayList<Node> neighbours=node.getNeighbours(player);
        /*test getNeighboursHeritage checked */
        //Node n=neighbours.get(0).getNeighbours(player).get(0).getNeighbours(player).get(0);
        //System.out.println(Node.getNeighboursHeritage(n, player).getOrigin().getLocation());
        //System.out.println(bool[0]);
        /*alignement problem of heritage */
        System.out.println(Node.CountAlignment(neighbours.get(0), player));
    }
}
