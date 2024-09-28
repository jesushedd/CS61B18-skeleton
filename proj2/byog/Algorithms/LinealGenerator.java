package byog.Algorithms;

import byog.TileEngine.TETile;

import java.util.Arrays;
import java.util.Objects;

public class LinealGenerator {
    final private int NUMBER_OF_SEEDS;

    final private float MAP_PERCENTAGE = 0.33f;

    final private TETile[][] WORLD;

    final private int AREA;

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




    public LinealGenerator(TETile[][] inWorld){
        for (TETile[] yTeTiles : inWorld) {
            if (Arrays.stream(yTeTiles).anyMatch(Objects::isNull)) {
                throw new IllegalArgumentException("All tiles should be non null");
            }
        }

        this.WORLD = inWorld;
        this.AREA = WORLD.length * WORLD[1].length;

        int numOfSeeds = (int) (AREA * SEEDS_PER_AREA);
        this.NUMBER_OF_SEEDS =  numOfSeeds % 2 == 0  ?  numOfSeeds : numOfSeeds + 1;

        this.MAX_X = inWorld.length - 1;
        this.MAX_Y = inWorld[0].length -1;
    }




}
