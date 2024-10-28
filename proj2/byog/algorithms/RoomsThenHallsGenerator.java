package byog.algorithms;

import byog.Core.RandomUtils;
import byog.TileEngine.TETile;
import byog.Core.TileMatrixHelpers;
import byog.TileEngine.Tileset;
import byog.algorithms.position.Position;

import java.util.Random;



public class RoomsThenHallsGenerator implements GenAlgorithm{

    private static class Room {
        private final Position botLeftCorner;
        private final Position topRightCorner;
        private Room(Position botLeft, Position topRight ){
            botLeftCorner = botLeft;
            topRightCorner = topRight;
        }

        private int getWidth(){
            return topRightCorner.getXxPosition() - botLeftCorner.getXxPosition();
        }

        private int getHeight(){
            return topRightCorner.getYyPosition() - botLeftCorner.getYyPosition();
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
    @Override
    public void setTiles(){
        Room room = getRandomRoom();
        placeRoom(room);
    }

    @Override
    public int getUsedArea() {
        return 0;
    }

    private Room getRandomRoom(){
        //create position x, y at top left corner
        int x = RandomUtils.uniform(random, X_MAX + 1);
        int y = RandomUtils.uniform(random, Y_MAX+ 1);

        Position botLeftCorner = new Position(x, y);
        //getting a valid top right corner position from left corner
        Position topRightCorner = null;
        while (!isWithinBounds(topRightCorner)){
            topRightCorner = randomBotCorner(botLeftCorner);
        }
        return new Room(botLeftCorner, topRightCorner);
    }

    private boolean isWithinBounds(Position pos){
        if (pos == null){
            return false;
        }
        return (pos.getXxPosition() <= X_MAX && pos.getYyPosition() <= Y_MAX);
    }

    private Position randomBotCorner(Position botCorner){
        //random width
        int width = RandomUtils.uniform(random,ROOM_MIN_LEN, ROOM_WIDTH_MAX + 1);
        //random height
        int height = RandomUtils.uniform(random, ROOM_MIN_LEN, ROOM_HEIGHT_MAX + 1);

        return new Position(botCorner.getXxPosition() + width , botCorner.getYyPosition() + height);
    }




    private void placeRoom(Room roomToPlace){
        // Get starting coordinates
        int xStart = roomToPlace.botLeftCorner.getXxPosition();
        int yStart = roomToPlace.botLeftCorner.getYyPosition();
        //fill tiles
        for (int y = yStart ; y <= yStart + roomToPlace.getHeight(); y++) {
            for (int x = xStart; x < xStart + roomToPlace.getWidth()  ; x++) {
                WORLD[x][y] = Tileset.FLOOR;
            }
        }
        }


    }

