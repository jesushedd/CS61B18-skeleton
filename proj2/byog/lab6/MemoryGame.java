package byog.lab6;

import byog.Core.RandomUtils;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        Random rand = new Random(seed);
        MemoryGame game = new MemoryGame(40, 40, rand);
        game.startGame();
    }

    public MemoryGame(int width, int height, Random rand) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
        //TODO: Initialize random number generator
        this.rand = rand;

    }

    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n
        //alocate StringBuilder
        StringBuilder rdString = new StringBuilder();
        //for n, get a random char
        for (int i = 0; i < n ; i++) {
            rdString.append(getRandomChar());
        }


        return rdString.toString();
    }

    private char getRandomChar(){
        return CHARACTERS[rand.nextInt(CHARACTERS.length)];
    }

    public void drawFrame(String s) {
        //TODO: Take the string and display it in the center of the screen
        Font font = new Font("Arial", Font.BOLD, 40);
        StdDraw.setFont(font);
        for (char c : s.toCharArray()){
            StdDraw.clear();
            StdDraw.text(this.width / 2, this.height / 2, String.valueOf(c));
            StdDraw.show();
            StdDraw.pause(500);
        }
        //TODO: If game is not over, display relevant game information at the top of the screen
    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        return null;
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts

        //TODO: Establish Game loop
    }

}
