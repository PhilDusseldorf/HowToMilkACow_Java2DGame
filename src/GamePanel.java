import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    // Screen Settings
    final int originalTileSize = 16;   // 16 x 16 per tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale;  // 48 x 48 tile
    final int maxScreenCol = 16;
    final int maxSCreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;    // 768 pixels
    final int screenHeight = tileSize * maxSCreenRow;   // 576 pixels

    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.DARK_GRAY);
        this.setDoubleBuffered(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while(gameThread != null) {
            System.out.println("The game loop is running");
            // Update: update the information like character positions
            update();
            // Draw: draw the screen with updated information
            repaint();
        }
    }

    public void update() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.WHITE);
        g2.fillRect(100, 100, tileSize, tileSize);
        g2.dispose();
    }
}
