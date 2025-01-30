package display;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;
import java.util.ArrayList;
import logic.Game;
import logic.Player;
import logic.Point;

public class GamePanel extends JPanel {
    private Game game;
    private int cellSize = 20;
    private ArrayList<java.awt.Point> alignedPoints = new ArrayList<>();
    JButton suggestion;
    
    
    public GamePanel(Game game) {
        this.game = game;
        this.setPreferredSize(new Dimension(
            game.getField().getWidth() * cellSize,
            game.getField().getLength() * cellSize
        ));
        //JButton suggestion=new JButton("Suggestion");
        //suggestion.addActionListener(e -> game.getPlayers()[0].getNode().suggest(game.getPlayers()[0]));
        //add(suggestion, BorderLayout.SOUTH);
    }

    public JButton getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(JButton suggestion) {
        this.suggestion = suggestion;
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setAlignedPoints(ArrayList<java.awt.Point> alignedPoints) {
        this.alignedPoints = alignedPoints;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dessiner la grille
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i <= game.getField().getWidth(); i++) {
            g.drawLine(i * cellSize, 0, i * cellSize, getHeight());
        }
        for (int i = 0; i <= game.getField().getLength(); i++) {
            g.drawLine(0, i * cellSize, getWidth(), i * cellSize);
        }

        // Dessiner les points des joueurs
        Player[] players = game.getPlayers();
        Color[] playerColors = {Color.RED, Color.BLUE}; // Couleurs des joueurs

        for (int i = 0; i < players.length; i++) {
            g.setColor(playerColors[i % playerColors.length]);
            for (Point point : players[i].getPoints()) {
                int x = (int) point.getX() * cellSize;
                int y = (int) point.getY() * cellSize;
                g.fillOval(x, y, cellSize, cellSize);
            }
        }

        // Dessiner une ligne pour les points alignés
        if (!alignedPoints.isEmpty()) {
            g.setColor(Color.BLACK);
            for (int i = 0; i < alignedPoints.size() - 1; i++) {
                java.awt.Point p1 = alignedPoints.get(i);
                java.awt.Point p2 = alignedPoints.get(i + 1);
                Graphics2D g2= (Graphics2D)g;
                g2.setStroke(new BasicStroke(10));
                g2.drawLine(
                    (int) p1.getX() * cellSize + cellSize / 2,
                    (int) p1.getY() * cellSize + cellSize / 2,
                    (int) p2.getX() * cellSize + cellSize / 2,
                    (int) p2.getY() * cellSize + cellSize / 2
                );
            }
        }
    }
}
