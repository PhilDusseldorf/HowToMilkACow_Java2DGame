package tile;

import java.awt.image.BufferedImage;

public class Tile {
// ATTRIBUTES
    protected String tilename;
    protected BufferedImage image;
    protected boolean collidable = false;

// GETTERS/SETTERS
    public String getTilename() {
        return tilename;
    }
    public void setTilename(String tilename) {
        this.tilename = tilename;
    }
    public boolean isCollidable() {
        return collidable;
    }
    public void setCollidable(boolean collidable) {
        this.collidable = collidable;
    }

// Constructors
}
