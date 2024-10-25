package byog.algorithms;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;


public class TestLinealGenerator {

    TETile[][] testWorld = new TETile[80][30];


    private static void fillVoids(TETile[][] w){

        for(TETile[] Y : w){
            Arrays.fill(Y, Tileset.NOTHING);
        }
    }

    @Test
    public void testMaxXY(){
        fillVoids(testWorld);
        RandomWalkGenerator lineaAlgo = new RandomWalkGenerator(testWorld, 999);

        Assert.assertEquals(79, lineaAlgo.getMAX_X());
        Assert.assertEquals(29, lineaAlgo.getMAX_Y());

    }
}
