package main;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

import entity.Entity;
import entity.Entity.Direction;
import interfaces.IBoxCollider;
import item.Item;
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

    public boolean CheckEntityCollision(Entity myself, List<IBoxCollider> list) {
        // create the boxColliders
        Rectangle selfBoxCollider = myself.boxCollider;
        Rectangle otherBoxCollider = null;
        // prepare the other BoxCollider
        for (IBoxCollider other : list) {
            if (other instanceof Entity) {
                otherBoxCollider = ((Entity)other).boxCollider;
                if (otherBoxCollider.equals(selfBoxCollider)) {
                    otherBoxCollider = null;
                }
            }

            if (other instanceof Item) {
                otherBoxCollider = ((Item)other).boxCollider;
            }

            if(otherBoxCollider != null && selfBoxCollider.intersects(otherBoxCollider)) {
                //System.out.println(myself.getClass().getName() + " collides with " + other.getClass().getName());
                return true;
            }
        }
        return false;
    }

    public static IBoxCollider GetBoxColliderObjectForInteraction(Entity myself, List<IBoxCollider> list) {
        // object to be returned
        IBoxCollider returnObject = null;
        // create the boxColliders
        Rectangle selfBoxCollider = myself.boxCollider;
        Rectangle otherBoxCollider = null;
        // locate player interaction zone
        Point interactionPoint = new Point();
        int gap = 12;
        if (myself.facing == Direction.DOWN) {
            interactionPoint.setLocation(myself.xPosition + gap*2, myself.yPosition + gap*4);
        }
        if (myself.facing == Direction.UP) {
            interactionPoint.setLocation(myself.xPosition + gap*2, myself.yPosition - gap);
        }
        if (myself.facing == Direction.LEFT) {
            interactionPoint.setLocation(myself.xPosition - gap, myself.yPosition + gap*2);
        }
        if (myself.facing == Direction.RIGHT) {
            interactionPoint.setLocation(myself.xPosition + gap*4, myself.yPosition + gap*2);
        }
        System.out.println("interactionPoint at " + interactionPoint.x + "/" + interactionPoint.y);
        System.out.println("Player at " + myself.xPosition + "/" + myself.yPosition);
        // prepare the other BoxCollider
        for (IBoxCollider other : list) {
            if (other instanceof Entity) {
                otherBoxCollider = ((Entity)other).boxCollider;
                if (otherBoxCollider.equals(selfBoxCollider)) {
                    otherBoxCollider = null;
                }
            }

            if (other instanceof Item) {
                otherBoxCollider = ((Item)other).boxCollider;
            }

            if(otherBoxCollider != null && otherBoxCollider.contains(interactionPoint)) {
                returnObject = other;
            }
        }
        return returnObject;
    }
}
