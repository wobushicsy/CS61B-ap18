public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> charList = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            charList.addLast(word.charAt(i));
        }

        return charList;
    }

    public boolean isPalindrome(String word) {
        int size = word.length();
        boolean ispalindrome = true;
        char a, b;
        for (int i = 0; i < size / 2; i++) {
            a = word.charAt(i);
            b = word.charAt(size - i - 1);
            if (a != b) {
                ispalindrome = false;
                break;
            }
        }

        return ispalindrome;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        int size = word.length();
        boolean ispalindrome = true;
        char a, b;
        for (int i = 0; i < size / 2; i++) {
            a = word.charAt(i);
            b = word.charAt(size - i - 1);
            if (!cc.equalChars(a, b)) {
                ispalindrome = false;
                break;
            }
        }

        return ispalindrome;
    }
}
