package main;
import javax.swing.JFrame;

public class App {
    public static void main(String[] args) {
        // make a window frame
        JFrame window = new JFrame();
        window.setTitle("2D Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        // make a Panel and add it to the window
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        // position the window and make it visible
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    
        gamePanel.startGameThread();
    }
}
