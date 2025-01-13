package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.algorithms.RoomsThenHallsGenerator;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Game {


    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    private final Set<Character> MOVEMENT_KEYS = new HashSet<>(List.of('w', 'a', 's', 'd'));

    private TETile[][] MAP;

    private final int AREA = WIDTH * HEIGHT;

    private Player player;

    private boolean gameOver = false;
    private String tileHoveredDesc;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() throws InterruptedException {
        makeNewGame(10);


        /*GenAlgorithm randomWalkGenerator = new RandomWalkGenerator(MAP, 99);
        while (roomGenerator.getUsedArea() <  AREA  ){
            //System.out.println(mapGenerator.getUsedArea());
            randomWalkGenerator.setTiles();
            ter.renderFrame(MAP);
        }*/
        gameLoop();

        //System.out.println(mapGenerator.getUsedArea());


    }

    private void gameLoop(){
        ter.initialize(WIDTH, HEIGHT);
        while (!gameOver){
            movePlayer(listenKey());
            if (player.hasKey() & player.isNearDoor()){
                gameOver = true;
            }

            ter.renderFrame(MAP);
            showTileDescription();
            StdDraw.pause(33);
        }
        StdDraw.clear();
        StdDraw.show();

    }

    private void movePlayer(Character typed){
        if (typed != null){
            switch (typed){
                case 'w':
                    player.moveUp();
                    break;
                case  's':
                    player.moveDown();
                    break;
                case 'd':
                    player.moveRight();
                    break;
                case 'a':
                    player.moveLeft();
            }
        }
    }

    /*
    * Listen for a key if there is some key return it
    * If key is ':' wait a little bit to listen another key, if that key is 'q' set game over = true*/
    private Character listenKey(){
        //while (StdDraw.hasNextKeyTyped()) StdDraw.nextKeyTyped();


        if (!StdDraw.hasNextKeyTyped()){
            return null;
        }

        char keyPressed = StdDraw.nextKeyTyped();
        if (keyPressed == ':'){
            StdDraw.pause(300);
            if (StdDraw.hasNextKeyTyped()){
                if (Character.toLowerCase(StdDraw.nextKeyTyped()) == 'q'){
                    gameOver = true;
                }
            }

        }

        if ( MOVEMENT_KEYS.contains(keyPressed)){
            return Character.toLowerCase(keyPressed);
        }
        return null;
    }

    private void overTile(){
            int mouseX  = (int) StdDraw.mouseX();
            int mouseY  = (int) StdDraw.mouseY();
            if (yMouseInBounds(mouseY)) tileHoveredDesc = MAP[mouseX][mouseY].description();
    }

    private void showTileDescription(){
        Font prevFont = StdDraw.getFont();
        overTile();
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setFont(new Font("Arial Black",Font.BOLD,16));
        StdDraw.text(74,1, tileHoveredDesc);
        StdDraw.show();
        StdDraw.setFont(prevFont);
    }

    private boolean yMouseInBounds(int y){
        return y < HEIGHT - 1;
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
        input = input.toLowerCase();
        char startingOption = input.charAt(0);
        if (!(startingOption == 'n' | startingOption == 'l')){
            return null;
        }

        boolean startingNewGame = startingOption == 'n';
        String commands = input.substring(1);


        if (startingNewGame){
            if (!commands.matches("^\\d+[wasd]*(:q)?$")){
                return null;
            }
            long seed = Long.parseLong(commands.replaceAll("\\D", ""));
            makeNewGame(seed);
        }

        TETile[][] finalWorldFrame = null;
        return finalWorldFrame;
    }

    private void makeNewGame(long seed){
        MAP = new TETile[WIDTH][HEIGHT];
        TileMatrixHelpers.fillVoids(MAP);
        RoomsThenHallsGenerator roomGenerator = new RoomsThenHallsGenerator(MAP, seed);
        player = roomGenerator.createWorld();
    }
}
