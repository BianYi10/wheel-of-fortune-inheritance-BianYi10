package Part1;
/**
 * interface to play this game
 * @author Yi
 * @see WheelOfFortuneAIGame
 * @see WheelOfFortuneAIPlayerAlphabet
 * @see WheelOfFortuneAIPlayerFrequency
 * @see WheelOfFortuneAIPlayerRandom
 */
public interface WheelOfFortunePlayer {
    //get the next guess from the player
    public char nextGuess();
    //an id for the player
    public String playerId();
    //reset the player to start a new game
    public void reset();
}
