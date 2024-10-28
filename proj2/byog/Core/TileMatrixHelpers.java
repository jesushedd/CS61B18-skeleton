package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Arrays;
import java.util.Objects;

public class TileMatrixHelpers {
    public static void fillVoids(TETile[][] w){
        int height = w[0].length;
        int width = w.length;

        for (int i = 0; i < width ; i++) {
            for (int j = 0; j < height ; j++) {
                w[i][j] = Tileset.NOTHING;
            }
        }
    }

    public static boolean anyTilesIsNull(TETile [][] w) {


        for (TETile[] yTeTiles : w) {
            boolean isnull = Arrays.stream(yTeTiles).anyMatch(Objects::isNull);
            if (isnull){
                return true;
            }
        }
        return false;
    }
}
