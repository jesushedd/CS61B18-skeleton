package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.algorithms.RandomWalkGenerator;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 40;
    public static final int HEIGHT = 15;

    private TETile[][] MAP;

    private final int AREA = WIDTH * HEIGHT;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        MAP = new TETile[WIDTH][HEIGHT];
        fillVoids(MAP);
        ter.initialize(WIDTH, HEIGHT);

        RandomWalkGenerator mapGenerator = new RandomWalkGenerator(MAP, 25);
        while (mapGenerator.getUsedArea() <  AREA  ){
            System.out.println(mapGenerator.getUsedArea());
            mapGenerator.setTiles();
            ter.renderFrame(MAP);
        }
        ter.renderFrame(MAP);
        System.out.println("Listo! " + mapGenerator.getFinalCount());
        System.out.println(mapGenerator.getUsedArea());


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

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        TETile[][] finalWorldFrame = null;
        return finalWorldFrame;
    }
}
