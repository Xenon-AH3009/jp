package logic;

import java.util.ArrayList;

public class Node {
    private ArrayList<java.awt.Point> forward;
    private java.awt.Point previous;
    private java.awt.Point origin;

    public Node(Player player,int index) {
        this.setOrigin(player.getPoints().get(index));
        java.awt.Point point=new java.awt.Point((int)player.getPoints().get(index).getX(),(int)player.getPoints().get(index).getY());
        this.setForward(point);
    }
    
    public ArrayList<java.awt.Point> getForward() {
        return this.forward;
    }
    public java.awt.Point getPrevious() {
        return this.previous;
    }
    public java.awt.Point getOrigin() {
        return this.origin;
    }
    public void setForward(java.awt.Point point) {
        ArrayList<java.awt.Point> neighbours=new ArrayList<java.awt.Point>(8);
        neighbours.add(0, new java.awt.Point( (int)point.getX()-1 , (int)point.getY()-1) );
        neighbours.add(1, new java.awt.Point( (int)point.getX() , (int)point.getY()-1) );
        neighbours.add(2, new java.awt.Point( (int)point.getX()+1 , (int)point.getY()-1) );
        neighbours.add(3, new java.awt.Point( (int)point.getX()+1 , (int)point.getY()) );
        neighbours.add(4, new java.awt.Point( (int)point.getX()+1 , (int)point.getY()+1) );
        neighbours.add(5, new java.awt.Point( (int)point.getX() , (int)point.getY()+1) );
        neighbours.add(6, new java.awt.Point( (int)point.getX()-1 , (int)point.getY()+1) );
        neighbours.add(7, new java.awt.Point( (int)point.getX()-1 , (int)point.getY()) );
        this.forward=neighbours;
    }
    public void setOrigin(java.awt.Point origin) {
        this.origin = origin;
    }
    public void setPrevious(java.awt.Point previous) {
        this.previous = previous;
    }

    public ArrayList<Node> getNeighbours(Player player) {
        ArrayList<Point> playerPoints=player.getPoints();
        ArrayList<java.awt.Point> points=new ArrayList<java.awt.Point>(playerPoints.size());
        int i=0,j=0;
        while (i<playerPoints.size()) {
            points.add(new java.awt.Point((int)playerPoints.get(i).getX(),(int)playerPoints.get(i).getY()));
            i++;
        }
        i=0;
        ArrayList<Node> neighbour=new ArrayList<Node>();
        while(i<this.getForward().size()) {
            if(points.contains(this.getForward().get(i)) && !this.getForward().get(i).equals(this.getPrevious())) {
                neighbour.add(new Node(player, points.indexOf(this.getForward().get(i))));
                neighbour.get(j).setPrevious(this.getOrigin());
                j++;
            }
            i++;
        }
        return neighbour;
    }

    public static boolean checkNeighboursHeritage(Node node,Player player) {
        boolean bool=false;
        int x=Math.abs((int)(node.getOrigin().getX()-node.getPrevious().getX()));
        int y=Math.abs((int)(node.getOrigin().getY()-node.getPrevious().getY()));
        ArrayList<Node> neighbours=node.getNeighbours(player);
        for(Node nodeSon:neighbours) {
            if(nodeSon.getOrigin().getX()==nodeSon.getPrevious().getX()-x && nodeSon.getOrigin().getY()==nodeSon.getPrevious().getY()-y 
            || nodeSon.getOrigin().getX()==nodeSon.getPrevious().getX()+x && nodeSon.getOrigin().getY()==nodeSon.getPrevious().getY()+y 
            || nodeSon.getOrigin().getX()==nodeSon.getPrevious().getX()-x && nodeSon.getOrigin().getY()==nodeSon.getPrevious().getY()+y 
            || nodeSon.getOrigin().getX()==nodeSon.getPrevious().getX()+x && nodeSon.getOrigin().getY()==nodeSon.getPrevious().getY()-y ) {
                bool=true;
            }
        }
        return bool;
    }

    public static Node getNeighboursHeritage(Node node,Player player) {
        int x=Math.abs((int)(node.getOrigin().getX()-node.getPrevious().getX()));
        int y=Math.abs((int)(node.getOrigin().getY()-node.getPrevious().getY()));
        ArrayList<Node> neighbours=node.getNeighbours(player);
        Node retour=null;
        for(Node nodeSon:neighbours) {
            if(nodeSon.getOrigin().getX()==nodeSon.getPrevious().getX()-x && nodeSon.getOrigin().getY()==nodeSon.getPrevious().getY()-y 
            || nodeSon.getOrigin().getX()==nodeSon.getPrevious().getX()+x && nodeSon.getOrigin().getY()==nodeSon.getPrevious().getY()+y 
            || nodeSon.getOrigin().getX()==nodeSon.getPrevious().getX()-x && nodeSon.getOrigin().getY()==nodeSon.getPrevious().getY()+y 
            || nodeSon.getOrigin().getX()==nodeSon.getPrevious().getX()+x && nodeSon.getOrigin().getY()==nodeSon.getPrevious().getY()-y ) {
                retour=nodeSon;
            }
        }
        return retour;
    }

    public ArrayList<java.awt.Point> getFiveHeritedPoints(Player player) {
        ArrayList<java.awt.Point> heritedPoints = new ArrayList<>();
        ArrayList<Node> neighbours=this.getNeighbours(player);
        
        for(Node currentNode :neighbours){
            int count=0;
        // Ajoutez les points hérités jusqu'à obtenir 5 ou jusqu'à ce qu'il n'y ait plus de voisins hérités
            while (currentNode != null && heritedPoints.size() < 5) {
                heritedPoints.add(currentNode.getOrigin());
                currentNode = getNeighboursHeritage(currentNode, player);
                count++;
                if(count==5){break;}
            }
        }
        heritedPoints.add(player.getNode().getOrigin());
    
        return heritedPoints;
    }    

    public static int CountAlignment(Node node,Player player) {
        Node nodeSon=node;
        int count=1;
        while(true) {
            count++;
            System.out.println(nodeSon.getOrigin().getLocation());
            nodeSon=Node.getNeighboursHeritage(nodeSon, player);
            if(nodeSon==null){break;}
        }
        return count;
    }

    public int[] CountAlignment(Player player) {
        ArrayList<Node> nodeSon=this.getNeighbours(player);
        if(nodeSon.size()==0){return null;}
        int []count=new int[nodeSon.size()];
        int i=0;
        for (Node node : nodeSon) {
            count[i]=Node.CountAlignment(node,player);
            i++;
        }
        return count;
    }  
}
