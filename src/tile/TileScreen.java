package tile;

import main.GamePanel;

public class TileScreen {
    GamePanel gamePanel;
    TileManager tileManager;
    Tile[][] tileMatrix;

    public TileScreen (TileManager tileManager, GamePanel gamePanel) {
        this.tileManager = tileManager;
        this.gamePanel = gamePanel;
        tileMatrix = new Tile[gamePanel.getMaxSCreenRow()][gamePanel.getMaxScreenCol()];
    }
}
