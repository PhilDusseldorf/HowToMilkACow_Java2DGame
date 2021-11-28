package main;
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

    // FPS for the game
    final int FPS = 30;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;

    // Set player's default position
    int playerPositionX = 100;
    int playerPositionY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.DARK_GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000/FPS;
        double nextDrawTime = System.currentTimeMillis() + drawInterval;

        while(gameThread != null) {
            // Update: update the information like character positions
            update();
            // Draw: draw the screen with updated information
            repaint();

            try {
                double remainingTime = nextDrawTime - System.currentTimeMillis();
                if(remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        if(keyHandler.upPressed == true) {
            playerPositionY -= playerSpeed;
        }
        else if(keyHandler.downPressed == true) {
            playerPositionY += playerSpeed;
        }
        else if(keyHandler.leftPressed == true) {
            playerPositionX -= playerSpeed;
        }
        else if(keyHandler.rightPressed == true) {
            playerPositionX += playerSpeed;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.WHITE);
        g2.fillRect(playerPositionX, playerPositionY, tileSize, tileSize);
        g2.dispose();
    }
}
