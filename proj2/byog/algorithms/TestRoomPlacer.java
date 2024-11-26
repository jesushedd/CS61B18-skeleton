package byog.algorithms;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.Core.TileMatrixHelpers;

import java.io.Writer;


public class TestRoomPlacer {
    static int WIDTH = 80;
    static int HEIGHT =  30;

    static TERenderer tet = new TERenderer();
    static TERenderer tet2 = new TERenderer();

    public static void main(String[] args) throws InterruptedException {
        tet.initialize(WIDTH,HEIGHT);
        TETile [][] world = new TETile[WIDTH][HEIGHT];
        TileMatrixHelpers.fillVoids(world);
        RoomsThenHallsGenerator roomTest =  new RoomsThenHallsGenerator(world,5037);
        for (int i = 0; i < 5; i++) {
            roomTest.setTiles();
            tet.renderFrame(world);
            Thread.sleep(5);

        }
        roomTest.fillHalls();
        tet.renderFrame(world);
    }
}
