import javax.swing.JFrame;
import logic.*;
import display.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Configuration des joueurs
        ArrayList<Point> pointsPlayer1 = new ArrayList<>();
        Player player1 = new Player("Rivo", pointsPlayer1, 1);

        ArrayList<Point> pointsPlayer2 = new ArrayList<>();
        Player player2 = new Player("Miora", pointsPlayer2, 2);

        Player[] players = {player1, player2};

        // Configuration du jeu
        Field field = new Field(30, 30);
        Game game = new Game(field, players);

        // Affichage
        JFrame frame = new JFrame("Ze de poinnns");
        frame.setResizable(false);
        GamePanel panel = new GamePanel(game);
        Listener listener = new Listener(game, panel);

        panel.addMouseListener(listener);
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
