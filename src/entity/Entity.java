package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class Entity {
// Attributes
    public int xPosition, yPosition;
    public int speed;
    public Direction facing = Direction.DOWN;
    public Rectangle boxCollider = new Rectangle();

    // for Animation
    protected File animDir;
    protected List<BufferedImage> animList = new ArrayList<BufferedImage>();
    protected BufferedImage curAnim;
    protected boolean picSwitch;
    protected int animDelay = 12;
    protected int animCounter;

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    // Methods
    public abstract void createAnimList();
    public abstract void draw(Graphics2D g);
    public abstract void updateBoxCollider();
    public abstract void correctPosition();
}
