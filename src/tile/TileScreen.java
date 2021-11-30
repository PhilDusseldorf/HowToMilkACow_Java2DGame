package tile;

import main.GamePanel;

public class TileScreen {
    GamePanel gamePanel = GamePanel.getInstance();
    TileManager tileManager = TileManager.getInstance();
    Tile[][] tileMatrix;

    public TileScreen () {
        tileMatrix = new Tile[gamePanel.getMaxSCreenRow()][gamePanel.getMaxScreenCol()];
    }
}
