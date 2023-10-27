package Part1;

import java.util.Objects;

/**
 * Class using Alphabet BOT to play this game
 * @author Yi
 * @see Game
 * @see WheelOfFortuneAIGame
 * @see WheelOfFortunePlayer
 */
public class WheelOfFortuneAIPlayerAlphabet implements WheelOfFortunePlayer{

    private String alphabet ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int indexOfAlphabet = -1;
    @Override
    public char nextGuess() {
        indexOfAlphabet++;
        if(indexOfAlphabet<26){
            return alphabet.charAt(indexOfAlphabet);
        }
        return ' ';
    }
    @Override
    public String playerId() {
        return "Alphabet Bot";
    }

    @Override
    public void reset() {
        indexOfAlphabet = -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WheelOfFortuneAIPlayerAlphabet that = (WheelOfFortuneAIPlayerAlphabet) o;
        return indexOfAlphabet == that.indexOfAlphabet && Objects.equals(alphabet, that.alphabet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alphabet, indexOfAlphabet);
    }

    @Override
    public String toString() {
        return "WheelOfFortuneAIPlayerAlphabet{" +
                "alphabet='" + alphabet + '\'' +
                ", indexOfAlphabet=" + indexOfAlphabet +
                '}';
    }
}
