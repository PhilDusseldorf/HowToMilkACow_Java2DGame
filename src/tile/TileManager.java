package tile;

import java.awt.Graphics2D;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
// Singleton
    private static TileManager instance;
    public static synchronized TileManager getInstance() {
        if (instance == null) {
            instance = new TileManager();
        }
        return instance;
    }

// ATTRIBUTES
    static final File tileDir = new File("res/tiles");
    private List<Tile> tileList = new ArrayList<Tile>();
    private TileScreen tileScreen;

// Getters/Setters
    public TileScreen getTileScreen() {
        return tileScreen;
    }

    public void setTileScreen(TileScreen tileScreen) {
        this.tileScreen = tileScreen;
    }

    // CONSTRUCTORS
    public TileManager() {
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
                        newTile.collidable = true;
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
        tileScreen = new TileScreen();
        // iterate through tileMatrix and fill it with grassland
        for (int row = 0; row < tileScreen.tileMatrix.length; row++) {
            for (int col = 0; col < tileScreen.tileMatrix[row].length; col++) {
                int tileIndex;
                if (row == 0 || 
                        col == 0 || 
                        col == tileScreen.tileMatrix[row].length-1 ||
                        row == tileScreen.tileMatrix.length-1) {
                    tileIndex = 5;
                } else {
                    tileIndex = 2;
                }
                tileScreen.tileMatrix[row][col] = tileList.get(tileIndex);
            }
        }

    }
    
    public void draw(Graphics2D g2D) {
        // iterate through tileMatrix and draw every tile to g2D
        for (int row = 0; row < tileScreen.tileMatrix.length; row++) {
            for (int col = 0; col < tileScreen.tileMatrix[row].length; col++) {
                g2D.drawImage(tileScreen.tileMatrix[row][col].image, GamePanel.getInstance().getTileSize() *col, GamePanel.getInstance().getTileSize() *row, GamePanel.getInstance().getTileSize(), GamePanel.getInstance().getTileSize(), null);
            }
        }
    }
}
