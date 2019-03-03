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
public class HexWorld {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 40;
    private static position p2 = new position(30, 0);
    private static final position p3 = new position(30, 5);
    private static final position p4 = new position(30, 15);


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


        TETile t = new TETile('a', Color.white, Color.black, "a");
        HexWorld.addHexagon(worldHexagon, p2, 2, t);
        HexWorld.addHexagon(worldHexagon, p3, 3, t);
        HexWorld.addHexagon(worldHexagon, p4, 4, t);

        me.renderFrame(worldHexagon);
    }

    /** T is tile, p is lower left corner of the hexagon, s is length of the hexagon. */
    public static void addHexagon(TETile[][] w, position p, int s, TETile t) {
        TETile[][] world = w;
        for (int row = 0; row < s; row ++) {
            for (int col = s - row - 1; col <= 2*s + row - 2; col ++) {
                world[col + p.yPos][row + p.xPos] = t;
            }
        }

        for (int row = s; row < 2 * s; row ++) {
            for (int col = row - s; col <= 4*s - row - 3; col ++) {
                world[col + p.yPos][row + p.xPos] = t;
            }
        }
    }
}
