package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import interfaces.IBoxCollider;
import main.GamePanel;

public abstract class Entity implements IBoxCollider{
// Attributes
    public int xPosition, yPosition;
    public int speed;
    public Direction facing = Direction.DOWN;
    public Rectangle boxCollider = new Rectangle();
    protected GamePanel gamePanel;

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
    protected abstract void setDefaultValues();
    public abstract void update();
    public abstract void correctPosition();
    public abstract void createAnimList();
    protected abstract BufferedImage setAnimation();
    public abstract void draw(Graphics2D g);
}
