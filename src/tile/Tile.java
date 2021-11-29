package tile;

import java.awt.image.BufferedImage;

public class Tile {
// ATTRIBUTES
    protected String tilename;
    public BufferedImage image;
    public boolean collision = false;

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

    
}
