package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        boxCollider = new Rectangle(6, 16, gamePanel.getTileSize()-12, gamePanel.getTileSize()-16);
        setDefaultValues();
    }

    public void setDefaultValues() {
        xPosition = 100;
        yPosition = 100;
        speed = 4;
    }

    public void update() {
        playerMovement();
    }

    private void playerMovement() {
        if(keyHandler.upPressed == true) {
            yPosition -= speed;
        }
        if(keyHandler.downPressed == true) {
            yPosition += speed;
        }
        if(keyHandler.leftPressed == true) {
            xPosition -= speed;
        }
        if(keyHandler.rightPressed == true) {
            xPosition += speed;
        }

        collisionOn = false;
        gamePanel.collisionDetector.CheckTile(this);
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(xPosition, yPosition, gamePanel.getTileSize(), gamePanel.getTileSize());
    }
}
