package main;

import java.awt.Rectangle;

import entity.Entity;
import tile.Tile;
import tile.TileManager;
import tile.TileScreen;

public class CollisionDetector {
    // Singleton
    private static CollisionDetector instance;
    public static synchronized CollisionDetector getInstance() {
        if (instance == null) {
            instance = new CollisionDetector();
        }
        return instance;
    }

    // Attributes
    TileScreen tileScreen = TileManager.getInstance().getTileScreen();

    // Methods
    public boolean CheckTile(Entity entity) {
        int tileSize = GamePanel.getInstance().getTileSize();
        // prepare the entity
        Rectangle entityBoxCollider = entity.boxCollider;

        // lazy and slow way: iterate all tiles all the time
        for (int row = 0; row < tileScreen.getTileMatrix().length; row++) {
            for (int col = 0; col < tileScreen.getTileMatrix()[row].length; col++) {
                Tile curTile = tileScreen.getTileMatrix()[row][col];
                if(curTile.isCollidable()) {
                    if(entityBoxCollider.intersects(new Rectangle(col * tileSize, row * tileSize, tileSize, tileSize))) {
                        // System.out.println("At " + row + "/" + col + ":");
                        // System.out.println("Collision detected");
                        return true;
                    }
                }
            }
        } 
        return false;
    }

    public boolean CheckEntityCollision(Entity entity) {
        // prepare the entity
        Rectangle entityBoxCollider = entity.boxCollider;

        return false;
    }
}
