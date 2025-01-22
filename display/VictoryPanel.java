package display;
import javax.swing.*;

import logic.Game;
import logic.Player;
import logic.Field;
import logic.Point;

import java.awt.*;
import java.util.ArrayList;

public class VictoryPanel extends JPanel {
    private String winnerName;
    private JFrame frame;
    private Game game;

    public VictoryPanel(String winnerName,JFrame frame,Game game) {
        this.winnerName = winnerName;
        this.frame=frame;
        this.game=game;
        setupVictoryPanel();
    }

    private void setupVictoryPanel() {
        setLayout(new BorderLayout());
        JLabel victoryMessage = new JLabel("Félicitations, " + winnerName + " a gagné !");
        victoryMessage.setFont(new Font("Arial", Font.BOLD, 15));
        victoryMessage.setHorizontalAlignment(SwingConstants.CENTER);
        victoryMessage.setVerticalAlignment(SwingConstants.CENTER);
        add(victoryMessage, BorderLayout.CENTER);

        JButton restartButton = new JButton("Rejouer");
        restartButton.addActionListener(e -> restartGame());
        add(restartButton, BorderLayout.SOUTH);
    }

    private void restartGame() {
        // Redémarrer le jeu (réinitialiser l'état)
        JOptionPane.showMessageDialog(this, "Le jeu redémarre !");
        // Ici, ajoutez la logique pour réinitialiser le jeu
        ArrayList<Point> pointsPlayer1 = new ArrayList<>();
        Player player1 = new Player("Rivo", pointsPlayer1, 1);

        ArrayList<Point> pointsPlayer2 = new ArrayList<>();
        Player player2 = new Player("Miora", pointsPlayer2, 2);

        Player[] players = {player1, player2};

        // Configuration du jeu
        Field field = new Field(30, 30);
        game=new Game(field, players);

        // Affichage
        
        
        frame.removeAll();
        frame.setResizable(false);
        GamePanel panel = new GamePanel(game);
        Listener listener = new Listener(game, panel);

        panel.addMouseListener(listener);
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    protected void showVictoryPanel(String playerName,JFrame frame,Game game) {
        JFrame victoryFrame = new JFrame("Victoire !");
        victoryFrame.setSize(250, 200);
        victoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        victoryFrame.add(new VictoryPanel(playerName,frame,game));
        victoryFrame.setVisible(true);
    }
    
}

