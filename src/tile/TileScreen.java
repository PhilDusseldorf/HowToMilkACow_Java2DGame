package tile;

import main.GamePanel;

public class TileScreen {
// Attributes
    GamePanel gamePanel = GamePanel.getInstance();
    TileManager tileManager = TileManager.getInstance();
    Tile[][] tileMatrix;

// Getters/Setters
    public Tile[][] getTileMatrix() {
        return tileMatrix;
    }

    public void setTileMatrix(Tile[][] tileMatrix) {
        this.tileMatrix = tileMatrix;
    }

// Constructors
    public TileScreen () {
        tileMatrix = new Tile[gamePanel.getMaxSCreenRow()][gamePanel.getMaxScreenCol()];
    }
}
