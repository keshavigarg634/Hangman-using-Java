import java.util.*;

public class HangmanGame {

    public static final String RESET = "\u001B[0m";
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BOLD = "\033[0;1m";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        showWelcome();

        while (true) {

            System.out.println(CYAN + "\n━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);
            System.out.println(BOLD + "🎮 SELECT DIFFICULTY" + RESET);
            System.out.println(CYAN + "━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);

            System.out.println("1️⃣ Easy");
            System.out.println("2️⃣ Medium");
            System.out.println("3️⃣ Hard");

            System.out.print(YELLOW + "👉 Choose difficulty: " + RESET);

            int choice = sc.nextInt();
            sc.nextLine(); 

            String difficulty = (choice == 1) ? "easy" :
                                (choice == 2) ? "medium" : "hard";

            String word = new WordGenerator().getWord(difficulty);

            GameLogic game = new GameLogic(word, sc); 
            game.startGame();

            System.out.print(YELLOW + "\n🔁 Play again? (y/n): " + RESET);
            String input = sc.nextLine();

            if (!input.equalsIgnoreCase("y")) break;
        }

        typeWriter(GREEN + "\n👋 Thanks for playing Hangman!" + RESET);
    }

    public static void showWelcome() {
        System.out.println(CYAN + "========================================" + RESET);
        typeWriter(BOLD + "      🎯 WELCOME TO HANGMAN GAME 🎯" + RESET);
        System.out.println(CYAN + "========================================" + RESET);

        typeWriter("🧠 Guess the word before the man hangs!");
        typeWriter("💡 You can use 'hint' once during the game.");
        typeWriter("🔥 Some letters are already revealed!");

        System.out.println(CYAN + "========================================" + RESET);
    }

    public static void typeWriter(String text) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try { Thread.sleep(25); } catch (Exception e) {}
        }
        System.out.println();
    }
}