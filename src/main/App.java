package main;
import javax.swing.JFrame;

public class App {
    public static void main(String[] args) {
        // make a window frame
        JFrame window = new JFrame();
        window.setTitle("2D Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        
        GamePanel gamePanel = GamePanel.getInstance();
        // make a Panel and add it to the window
        window.add(gamePanel);

        window.pack();

        // position the window and make it visible
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    
        gamePanel.startGameThread();
    }
}
