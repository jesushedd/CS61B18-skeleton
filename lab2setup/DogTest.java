import static org.junit.Assert.*;
import org.junit.Test;

public class DogTest {    
    @Test
    public void testSmall() {
        Dog d = new Dog(3, "chihuahua");
        assertEquals("yip", d.noise());
    }

    @Test
    public void testLarge() {
        Dog d = new Dog(20, "dalmata");
        assertEquals("bark", d.noise());
    }

    public static void main(String[] args) {
        Dog greca = new Dog(20, "chusco");
        System.out.println(greca.race);
        greca.noise();
    }
}
