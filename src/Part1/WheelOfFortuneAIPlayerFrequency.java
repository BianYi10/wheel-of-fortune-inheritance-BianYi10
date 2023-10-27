package Part1;

import java.util.Objects;

/**
 * Class using Letter Frequency BOT to play this game
 * @author Yi
 * @see Game
 * @see WheelOfFortuneAIGame
 * @see WheelOfFortunePlayer
 */
public class WheelOfFortuneAIPlayerFrequency implements WheelOfFortunePlayer{

    private String frequency ="ETAINOSHRDLUCMFWYGPBVKQJXZ";
    private int indexOfFrequency = -1;
    @Override
    public char nextGuess() {
        indexOfFrequency++;
        System.out.println("indexOfFrequency=["+indexOfFrequency+"]");
        if(indexOfFrequency<26){
            return frequency.charAt(indexOfFrequency);
        }
        return ' ';
    }

    @Override
    public String playerId() {
        return "Frequency Bot";
    }

    @Override
    public void reset() {
        indexOfFrequency=-1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WheelOfFortuneAIPlayerFrequency that = (WheelOfFortuneAIPlayerFrequency) o;
        return indexOfFrequency == that.indexOfFrequency && Objects.equals(frequency, that.frequency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frequency, indexOfFrequency);
    }

    @Override
    public String toString() {
        return "WheelOfFortuneAIPlayerFrequency{" +
                "frequency='" + frequency + '\'' +
                ", indexOfFrequency=" + indexOfFrequency +
                '}';
    }
}
