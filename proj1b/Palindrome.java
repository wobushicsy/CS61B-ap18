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
        int size = word.length();
        boolean ispalindrom = true;
        char a, b;
        for (int i = 0; i < size / 2; i++) {
            a = word.charAt(i);
            b = word.charAt(size - i - 1);
            if (a != b) {
                ispalindrom = false;
                break;
            }
        }

        return ispalindrom;
    }
}
