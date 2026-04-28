import java.util.*;

public class GameLogic {

    static final String RESET = "\u001B[0m";
    static final String RED = "\u001B[31m";
    static final String GREEN = "\u001B[32m";
    static final String YELLOW = "\u001B[33m";
    static final String CYAN = "\u001B[36m";

    private String word;
    private char[] guessedWord;
    private HashSet<Character> guessedLetters;
    private HashSet<Character> revealedLetters;

    private int attempts = 6;
    private boolean hintUsed = false;
    private Scanner sc; 

    public GameLogic(String word, Scanner sc) {
        this.word = word;
        this.sc = sc;

        guessedWord = new char[word.length()];
        Arrays.fill(guessedWord, '_');

        guessedLetters = new HashSet<>();
        revealedLetters = new HashSet<>();

        revealInitialLetters();
    }

    public void startGame() {

        while (attempts > 0) {

            display();

            System.out.print(YELLOW + "👉 Enter letter / hint: " + RESET);
            String input = sc.nextLine().toLowerCase();

            if (input.equals("hint") && !hintUsed) {
                giveHint();
                hintUsed = true;
                continue;
            }

            if (input.length() == 0) continue;

            char ch = input.charAt(0);

            if (!Character.isLetter(ch)) {
                System.out.println(RED + "⚠ Enter valid letter!" + RESET);
                continue;
            }

            if (revealedLetters.contains(ch)) {
                System.out.println(YELLOW + "ℹ Already revealed!" + RESET);
                continue;
            }

            if (guessedLetters.contains(ch)) {
                System.out.println(YELLOW + "⚠ Already guessed!" + RESET);
                continue;
            }

            guessedLetters.add(ch);

            if (word.indexOf(ch) >= 0) {
                updateWord(ch);
                System.out.println(GREEN + "✅ Correct!" + RESET);
            } else {
                attempts--;
                System.out.println(RED + "❌ Wrong!" + RESET);
            }

            if (String.valueOf(guessedWord).equals(word)) {
                System.out.println(GREEN + "\n🎉 YOU WIN! Word: " + word + RESET);
                SoundPlayer.play("win.wav");
                return;
            }
        }

        System.out.println(RED + "\n💀 YOU LOST! Word was: " + word + RESET);
        SoundPlayer.play("lose.wav");
    }

    private void updateWord(char ch) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == ch) guessedWord[i] = ch;
        }
    }

    private void revealInitialLetters() {
        Random r = new Random();
        int reveal = Math.max(1, word.length() / 2);

        int count = 0;
        while (count < reveal) {
            int i = r.nextInt(word.length());

            if (guessedWord[i] == '_') {
                char c = word.charAt(i);
                guessedWord[i] = c;
                revealedLetters.add(c);
                count++;
            }
        }
    }

    private void giveHint() {
        for (int i = 0; i < word.length(); i++) {
            if (guessedWord[i] == '_') {
                char c = word.charAt(i);
                guessedWord[i] = c;
                revealedLetters.add(c);
                System.out.println(CYAN + "💡 Hint used!" + RESET);
                return;
            }
        }
    }

    private void display() {
        System.out.println(CYAN + "\nWord: " + String.valueOf(guessedWord) + RESET);
        System.out.println("Attempts left: " + attempts);
        System.out.println("Guessed: " + guessedLetters);
        drawHangman();
    }

    private void drawHangman() {
        String[] stages = {
            """
            
            
            
            
            """,
            """
            
            
            
            
            _______
            """,
            """
            |
            |
            |
            |
            |_______
            """,
            """
            _______
            |     |
            |
            |
            |
            |_______
            """,
            """
            _______
            |     |
            |     O
            |
            |
            |_______
            """,
            """
            _______
            |     |
            |     O
            |     |
            |
            |_______
            """,
            """
            _______
            |     |
            |     O
            |    /|\\
            |    / \\
            |_______
            """
        };

        System.out.println(stages[6 - attempts]);
    }
}