package byog.algorithms;

import byog.Core.RandomUtils;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.algorithms.position.Position;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class LinealGenerator {
    private final Random random;
    final private int NUMBER_OF_SEEDS;

    final private TETile[][] WORLD;

    final private float SEEDS_PER_AREA = 10f/2400;

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
        this.NUMBER_OF_SEEDS =  numOfSeeds % 2 == 0  ?  numOfSeeds : numOfSeeds + 1;
        System.out.println("Lineal Generator started with : " + NUMBER_OF_SEEDS + " seeds for each tile" );

        this.MAX_X = inWorld.length - 1;
        this.MAX_Y = inWorld[0].length -1;

        this.random = new Random(seed);


    }

    public void setTiles(){
         if (wallPosition == null | floorPosition == null){
             for (int i = 0; i < NUMBER_OF_SEEDS; i++) {
                 setTileSeeds();
             }
         }
         setNextPosition(wallPosition);
         if (tileIsNothing(wallPosition)){
             WORLD[wallPosition.getXxPosition()][wallPosition.getYyPosition()] = Tileset.WALL;
             usedArea++;
         }

        setNextPosition(floorPosition);
        if (tileIsNothing(floorPosition)){
            WORLD[floorPosition.getXxPosition()][floorPosition.getYyPosition()] = Tileset.FLOOR;
            usedArea++;
        }
        
    }

    private void setNextPosition(Position currentPosition){

        int currentX = currentPosition.getXxPosition();
        int currentY = currentPosition.getYyPosition();

        List<String> possibleDirections = new java.util.ArrayList<>(List.of("UP", "DOWN", "LEFT", "RIGHT"));

        if ( currentY == MAX_Y){
            possibleDirections.remove("UP");
        } else if (currentY == MIN_Y) {
            possibleDirections.remove("DOWN");
        }

        if (currentX == MAX_X){
            possibleDirections.remove("RIGHT");
        } else if (currentX == MIN_X) {
            possibleDirections.remove("LEFT");
        }

        int idx = RandomUtils.uniform(random, possibleDirections.size());
        String nextDirection = possibleDirections.get(idx);

        switch (nextDirection){
            case "UP":
                currentPosition.moveUp();
                break;
            case "DOWN":
                currentPosition.moveDown();
                break;
            case "LEFT":
                currentPosition.moveLeft();
                break;
            case "RIGHT":
                currentPosition.moveRight();
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
        floorPosition = new Position(xFloorPos, yFloorPos);

        WORLD[wallPosition.getXxPosition()][wallPosition.getYyPosition()] = Tileset.WALL;
        usedArea++;

        WORLD[floorPosition.getXxPosition()][floorPosition.getYyPosition()] = Tileset.FLOOR;
        usedArea++;



    }


    private boolean tileIsNothing(Position current){
        return WORLD[current.getXxPosition()][current.getYyPosition()] == Tileset.NOTHING;

    }









}
