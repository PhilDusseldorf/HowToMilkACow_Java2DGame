package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public static boolean spaceBar;

    @Override
    public void keyTyped(KeyEvent e) {
        // no actions integrated yet
    }

    
    @Override
    public void keyPressed(KeyEvent e) {
        handleMovement(e, true);
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        handleMovement(e, false);
    }
    
    private void handleMovement(KeyEvent e, boolean state) {
        int code = e.getKeyCode();
        
        // directions
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = state;
        }
        
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = state;
        }
        
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = state;
        }
        
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = state;
        }

        // actions
        if (code == KeyEvent.VK_SPACE) {
            spaceBar = state;
        }
    }
}
