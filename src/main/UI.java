package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {
    GamePanel gamePanel;
    Font stdFont;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        stdFont = new Font("Arial", Font.PLAIN, 25);
    }

    public void draw(Graphics2D g2d) {
        g2d.setFont(stdFont);
        g2d.setColor(Color.WHITE);
        g2d.drawString("Player got Milk? "+ (gamePanel.player.gotMilk ? "yes" : "no"), 50, 50);
        g2d.drawString("Cow ready? "+ (gamePanel.cow.gotMilk ? "yes" : "no"), 50, 100);
    }
}
