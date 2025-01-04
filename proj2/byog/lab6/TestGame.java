package byog.lab6;

import java.util.Random;

public class TestGame {
    public static void main(String[] args) {
        MemoryGame game = new MemoryGame(200,100,new Random());
        for (int i = 0; i < 10; i++) {
            System.out.println(game.generateRandomString(10));
        }
    }
}
