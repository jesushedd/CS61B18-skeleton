package byog.algorithms;

import byog.Core.RandomUtils;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.Core.TileMatrixHelpers;
import byog.algorithms.position.Position;

import java.util.Random;



public class RoomsThenHallsGenerator {

    private static class Room {
        private final Position topLeftCorner;
        private final Position botRightCorner;
        private final int width;
        private final int height;
        private Room(Position topLeft, int inWidth, int inHeight ){
            topLeftCorner = topLeft;
            width = inWidth;
            height = inHeight;
            botRightCorner = new Position(topLeft.getXxPosition() + width, topLeft.getYyPosition() +height);

        }
    }
    private final Random random;
    private final TETile[][] WORLD;
    private final int Y_MAX;
    private final int X_MAX;
    private final int ROOM_WIDTH_MAX;
    private final int ROOM_MIN_LEN = 2;
    private final int ROOM_HEIGHT_MAX;



    
    private final float WIDTH_PROPORTION = (4F / 27);
    private final float HEIGHT_PROPORTION = (3F/12);
    public RoomsThenHallsGenerator(TETile [][] inWorld, long seed){

        if (TileMatrixHelpers.anyTilesIsNull(inWorld)){
            throw new IllegalArgumentException("All tiles should be non null");
        }
        
        int heightOfWorld = inWorld[0].length;
        int widthOfWorld = inWorld.length;
        random = new Random(seed);
        WORLD = inWorld;
        X_MAX = widthOfWorld - 1;
        Y_MAX = heightOfWorld - 1;
        ROOM_WIDTH_MAX =  (int) (WIDTH_PROPORTION * widthOfWorld);
        ROOM_HEIGHT_MAX = (int) (HEIGHT_PROPORTION * heightOfWorld);
    }


    private void createRandomHall(){
        //create position x, y at top left corner
        int x = RandomUtils.uniform(random, X_MAX + 1);
        int y = RandomUtils.uniform(random, Y_MAX+ 1);
        //random width
        int width = RandomUtils.uniform(random,ROOM_MIN_LEN, ROOM_WIDTH_MAX + 1);
        //random height
        int height = RandomUtils.uniform(random, ROOM_MIN_LEN, ROOM_HEIGHT_MAX + 1);
        //check is between world bounds
        int botRightX = x + width;
        int botRightY = y + height;
    }




    public void fillTest(){
        for (int y = 0; y < ROOM_HEIGHT_MAX ; y++) {
            for (int x = 0; x < ROOM_WIDTH_MAX; x++) {

                WORLD[x][y] = Tileset.FLOWER;
            }

        }
    }
}
