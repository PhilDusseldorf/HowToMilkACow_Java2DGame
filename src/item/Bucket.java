package item;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class Bucket extends Item {
    // Attributes
    public boolean isFilled = false;
    
    // Constructors
    public Bucket() {
        this.tileSize = 48;
        setDefaultValues();
        createAnimList();
    }
    
    // Methods
    @Override
    public void update() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateBoxCollider() {
        boxCollider.x = xPosition;
        boxCollider.y = yPosition;
    }

    @Override
    protected void setDefaultValues() {
        itemName = "My Bucket";
        xPosition = 300;
        yPosition = 300;

        // for collisions
        collidable = true;
        boxCollider.add(tileSize, tileSize);
        updateBoxCollider();
        
        // set animation attributes
        imageDir = new File("res/item");
    }

    @Override
    protected BufferedImage setAnimation() {
        if (isFilled) {
            return imageList.get(1);
        } else {
            return imageList.get(0);
        }
    }


    @Override
    public void correctPosition() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void createAnimList() {
        if(imageDir.isDirectory()) {
            for (File anim : imageDir.listFiles()) {
                // go through all files and see if they contain the class name
                if (anim.getName().contains(this.getClass().getSimpleName().toLowerCase())) {
                    try {
                        BufferedImage newImage = ImageIO.read(anim);
                        imageList.add(newImage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        curAnim = setAnimation();
        g.drawImage(curAnim, xPosition, yPosition, tileSize, tileSize, null);
    }
}
