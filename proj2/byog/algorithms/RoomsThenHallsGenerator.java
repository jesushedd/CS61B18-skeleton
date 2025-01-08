package byog.algorithms;

import byog.Core.Player;
import byog.Core.RandomUtils;
import byog.TileEngine.TETile;
import byog.Core.TileMatrixHelpers;
import byog.TileEngine.Tileset;
import byog.algorithms.position.Position;
import java.util.Random;
import java.util.TreeSet;


/*
* Creates thw world based  on the algorithm:
* First creates Rooms in random positions
* Then lniks the rooms in order from most left to right*/
public class RoomsThenHallsGenerator implements GenAlgorithm{

    private class Room {
        private final Position botLeftCorner;
        private final Position topRightCorner;

        private final Position centroid;
        private final int distanceFromOrigin;
        private Room(Position botLeft, Position topRight ){
            botLeftCorner = botLeft;
            topRightCorner = topRight;
            centroid = calculateCentroid(botLeft, topRight);

            //distanceFromOrigin
            distanceFromOrigin = (int) (1000 * distanceTo(RoomsThenHallsGenerator.this.origin));

        }

        private int getWidth(){
            return topRightCorner.getXxPosition() - botLeftCorner.getXxPosition();
        }

        private int getHeight(){
            return topRightCorner.getYyPosition() - botLeftCorner.getYyPosition();
        }


        

        private Position calculateCentroid(Position botLeft, Position topRight){
            int height = topRight.getYyPosition() + botLeft.getYyPosition();
            int base = topRight.getXxPosition() + botLeft.getXxPosition();
            return new Position(base / 2, height / 2);
        }

        private int areaFromCorners(Position botLeft, Position topRight){
            int height = topRight.getYyPosition() - botLeft.getYyPosition();
            int base = topRight.getXxPosition() - botLeft.getXxPosition();
            return base * height;
        }

        private double distanceTo(Position pos){
            return Math.sqrt( Math.pow (centroid.getYyPosition() - pos.getYyPosition(), 2) + Math.pow (centroid.getXxPosition() - pos.getXxPosition(), 2));
        }
    }
    private final Random random;
    private final TETile[][] WORLD;
    private final int Y_MAX;
    private final int X_MAX;
    private final int ROOM_WIDTH_MAX;
    private final int ROOM_MIN_LEN = 2;
    private final int ROOM_HEIGHT_MAX;

    private final TreeSet<Room> listOfRooms = new TreeSet<>((a, b) -> a.distanceFromOrigin - b.distanceFromOrigin );
    //private final LinkedList<Room> listOfRooms = new LinkedList<>();
    private final int AREA;
    private int usedArea = 0;
    private Position origin;

    private Player playerEntity;

    private boolean isKeyPlaced = false;

    private boolean isDoorPlaced = false;



    
    private final float WIDTH_PROPORTION = (2F / 27);
    private final float HEIGHT_PROPORTION = (1F/12);
    public RoomsThenHallsGenerator(TETile [][] inWorld, long seed){

        if (TileMatrixHelpers.anyTilesIsNull(inWorld)){
            throw new IllegalArgumentException("All tiles should be non null");
        }


        
        int heightOfWorld = inWorld[0].length;
        int widthOfWorld = inWorld.length;
        AREA = heightOfWorld * widthOfWorld;

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

        //Set origin position
        origin = new Position(X_MAX* 3/4 , Y_MAX/2);//at bottom middle of world

    }
    @Override
    public void setTiles(){
        placeRoom();
        //fillHalls();
    }

    @Override
    public int getUsedArea() {
        return usedArea;
    }

    public Player createWorld(){
        while (getUsedArea() <  AREA / 10  ){
            //System.out.println(mapGenerator.getUsedArea());
            placeRoom();
        }
        fillHalls();
        return playerEntity;
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
        //relocateOrigin(roomToPlace.centroid);
    }

    public void fillHalls(){
        //pop last room, trace  path from its centroid   to new last room centroid
         while (listOfRooms.size() > 1) {
            Position starting = listOfRooms.pollLast().centroid;
            Position ending = listOfRooms.last().centroid;

            /*Position starting = listOfRooms.pollLast().centroid;
            Position ending = listOfRooms.peekLast().centroid;*/
            
            int dX = ending.getXxPosition() - starting.getXxPosition();
            int dY = ending.getYyPosition() - starting.getYyPosition();
             //if 0 trace horizontal component first, else race vertical first
            int direction = RandomUtils.uniform(random, 2);
            Position intermediate;
            if (direction == 0){
                intermediate = new Position(starting.getXxPosition() + dX, starting.getYyPosition());
            } else {
                intermediate = new Position(starting.getXxPosition(), starting.getYyPosition() + dY); 
            }
            
            traceLinealHall(starting, intermediate);
            traceLinealHall(intermediate, ending);
        }
        setPlayer(listOfRooms.last());
    }

    private void setPlayer(Room first) {
        playerEntity = new Player(first.centroid, WORLD);
        //draw player entity en world map
        WORLD[playerEntity.getX()][playerEntity.getY()] = Tileset.PLAYER;

    }


    private void traceLinealHall(Position start, Position end){
        boolean vertical = start.getXxPosition() == end.getXxPosition();
        boolean horizontal = start.getYyPosition() == end.getYyPosition();
        
        if (horizontal) {
            int y = start.getYyPosition();

            int dx = (end.getXxPosition() - start.getXxPosition());
            if (dx != 0) {
                dx = dx / Math.abs(end.getXxPosition() - start.getXxPosition());
            }

            for (int x = start.getXxPosition(); x != end.getXxPosition() + dx; x += dx) {
                if (isAvailable(x,y)){
                    WORLD[x][y] = Tileset.GRASS;
                }
                traceHorizontalWalls(x,y);
            }

        } else if (vertical) {
            int x = start.getXxPosition();

            int dy = (end.getYyPosition() - start.getYyPosition());
            if (dy != 0){
                dy = dy / Math.abs(end.getYyPosition() - start.getYyPosition());
            }

            for (int y = start.getYyPosition(); y != end.getYyPosition() + dy; y += dy) {
                if (isAvailable(x, y)){
                    WORLD[x][y] = Tileset.GRASS;
                }
                traceVerticalWalls(x, y);
            }

        }
    }

    private void traceHorizontalWalls(int x, int y){
        int topWall = y + 1;
        int bottomWall = y - 1;

        if (tilesIsNothing(x, topWall)){
            WORLD[x][topWall] = Tileset.WALL;
            if (!isDoorPlaced) tryToPlaceDoor(x, topWall);
        }
        if (tilesIsNothing(x, bottomWall)){
            WORLD[x][bottomWall] = Tileset.WALL;
            if (!isDoorPlaced)  tryToPlaceDoor(x,bottomWall);
        }
    }


    private void traceVerticalWalls(int x, int y){
        int rightWall = x + 1;
        int leftWall = x - 1;

        if (tilesIsNothing(rightWall, y)){
            WORLD[rightWall][y] = Tileset.WALL;
            if (!isDoorPlaced) tryToPlaceDoor(rightWall, y);
        }
        if (tilesIsNothing(leftWall, y)){
            WORLD[leftWall][y] = Tileset.WALL;
            if (!isDoorPlaced) tryToPlaceDoor(leftWall, y);
        }
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
                    WORLD[x][y] = Tileset.GRASS;
                    if (!isKeyPlaced){
                        tryToPlaceKey(x,y);
                    }
                }

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
                if (!isDoorPlaced){
                    tryToPlaceDoor(x,i);
                }
            }
        }

        //right
        x = room.topRightCorner.getXxPosition() + 1;
        for(int i = startY; i <= endY ; i++) {
            if (tilesIsNothing(x, i)) {
                WORLD[x][i] = Tileset.WALL;
                if (!isDoorPlaced){
                    tryToPlaceDoor(x,i);
                }
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
                if (!isDoorPlaced){
                    tryToPlaceDoor(x,y);
                }
            }
        }
        //top
        y = room.topRightCorner.getYyPosition() + 1;
        for(int i = startX; i <= endX ; i++) {
            if (tilesIsNothing(i, y)) {
                WORLD[i][y] = Tileset.WALL;
                if (!isDoorPlaced){
                    tryToPlaceDoor(x,y);
                }
            }
        }



    }
    private boolean tilesIsNothing(int x, int y){
        return WORLD[x][y] == Tileset.NOTHING;
    }


    private void relocateOrigin(Position centroidR){
        int n = RandomUtils.uniform(random, 30);
        if (n < 10){
            origin = centroidR;
        }
    }

    private void tryToPlaceKey(int x, int y){
        int prob = RandomUtils.uniform(random, 1000);
        if (prob < 100){
            WORLD[x][y] = Tileset.KEY;
            isKeyPlaced = true;
            System.out.println(x + "key" +y);
        }
    }

    private void  tryToPlaceDoor(int x, int y){
        int prob = RandomUtils.uniform(random, 1000);
        if (prob <100){
            WORLD[x][y] = Tileset.LOCKED_DOOR;
            isDoorPlaced = true;
            System.out.println(x + "door" +y);
        }
    }

    private boolean isAvailable(int x, int y){
        return WORLD[x][y] != Tileset.LOCKED_DOOR &  WORLD[x][y] != Tileset.KEY & WORLD[x][y] != Tileset.PLAYER;

    }




    }

