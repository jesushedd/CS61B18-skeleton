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


    public static String nakedHex(char c, int side){
        StringBuilder hexagon = new StringBuilder();
        String unit = String.valueOf(c);

        int blanks = side -1; //first row ,number of blanks on each side
        int rowWidth = side;
        int addToBlanks = -1;
        int addToRow = 2;

        for (int i = 0; i < 2 * side; i++){
            //append blanks
            for (int j = 0; j < blanks; j++){
                hexagon.append(" ");
            }
            //append unit
            for (int k = 0; k < rowWidth; k++) {
                hexagon.append(unit);
            }
            //append blanks again xd
            for (int j = 0; j < blanks; j++){
                hexagon.append(" ");
            }
            hexagon.append("\n");

            // update lengths for next row
            if (i == side - 1 ){
                continue;
            }
            if (i == side){
                addToRow *= -1;
                addToBlanks *= -1;
            }
            blanks+=addToBlanks;
            rowWidth+=addToRow;

        }
        return hexagon.toString();
    }

    public static void addHexagon(TETile[][] world, int pX, int pY, int size,  TETile tile){
        int rowStart =  pX ;
        int rowEnd = rowStart + (size - 1);
        int addToRowStart = -1;
        int addToRowEnd = 1;

        int firstHalf = pY + size - 1;
        int secondHalf = pY + size;

        for (int i = pY; i < pY + 2 * size ; i++) {

            //append tile
            for (int j = rowStart; j <= rowEnd; j++) {
                world[j][i] = tile;
            }
            if (i == firstHalf ){
                continue;
            }
            if (i == secondHalf){
                addToRowStart *= -1;
                addToRowEnd *= -1;
            }

            rowStart += addToRowStart;
            rowEnd += addToRowEnd;
        }

    }

    private static void fillVoids(TETile[][] w){
        int height = w[0].length;
        int width = w.length;

        for (int i = 0; i < width ; i++) {
            for (int j = 0; j < height ; j++) {
                w[i][j] = Tileset.NOTHING;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(nakedHex('#', 3));
        TERenderer rendr = new TERenderer();
        rendr.initialize(60, 30);

        // initialize tiles
        TETile[][] world = new TETile[100][200];

        fillVoids(world);

        addHexagon(world, 10,10,4, Tileset.MOUNTAIN);
        rendr.renderFrame(world);
    }
}
