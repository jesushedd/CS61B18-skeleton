public class Palindrome {
    public Deque<Character> wordToDeque (String word){
        Deque<Character> charDeque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            Character currentChar = word.charAt(i);
            charDeque.addLast(currentChar);
        }

        return charDeque;
    }



}
