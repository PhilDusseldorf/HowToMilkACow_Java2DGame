package item;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import interfaces.IBoxCollider;

public abstract class Item implements IBoxCollider {
// ATTRIBUTES
    protected String itemName;
    public int xPosition, yPosition;
    protected int tileSize;

    // for collisions
    public Rectangle boxCollider = new Rectangle();
    protected boolean collidable = false;
    protected boolean collectable = false;

    public boolean isCollectable() {
        return collectable;
    }
    public void setCollectable(boolean isCollectable) {
        this.collectable = isCollectable;
    }

    // for animation
    protected File imageDir;
    protected List<BufferedImage> imageList = new ArrayList<BufferedImage>();
    protected BufferedImage curAnim;

    // Methods
    protected abstract void setDefaultValues();
    protected abstract BufferedImage setAnimation();
    public abstract void update();
    public abstract void correctPosition();
    public abstract void createAnimList();
    public abstract void draw(Graphics2D g);
    public abstract void interact();
}
