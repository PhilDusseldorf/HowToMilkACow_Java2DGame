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
    public List<Tile> tileList = new ArrayList<Tile>();
    GamePanel gamePanel;

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

    public void draw(Graphics2D g2D) {

    }
}
