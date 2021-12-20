package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import interfaces.IBoxCollider;
import item.Item;
import main.CollisionDetector;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity { 
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefaultValues();
        createAnimList();
    }

    public void setDefaultValues() {
        xPosition = 100;
        yPosition = 100;
        speed = 4;
        animDir = new File("res/entity/player");
        // set the size of the boxCollider
        boxCollider.add(10*3, 8*3);
    }

    public void update() {
        entityMovement();
        updateBoxCollider();
        checkForAction();
    }
    
    private void checkForAction() {
        if (KeyHandler.spaceBar) {
            IBoxCollider interactionObject = CollisionDetector.GetBoxColliderObjectForInteraction(this, gamePanel.gameObjectsList);

            System.out.println("Interaction Object is " + interactionObject);
        }
    }

    private void entityMovement() {
        // detect collisions first
        if(CollisionDetector.getInstance().CheckTile(this)) {
            correctPosition();
        } else if (CollisionDetector.getInstance().CheckEntityCollision(this, gamePanel.gameObjectsList)) {
            IBoxCollider otherObject = returnCollisionObject(gamePanel.gameObjectsList);
            if (otherObject instanceof Entity) {
                ((Entity)otherObject).pushAway(facing);
            }
            if (otherObject instanceof Item) {
                if (((Item)otherObject).isCollectable()) {
                    collectItem();
                } else {
                    correctPosition();
                }
            }
        } else {
            setFacing();
            moveEntity();
        }
    }

    private void collectItem() {
        // TODO: Collect an item and destroy the object in game
    }

    private IBoxCollider returnCollisionObject(List<IBoxCollider> list) {
        // create the boxCollider
        Rectangle otherBoxCollider = null;
        // prepare the other BoxCollider
        for (IBoxCollider other : list) {
            if (other instanceof Entity) {
                otherBoxCollider = ((Entity)other).boxCollider;
                if (otherBoxCollider.equals(boxCollider)) {
                    otherBoxCollider = null;
                }
            }

            if (other instanceof Item) {
                otherBoxCollider = ((Item)other).boxCollider;
            }

            if(otherBoxCollider != null && boxCollider.intersects(otherBoxCollider)) {
                //System.out.println(self.getClass().getName() + " collides with " + other.getClass().getName());
                return other;
            }
        }
        return null;
    }
    
    @Override
    public void correctPosition() {
        if(facing == Direction.UP) {
            yPosition += speed;
        }
        if(facing == Direction.DOWN) {
            yPosition -= speed;
        }
        if(facing == Direction.LEFT) {
            xPosition += speed;
        }
        if(facing == Direction.RIGHT) {
            xPosition -= speed;
        }
    }

    private void setFacing() {
        if(keyHandler.upPressed == true) {
            facing = Direction.UP;
        }
        else if(keyHandler.downPressed == true) {
            facing = Direction.DOWN;
        }
        else if(keyHandler.leftPressed == true) {
            facing = Direction.LEFT;
        }
        else if(keyHandler.rightPressed == true) {
            facing = Direction.RIGHT;
        }
        // System.out.println("Facing: " + facing);
    }
    
    private void moveEntity() {
        // move the player by press of key
        if(keyHandler.upPressed == true) {
            yPosition -= speed;
        }
        else if(keyHandler.downPressed == true) {
            yPosition += speed;
        }
        else if(keyHandler.leftPressed == true) {
            xPosition -= speed;
        }
        else if(keyHandler.rightPressed == true) {
            xPosition += speed;
        }
    }
    
    @Override
    protected BufferedImage setAnimation() {
        // count when to switch to the second picture
        animCounter++;
        if (animCounter >= animDelay) {
            picSwitch = !picSwitch;
            animCounter = 0;
        }
        // choose the correct animation by where the entity is facing
        switch (facing) {
            default:
            if (picSwitch) {
                return animList.get(0);
            } else {
                return animList.get(1);
            }
            case LEFT:
            if (picSwitch) {
                return animList.get(2);
            } else {
                return animList.get(3);
            }
            case RIGHT:
            if (picSwitch) {
                return animList.get(4);
            } else {
                return animList.get(5);
            }
            case UP:
            if (picSwitch) {
                return animList.get(6);
            } else {
                return animList.get(7);
            }
        }
    }
    
    @Override
    public void createAnimList() {
        if(animDir.isDirectory()) {
            for (File anim : animDir.listFiles()) {
                try {
                    BufferedImage newImage = ImageIO.read(anim);
                    animList.add(newImage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void updateBoxCollider() {
        // adjusts the position of the boxCollider relativ to the playerPosition
        boxCollider.x = xPosition+(2*4);
        boxCollider.y = yPosition+(6*4);
    }

    @Override    
    public void draw(Graphics2D g) {
        //Placeholder Square
        // g.setColor(Color.WHITE);
        // g.fillRect(xPosition, yPosition, gamePanel.getTileSize(), gamePanel.getTileSize());
        curAnim = setAnimation();
        g.drawImage(curAnim, xPosition, yPosition, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
    }

    @Override
    public void pushAway(Direction facing) {
        // not in use
        
    }
}