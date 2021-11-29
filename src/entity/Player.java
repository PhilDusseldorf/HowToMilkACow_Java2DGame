package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.KeyHandler;
import main.GamePanel;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
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
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(xPosition, yPosition, gamePanel.getTileSize(), gamePanel.getTileSize());
    }
}
