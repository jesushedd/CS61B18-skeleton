import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offBy1 = new OffByOne();
    static Palindrome palindrome = new Palindrome();

    // Your tests go here.
    @Test
    public void testEqualChars(){
        assertFalse(offBy1.equalChars('y', 'g'));
        assertFalse(offBy1.equalChars('l','n'));
        assertFalse(offBy1.equalChars(';','.'));
        assertFalse(offBy1.equalChars('6','4'));
        assertFalse(offBy1.equalChars('¿','¿'));
        assertFalse(offBy1.equalChars('=','='));
        assertFalse(offBy1.equalChars('2', '8'));
        assertTrue(offBy1.equalChars('2','3'));
        assertTrue(offBy1.equalChars(';',':'));
        assertTrue(offBy1.equalChars('>', '?'));
    }


    @Test
    public void testIsPalindrome() {
        assertFalse(palindrome.isPalindrome("Hola", offBy1));
        assertFalse(palindrome.isPalindrome("palabrita", offBy1));
        assertFalse(palindrome.isPalindrome("otrapalabrita", offBy1));
        assertTrue(palindrome.isPalindrome("agb", offBy1));
        assertTrue(palindrome.isPalindrome("AhB", offBy1));
        assertTrue(palindrome.isPalindrome("jotopsnk", offBy1));
        assertTrue(palindrome.isPalindrome("flake", offBy1));
        assertTrue(palindrome.isPalindrome("45", offBy1));
        assertTrue(palindrome.isPalindrome("", offBy1));
        assertTrue(palindrome.isPalindrome(",", offBy1));
    }
}
