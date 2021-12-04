package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import entity.Cow;
import entity.Player;
import interfaces.IBoxCollider;
import item.Bucket;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
// Singleton
    private static GamePanel instance;
    public static synchronized GamePanel getInstance() {
        if (instance == null) {
            instance = new GamePanel();
        }
        return instance;
    }
// ATTRIBUTES

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
    Player player = new Player(this, keyHandler);
    Cow cow = new Cow(this);
    Bucket bucket = new Bucket();
    TileManager tileManager = TileManager.getInstance();
    public CollisionDetector collisionDetector = new CollisionDetector();
    public List<IBoxCollider> gameObjectsList = new ArrayList<IBoxCollider>();
    
 // Getters
    public Player getPlayer() {
        return player;
    }
    
    public Cow getCow() {
        return cow;
    }

// GETTERS/SETTERS
    public int getTileSize() {
        return tileSize;
    }

    public int getMaxScreenCol() {
        return maxScreenCol;
    }

    public int getMaxSCreenRow() {
        return maxSCreenRow;
    }


    // CONSTRUCTORS
    private GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.DARK_GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        loadGameObjectsList();
    }
    
    // METHODS
    private void loadGameObjectsList() {
        gameObjectsList.add(player);
        gameObjectsList.add(cow);
        gameObjectsList.add(bucket);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000/FPS;
        double nextDrawTime = System.currentTimeMillis() + drawInterval;

        onStart();

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

    private void onStart() {
        tileManager.createNewTileScreen();
    }

    public void update() {
        player.update();
        cow.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        // draw objects in correct order as instantiation order is like layering
        tileManager.draw(g2D);
        bucket.draw(g2D);
        cow.draw(g2D);
        player.draw(g2D);

        // make g2D free for efficiency
        g2D.dispose();
    }
}
