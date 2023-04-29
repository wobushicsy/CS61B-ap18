import sun.awt.image.ImageWatched;

public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> charList = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            charList.addLast(word.charAt(i));
        }

        return charList;
    }

    public boolean isPalindrom(String word) {
        return false;
    }
}
