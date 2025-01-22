package display;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import logic.Game;
import logic.Node;
import logic.Player;

public class Listener extends MouseAdapter {
    private Game game;
    private GamePanel panel;
    private int currentPlayerIndex = 0;
    private VictoryPanel victoryPanel;

        public Listener(Game game, GamePanel panel) {
            this.game = game;
            this.panel = panel;
        }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Obtenir les coordonnées du clic
        int x = e.getX() / panel.getCellSize();
        int y = e.getY() / panel.getCellSize();

        // Récupérer le joueur courant
        Player currentPlayer = game.getPlayers()[currentPlayerIndex];

        // Ajouter un point pour le joueur courant
        currentPlayer.insertPoint(x, y);

        // Créer un nouveau Node avec pour origine le dernier point ajouté
        int lastIndex = currentPlayer.getPoints().size() - 1;
        Node newNode = new Node(currentPlayer, lastIndex);
        currentPlayer.setNode(newNode);

        // Appeler une fonction du jeu (ajustée selon vos besoins)
        if(game.seekPlayerNode(currentPlayer)) {
            ArrayList<java.awt.Point> pointsWinner=newNode.getFiveHeritedPoints(currentPlayer);
            panel.setAlignedPoints(pointsWinner);
            victoryPanel=new VictoryPanel(currentPlayer.name,(JFrame) SwingUtilities.getWindowAncestor(panel),game);
            victoryPanel.showVictoryPanel(currentPlayer.name,(JFrame) SwingUtilities.getWindowAncestor(panel),game);
        }

        // Passer au joueur suivant
        currentPlayerIndex = (currentPlayerIndex + 1) % game.getPlayers().length;

        // Mettre à jour l'affichage
        panel.repaint();
    }
}
