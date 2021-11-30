package tile;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Tile {
// ATTRIBUTES
    protected String tilename;
    protected BufferedImage image;
    protected boolean collision = false;
    public Rectangle boxCollider;

// GETTERS/SETTERS
    public String getTilename() {
        return tilename;
    }
    public void setTilename(String tilename) {
        this.tilename = tilename;
    }
    public boolean isCollision() {
        return collision;
    }
    public void setCollision(boolean collision) {
        this.collision = collision;
    }
    public Tile() {
        boxCollider = new Rectangle(0, 0, 48, 48);
    }
}
