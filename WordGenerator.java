import java.util.*;

public class WordGenerator {

    private final String[] easy = {"cat", "dog", "sun", "bat"};
    private final String[] medium = {"java", "code", "game", "play"};
    private final String[] hard = {"programming", "developer", "engineering"};

    public String getWord(String level) {
        Random r = new Random();

        switch (level) {
            case "easy":
                return easy[r.nextInt(easy.length)];
            case "medium":
                return medium[r.nextInt(medium.length)];
            case "hard":
                return hard[r.nextInt(hard.length)];
            default:
                return easy[r.nextInt(easy.length)];
        }
    }
}