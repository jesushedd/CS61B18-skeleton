package byog.algorithms;

import byog.Core.RandomUtils;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.algorithms.position.Position;

import java.util.*;
import java.util.function.Predicate;

public class LinealGenerator {

    private class TileGrower implements Runnable{

        Position currentPosition;
        TETile tileStyle;
        public TileGrower(Position startingPosition, TETile predefinedTile){
            currentPosition = startingPosition;
            tileStyle = predefinedTile;
        }

        @Override
        public  void run(){
        synchronized (LinealGenerator.this){
            setNextPosition(currentPosition);
                //set a selected in current position if not previously set to other tile
                if (tileIsNothing(currentPosition)) {

                        WORLD[currentPosition.getXxPosition()][currentPosition.getYyPosition()] = tileStyle;
                        System.out.println("llenado en" + currentPosition.getXxPosition() + ", " + currentPosition.getYyPosition());
                        usedArea++;
                    }


        }}
    }

    private final Random random;
    final private int NUMBER_OF_SEEDS;

    final private TETile[][] WORLD;

    final private float SEEDS_PER_AREA = 1000f/2400;

    final private int MAX_X;
    public int getMAX_X(){
        return MAX_X;
    }

    final private int MIN_X = 0;

    final private int MAX_Y;
    public int getMAX_Y(){
        return MAX_Y;
    }

    final private int MIN_Y = 0;

    private Position wallPosition;

    private Position floorPosition;

    private int usedArea;

    private final List<Position> seedsOfWall = new ArrayList<>();
    private final List<Position> seedsOfFloor = new ArrayList<>();
    public int getUsedArea(){
        return usedArea;
    }








    public LinealGenerator(TETile[][] inWorld, long seed){
        for (TETile[] yTeTiles : inWorld) {
            if (Arrays.stream(yTeTiles).anyMatch(Objects::isNull)) {
                throw new IllegalArgumentException("All tiles should be non null");
            }
        }

        this.WORLD = inWorld;
        int AREA = WORLD.length * WORLD[1].length;
        System.out.println("Lineal Generator started with AREA of: " + AREA );
        this.usedArea = 0;

        int numOfSeeds = (int) (AREA * SEEDS_PER_AREA);
        numOfSeeds = numOfSeeds % 2 == 0  ?  numOfSeeds : numOfSeeds + 1;
        this.NUMBER_OF_SEEDS =  numOfSeeds / 2;
        System.out.println("Lineal Generator started with : " + NUMBER_OF_SEEDS + " seeds for each tile" );

        this.MAX_X = inWorld.length - 1;
        this.MAX_Y = inWorld[0].length -1;

        this.random = new Random(seed);


    }

    public void setTiles(){
        //Check all tiles are initialized
         if (wallPosition == null | floorPosition == null){
             for (int i = 0; i < NUMBER_OF_SEEDS ; i++) {
                 setTileSeeds();
             }
         }

         Thread[] threadsForFloors = new Thread[NUMBER_OF_SEEDS ];
         Thread[] threadsForWalls = new Thread[NUMBER_OF_SEEDS ];


        for (int i = 0; i < NUMBER_OF_SEEDS ; i++) {
            threadsForFloors[i] = new Thread(new TileGrower(seedsOfFloor.get(i), Tileset.FLOOR));
        }

        for (int i = 0; i < NUMBER_OF_SEEDS ; i++) {
            threadsForWalls[i] = new Thread(new TileGrower(seedsOfWall.get(i), Tileset.WALL));
        }

        for (int i = 0; i < NUMBER_OF_SEEDS ; i++) {
            threadsForFloors[i].start();
            threadsForWalls[i].start();
        }

        for (int i = 0; i < NUMBER_OF_SEEDS ; i++) {
            try {
                threadsForFloors[i].join();
                threadsForWalls[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        
    }

    private void setNextPosition(Position currentPosition){

        int currentX = currentPosition.getXxPosition();
        int currentY = currentPosition.getYyPosition();

        List<String> possibleDirections = new java.util.ArrayList<>(List.of("UP", "DOWN", "LEFT", "RIGHT"));
        /*
        if ( currentY == MAX_Y){
            possibleDirections.remove("UP");
        } else if (currentY == MIN_Y) {
            possibleDirections.remove("DOWN");
        }

        if (currentX == MAX_X){
            possibleDirections.remove("RIGHT");
        } else if (currentX == MIN_X) {
            possibleDirections.remove("LEFT");
        }*/

        int idx = RandomUtils.uniform(random, possibleDirections.size());
        String nextDirection = possibleDirections.get(idx);

        switch (nextDirection){
            case "UP":
                currentPosition.moveUp(MAX_Y);
                break;
            case "DOWN":
                currentPosition.moveDown();
                break;
            case "LEFT":
                currentPosition.moveLeft();
                break;
            case "RIGHT":
                currentPosition.moveRight(MAX_X);
                break;
        }

    }



    private void setTileSeeds(){
        int xWallPos = 0;
        int yWallPos = 0;

        int xFloorPos = 0;
        int yFloorPos = 0;

        while (xWallPos == xFloorPos & yFloorPos == yWallPos) {
            xWallPos = RandomUtils.uniform(random, MAX_X + 1);
            yWallPos = RandomUtils.uniform(random, MAX_Y + 1);

            xFloorPos = RandomUtils.uniform(random, MAX_X + 1);
            yFloorPos = RandomUtils.uniform(random, MAX_Y + 1);
        }




        wallPosition = new Position(xWallPos, yWallPos);
        seedsOfWall.add(wallPosition);
        WORLD[wallPosition.getXxPosition()][wallPosition.getYyPosition()] = Tileset.WALL;
        System.out.println("LLenado en " +wallPosition.getXxPosition() +", " + wallPosition.getYyPosition());
        usedArea++;


        floorPosition = new Position(xFloorPos, yFloorPos);
        seedsOfFloor.add(floorPosition);
        WORLD[floorPosition.getXxPosition()][floorPosition.getYyPosition()] = Tileset.FLOOR;
        System.out.println("LLenado en " +floorPosition.getXxPosition() +", " + floorPosition.getYyPosition());
        usedArea++;



    }


    private synchronized boolean tileIsNothing(Position current){
        return WORLD[current.getXxPosition()][current.getYyPosition()] == Tileset.NOTHING;

    }

    public Map<Integer, Integer> getFinalCount(){
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i=0; i < WORLD.length; i++) {
            for (int j = 0; j < WORLD[0].length; j++) {
                if (WORLD[i][j] == Tileset.NOTHING){
                    map.put(i,j);
                }
            }
        }
        return map;
    }










}
