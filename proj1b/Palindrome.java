public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> charDeque = new LinkedListDeque<>();
        String text = word;
        for (int i = 0; i < word.length(); i ++) {
            char a_char = text.charAt(i);
            charDeque.addLast(a_char);
        }
        return charDeque;
    }

    public boolean isPalindrome(String word) {
        return true;
    }
}
