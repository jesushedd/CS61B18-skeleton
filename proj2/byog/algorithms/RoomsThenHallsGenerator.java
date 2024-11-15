package byog.algorithms;

import byog.Core.RandomUtils;
import byog.TileEngine.TETile;
import byog.Core.TileMatrixHelpers;
import byog.TileEngine.Tileset;
import byog.algorithms.position.Position;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;



public class RoomsThenHallsGenerator implements GenAlgorithm{

    private static class Room {
        private final Position botLeftCorner;
        private final Position topRightCorner;

        private final Position centroid;
        private Room(Position botLeft, Position topRight ){
            botLeftCorner = botLeft;
            topRightCorner = topRight;
            centroid = calculateCentroid(botLeft, topRight);
        }

        private int getWidth(){
            return topRightCorner.getXxPosition() - botLeftCorner.getXxPosition();
        }

        private int getHeight(){
            return topRightCorner.getYyPosition() - botLeftCorner.getYyPosition();
        }


        private Position getCentroid(){
            return centroid;
        }

        private Position calculateCentroid(Position botLeft, Position topRight){
            int height = topRight.getYyPosition() - botLeft.getYyPosition();
            int base = topRight.getXxPosition() - botLeft.getXxPosition();
            return new Position(base / 2, height / 2);
        }

        private int areaFromCorners(Position botLeft, Position topRight){
            int height = topRight.getYyPosition() - botLeft.getYyPosition();
            int base = topRight.getXxPosition() - botLeft.getXxPosition();
            return base * height;
        }
    }
    private final Random random;
    private final TETile[][] WORLD;
    private final int Y_MAX;
    private final int X_MAX;
    private final int ROOM_WIDTH_MAX;
    private final int ROOM_MIN_LEN = 2;
    private final int ROOM_HEIGHT_MAX;

    private final LinkedList<Room> listOfRooms = new LinkedList<>();
    private int usedArea = 0;



    
    private final float WIDTH_PROPORTION = (2F / 27);
    private final float HEIGHT_PROPORTION = (1F/12);
    public RoomsThenHallsGenerator(TETile [][] inWorld, long seed){

        if (TileMatrixHelpers.anyTilesIsNull(inWorld)){
            throw new IllegalArgumentException("All tiles should be non null");
        }


        
        int heightOfWorld = inWorld[0].length;
        int widthOfWorld = inWorld.length;

        if (heightOfWorld < 6 || widthOfWorld < 6){
            throw new IllegalArgumentException("Minimum side of world is 6");
        }
        random = new Random(seed);
        WORLD = inWorld;
        X_MAX = widthOfWorld - 1;
        Y_MAX = heightOfWorld - 1;

        int possibleWidth = (int) (WIDTH_PROPORTION * widthOfWorld);
        int possibleHeight = (int) (HEIGHT_PROPORTION * heightOfWorld);
        ROOM_WIDTH_MAX = Math.max(possibleWidth, ROOM_MIN_LEN);
        ROOM_HEIGHT_MAX = Math.max(possibleHeight, ROOM_MIN_LEN);


        if (X_MAX - ROOM_WIDTH_MAX <= 2 || Y_MAX - ROOM_WIDTH_MAX <= 2){
            throw new IllegalArgumentException("Illegal aspect ratio");
        }
    }
    @Override
    public void setTiles(){
        placeRoom();
    }

    @Override
    public int getUsedArea() {
        return usedArea;
    }

    private Room getRandomRoom(){
        //create position x, y at top left corner
        int x = RandomUtils.uniform(random,ROOM_MIN_LEN ,X_MAX - ROOM_WIDTH_MAX );
        int y = RandomUtils.uniform(random,ROOM_MIN_LEN,Y_MAX  - ROOM_WIDTH_MAX);

        Position botLeftCorner = new Position(x, y);
        //getting a valid top right corner position from left corner
        Position topRightCorner = null;
        while (!isWithinBounds(topRightCorner)){
            topRightCorner = randomTopCorner(botLeftCorner);
        }
        return new Room(botLeftCorner, topRightCorner);
    }

    private boolean isWithinBounds(Position pos){
        if (pos == null){
            return false;
        }
        return ((pos.getXxPosition() <= X_MAX) && (pos.getYyPosition() <= Y_MAX));
    }

    private Position randomTopCorner(Position botCorner){
        //random width
        int width = RandomUtils.uniform(random,ROOM_MIN_LEN, ROOM_WIDTH_MAX +1);
        //random height
        int height = RandomUtils.uniform(random,ROOM_MIN_LEN, ROOM_HEIGHT_MAX + 1);

        return new Position(botCorner.getXxPosition() + width , botCorner.getYyPosition() + height);
    }




    private void placeRoom(){
        //crate random room
        Room roomToPlace = getRandomRoom();
        listOfRooms.add(roomToPlace);
        placeRoomInterior(roomToPlace);
        placeRoomWalls(roomToPlace);




    }
    private void placeRoomInterior(Room room){
        // Get starting coordinates
        int xStart = room.botLeftCorner.getXxPosition();
        int yStart = room.botLeftCorner.getYyPosition();
        //fill tiles
        for (int y = yStart ; y <= yStart + room.getHeight(); y++) {
            for (int x = xStart; x <= xStart + room.getWidth()  ; x++) {
                if (tilesIsNothing(x,y)){
                    usedArea++;
                }
                WORLD[x][y] = Tileset.GRASS;
            }
        }

    }

    private void placeRoomWalls(Room room){
        //place vertical walls
        //left
        int x = room.botLeftCorner.getXxPosition() - 1;
        int startY = room.botLeftCorner.getYyPosition() - 1;
        int endY = room.topRightCorner.getYyPosition() + 1;
        for (int i = startY; i <= endY ; i++) {
            if (tilesIsNothing(x, i)) {
                WORLD[x][i] = Tileset.WALL;
            }
        }

        //right
        x = room.topRightCorner.getXxPosition() + 1;
        for(int i = startY; i <= endY ; i++) {
            if (tilesIsNothing(x, i)) {
                WORLD[x][i] = Tileset.WALL;
            }
        }
        //place horizontal walls
        //bottom
        int y = room.botLeftCorner.getYyPosition() - 1;
        int startX = room.botLeftCorner.getXxPosition();
        int endX = room.topRightCorner.getXxPosition();

        for(int i = startX; i <= endX ; i++) {
            if (tilesIsNothing(i, y)) {
                WORLD[i][y] = Tileset.WALL;
            }
        }
        //top
        y = room.topRightCorner.getYyPosition() + 1;
        for(int i = startX; i <= endX ; i++) {
            if (tilesIsNothing(i, y)) {
                WORLD[i][y] = Tileset.WALL;
            }
        }



    }
    private boolean tilesIsNothing(int x, int y){
        return WORLD[x][y] == Tileset.NOTHING;
    }




    }

