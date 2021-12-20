package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

import main.CollisionDetector;
import main.GamePanel;

public class Cow extends NPC{
    int tileSize = 48;

    // for random movement
    int moveDuration;
    int moveCounter;

    public Cow(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        setDefaultValues();
        createAnimList();
    }

    @Override
    protected void setDefaultValues() {
        xPosition = 400;
        yPosition = 300;
        speed = 1;
        animDir = new File("res/entity/cow");
        // set the size of the boxCollider
        boxCollider.add(10*3, 8*3);
    }

    public void update() {
        entityMovement();
        updateBoxCollider();
    }

    private void entityMovement() {
        // detect collisions first
        if(CollisionDetector.getInstance().CheckTile(this) || CollisionDetector.getInstance().CheckEntityCollision(this, gamePanel.gameObjectsList)) {
            correctPosition();
        } else {
            // check if duration is still on, otherwise set a new duration
            if (moveCounter >= moveDuration) {
                setFacing();
                moveDuration = ThreadLocalRandom.current().nextInt(50, 300 + 1);
                moveCounter = 0;
            } else {
                moveCounter++;
            }
            moveEntity();
        }
    }

    @Override
    public void correctPosition() {
        // Inverts the direction
        switch (facing) {
            case UP:
                facing = Direction.DOWN;
                break;
            case DOWN:
                facing = Direction.UP;
                break;
            case RIGHT:
                facing = Direction.LEFT;
                break;
            case LEFT:
                facing = Direction.RIGHT;
                break;
            default:
                facing = Direction.DOWN;
                break;
        }
        moveEntity();
    }

    private void setFacing() {
        int switchCondition;
        switchCondition = ThreadLocalRandom.current().nextInt(1, 4 + 1);

        switch (switchCondition) {
            case 1:
                facing = Direction.DOWN;
                break;
            case 2:
                facing = Direction.UP;
                break;
            case 3:
                facing = Direction.LEFT;
                break;
            case 4:
                facing = Direction.RIGHT;
                break;
            default:
                facing = Direction.DOWN;
                break;
        }
    }

    private void moveEntity() {
        if(facing == Direction.UP) {
            yPosition -= speed;
        }
        if(facing == Direction.DOWN) {
            yPosition += speed;

        }
        if(facing == Direction.LEFT) {
            xPosition -= speed;
        }
        if(facing == Direction.RIGHT) {
            xPosition += speed;
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
    public void updateBoxCollider() {
        // adjusts the position of the boxCollider relativ to the entity's Position
        boxCollider.x = xPosition+(3*4);
        boxCollider.y = yPosition+(6*4);
    }

    @Override
    public void draw(Graphics2D g) {
        curAnim = setAnimation();
        g.drawImage(curAnim, xPosition, yPosition, tileSize, tileSize, null);
    }

    @Override
    public void pushAway(Direction facing) {
        if(facing == Direction.UP) {
            yPosition -= speed;
        }
        if(facing == Direction.DOWN) {
            yPosition += speed;
        }
        if(facing == Direction.LEFT) {
            xPosition -= speed;
        }
        if(facing == Direction.RIGHT) {
            xPosition += speed;
        }
    }

    @Override
    public void interact() {
        // TODO milk the cow
        System.out.println("Player interacted with cow");
    }
}
