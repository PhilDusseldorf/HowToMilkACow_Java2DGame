package tile;

import java.awt.Graphics2D;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
// ATTRIBUTES
    static final File tileDir = new File("res/tiles");
    private List<Tile> tileList = new ArrayList<Tile>();
    GamePanel gamePanel;
    TileScreen tileScreen;

// CONSTRUCTORS
    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        createTileList();
    }

// METHODS
    public void createTileList() {
        if(tileDir.isDirectory()) {
            for (final File tileFile : tileDir.listFiles()) {
                try {
                    Tile newTile = new Tile();
                    newTile.tilename = tileFile.getName().replace(".png", "");
                    newTile.image =ImageIO.read(tileFile);
                    if (newTile.tilename.contains("collide")) {  // all non-walkable tiles HAVE to start with collide
                        newTile.collision = true;
                    }
                    tileList.add(newTile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void createNewTileScreen() {
        // create a new TileScreen
        tileScreen = new TileScreen(this, gamePanel);
        // iterate through tileMatrix and fill it with grassland
        for (int row = 0; row < tileScreen.tileMatrix.length; row++) {
            for (int col = 0; col < tileScreen.tileMatrix[row].length; col++) {
                tileScreen.tileMatrix[row][col] = tileList.get(2);
            }
        }

    }
    
    public void draw(Graphics2D g2D) {
        // iterate through tileMatrix and draw every tile to g2D
        for (int row = 0; row < tileScreen.tileMatrix.length; row++) {
            for (int col = 0; col < tileScreen.tileMatrix[row].length; col++) {
                //System.out.println(tileScreen.tileMatrix[row][col].tilename + (col+1));
                g2D.drawImage(tileScreen.tileMatrix[row][col].image, gamePanel.getTileSize() *col, gamePanel.getTileSize() *row, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
            }
        }
    }
}
