package logic;

public class Game {
    Field field;
    Player[] players;

    public Game(Field field,Player[] players) {
        this.setField(field);
        this.setPlayers(players);

    }

    public Field getField() {
        return field;
    }
    public void setField(Field field) {
        this.field = field;
    }

    public Player[] getPlayers() {
        return players;
    }
    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public boolean seekPlayerNode(Player player) {
        int[] count=player.getNode().CountAlignment(player);
        if(count==null){return false;}
        int i=0;
        while(i<count.length) {
            if(count[i]>=5){return true;}
            i++;
        }
        return false;
    }
    
}
