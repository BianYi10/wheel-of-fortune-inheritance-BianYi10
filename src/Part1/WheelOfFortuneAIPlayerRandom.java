package Part1;

import java.util.HashSet;
import java.util.Random;
/**
 * Class using random letter BOT to play this game
 * @author Yi
 * @see Game
 * @see WheelOfFortuneAIGame
 * @see WheelOfFortunePlayer
 */
public class WheelOfFortuneAIPlayerRandom implements WheelOfFortunePlayer{
    private HashSet<Character> guessedLetter;

    public WheelOfFortuneAIPlayerRandom() {
        guessedLetter= new HashSet<>();
    }

    @Override
    public char nextGuess() {
        Random random=new Random();
        while(guessedLetter.size()<26) {
            char letter = (char) (random.nextInt(26) + 'a');
            if (!this.guessedLetter.contains(letter)) {
                guessedLetter.add(letter);
                return letter;
            }
        }
        return ' ';
    }

    @Override
    public String playerId() {
        return "Random Bot";
    }

    @Override
    public void reset() {
        guessedLetter.clear();
    }
}
