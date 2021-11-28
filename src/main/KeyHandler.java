package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
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

        if(code == KeyEvent.VK_W) {
            upPressed = state;
        }

        if(code == KeyEvent.VK_A) {
            leftPressed = state;
        }

        if(code == KeyEvent.VK_S) {
            downPressed = state;
        }

        if(code == KeyEvent.VK_D) {
            rightPressed = state;
        }
    }
}
