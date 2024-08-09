import java.io.FileWriter;

public class Palindrome {
    public Deque<Character> wordToDeque (String word){
        Deque<Character> charDeque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            Character currentChar = word.charAt(i);
            charDeque.addLast(currentChar);
        }

        return charDeque;
    }

    /*Returns true if a word is a palindrome, false otherwise*/
    public boolean isPalindrome(String word){
        Deque<Character> charList = wordToDeque(word);
        String reversed = "";
        while (true){
            Character letter = charList.removeLast();
            if (letter == null){
                break;
            }
            reversed += letter;
        }

        return word.equals(reversed);
    }

    /*Returns true if a word is a palindrome by the implemented CharacterComparator conditions given*/
    public boolean isPalindrome(String word, CharacterComparator cc){
        boolean isPalindrome = true;
        Character leftChar;
        Character rightChar;
        Deque<Character> chars = wordToDeque(word);
        while (chars.size() > 1){

            leftChar = chars.removeFirst();
            rightChar = chars.removeLast();

            isPalindrome = cc.equalChars(leftChar, rightChar);
            if (!isPalindrome){
                return false;
            }
        }

        return true;
    }





}
