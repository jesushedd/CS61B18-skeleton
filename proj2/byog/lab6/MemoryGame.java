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
        // Initialize random number generator
        this.rand = rand;

        round = 1;

    }

    public String generateRandomString(int n) {
        // Generate random string of letters of length n
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

    public void drawFrame() {
        //Take the string and display it in the center of the screen
        String message = playerTurn ? "Guess!" : "Watch!";
        StdDraw.setPenColor(StdDraw.BLUE);

        StdDraw.rectangle(width/2.0, 39, width / 2, 1);
        //Rounder number left extreme
        Font font = new Font("Monaco", Font.BOLD, 25);
        StdDraw.setFont(font);
        StdDraw.text(width / 8.0, 39,"Round: " + round);
        //Game state ,Center
        StdDraw.text(width / 2.0, 39, message);
        //Cheer Message, right extreme
        StdDraw.text(width * 7.0 / 8.0 , 39, ENCOURAGEMENT[rand.nextInt(ENCOURAGEMENT.length)]);
        StdDraw.show();

        // If game is not over, display relevant game information at the top of the screen
    }

    public void flashSequence(String letters) {
        // Display each character in letters, making sure to blank the screen between letters
        for (char c : letters.toCharArray()){
            StdDraw.clear();
            StdDraw.text(this.width / 2.0, this.height / 2.0, String.valueOf(c));
            drawFrame();
            StdDraw.show();
            StdDraw.pause(1000);
            StdDraw.clear();
            drawFrame();
            StdDraw.show();
            StdDraw.pause(500);
        }
    }

    public String solicitNCharsInput(int n) {
        //Clear buffer
        while (StdDraw.hasNextKeyTyped()) StdDraw.nextKeyTyped();


        // Read n letters of player input
        //player set all chars != n
        int charCount = 0;
        playerTurn = true;
        StringBuilder charAccumulator = new StringBuilder();
        //while are chars
        /*StdDraw.clear();
        StdDraw.text(width / 2, height / 2, "Start Wrting");
        StdDraw.show();
        StdDraw.pause(1000);*/
        startWritingMessage();
        StdDraw.clear();
        drawFrame();
        StdDraw.show();
        while (charCount < n){
            while (StdDraw.hasNextKeyTyped()){
                // get char
                char currentChar = StdDraw.nextKeyTyped();
                charCount++;
                charAccumulator.append(currentChar);


                //show all chars pressed til now
                StdDraw.clear();
                StdDraw.text(width / 2.0, height / 2.0, charAccumulator.toString());
                drawFrame();
                StdDraw.show();
            }

        }


        return charAccumulator.toString();
    }

    private void startWritingMessage(){
        StdDraw.clear();
        StdDraw.text(width / 2, height / 2, "Start Wring!");
        drawFrame();
        StdDraw.show();

        for (int i = 3; i > 0; i--) {
            StdDraw.clear();
            StdDraw.text(width / 2.0, height / 2.0, String.valueOf(i));
            drawFrame();
            StdDraw.show();
            StdDraw.pause(750);

        }
        StdDraw.clear();
        StdDraw.text(width / 2 , height / 2, "Now!");
        drawFrame();
        StdDraw.show();
        StdDraw.pause(750);
    }

    public void startGame() {
        //Set any relevant variables before the game starts
        round = 1;
        gameOver = false;



        //Establish Game loop
        while (!gameOver){
            showRoundNumber();
            String targetString = generateRandomString(round);
            flashSequence(targetString);
            String playerInput =  solicitNCharsInput(round);
            if (targetString.equals(playerInput.toLowerCase())) round++;
            else gameOver = true;
        }
        showGameOverMessage();

    }

    private void showGameOverMessage(){
        StdDraw.clear();
        StdDraw.show();
        StdDraw.text(width / 2, height / 2, "Game Over! You made it to round: " + round);
        StdDraw.show();
    }

    private void showRoundNumber(){
        StdDraw.clear();
        StdDraw.show();
        StdDraw.text(width / 2.0, height / 2.0, "Round: " + round);
        drawFrame();
        StdDraw.show();
        StdDraw.pause(500);
    }

}
