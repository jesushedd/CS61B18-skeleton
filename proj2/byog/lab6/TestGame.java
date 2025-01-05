package byog.lab6;

import java.util.Random;

public class TestGame {
    public static void main(String[] args) {
        MemoryGame game = new MemoryGame(40,20,new Random());
        for (int i = 0; i < 10; i++) {
            System.out.println(game.generateRandomString(10));
        }
        game.drawFrame("HOLA MUNDA");
    }
}
