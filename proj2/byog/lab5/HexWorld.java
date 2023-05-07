package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    public static void addHexagon(TETile[][] world, int x, int y, int length) {
        int i;
        Random rand = new Random();
        int seed = Math.abs(rand.nextInt(100)) % 5;
        for(i = 0; i < length; i++) {
            addHexagonHelper(world, x + length - i - 1, y + i,
                    length + i * 2, seed);
        }
        for(i = 0; i < length; i++) {
            addHexagonHelper(world, x + i, y + length + i,
                    length * 3 - (i + 1) * 2, seed);
        }
    }

    /*
    A helper function of addHexagon, it just adds a vertical line of flower form (x, y) and length
    */
    private static void addHexagonHelper(TETile[][] world, int x, int y, int length, int seed) {
        for(int i = 0; i < length; i++) {
            switch (seed) {
                case 0:
                    world[x + i][y] = TETile.colorVariant(Tileset.FLOWER, 128, 0,
                            0, new Random());
                    break;
                case 1:
                    world[x + i][y] = Tileset.SAND;
                    break;
                case 2:
                    world[x + i][y] = TETile.colorVariant(Tileset.GRASS, 0, 128,
                            0, new Random());
                    break;
                case 3:
                    world[x + i][y] = TETile.colorVariant(Tileset.TREE, 0, 128,
                            0, new Random());
                    break;
                case 4:
                    world[x + i][y] = TETile.colorVariant(Tileset.MOUNTAIN, 128, 128,
                            128, new Random());
                    break;
            }
        }
    }

    public static void main(String[] args) {
        // main function is to test functionality above
        int WIDTH = 60;
        int HEIGHT = 30;
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        // addHexagon test
        HexWorld.addHexagon(world, 0, 0, 2);
        HexWorld.addHexagon(world, 6, 0, 3);
        HexWorld.addHexagon(world, 15, 0, 4);
        HexWorld.addHexagon(world, 27, 0, 5);

        // draws the world to the screen
        ter.renderFrame(world);
    }
}
