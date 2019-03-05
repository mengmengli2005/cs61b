package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.awt.*;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld2 {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 40;
    private static final long SEED = 2345654;
    private static final Random RANDOM = new Random(SEED);

    public static void main(String[] args) {
        TERenderer me = new TERenderer();
        me.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] worldHexagon = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                worldHexagon[x][y] = Tileset.NOTHING;
            }
        }

        position[] p = HexWorld2.addHexPos(19);
        HexWorld2.AddHexagons(worldHexagon, p);
        me.renderFrame(worldHexagon);
    }

    public static position[] addHexPos(int m) {
        position[] p = new position[m];
        int n = 0;
        for (int i = 0; i < 3; i ++) {
            for (int j = 0; j < i + 3; j ++) {
                if (i == 0) {p[n] = new position(6 * (j + 1), 5 * i);
                }else if (i == 1) {p[n] = new position(6 * j + 3, 5 * i);
                }else {p[n] = new position(6 * j, 5 * i);}
                n = n + 1;
            }
        }
        for (int i = 3; i < 5; i ++) {
            for (int j = 0; j < 7 - i; j ++) {
                if (i == 4) {p[n] = new position(6 * (j + 1), 5 * i);
                }else {p[n] = new position(6 * j + 3, 5 * i);}
                n = n + 1;
            }
        }
        return p;
    }

    public static void AddHexagons(TETile[][] w, position[] p) {
        for (int i = 0; i < p.length; i ++) {
            TETile t = randomTile();
            HexWorld.addHexagon(w, p[i], 3, t);
        }
    }
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(3);
        switch (tileNum) {
            case 0: return Tileset.TREE;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.MOUNTAIN;
            default: return Tileset.MOUNTAIN;
        }
    }
}
