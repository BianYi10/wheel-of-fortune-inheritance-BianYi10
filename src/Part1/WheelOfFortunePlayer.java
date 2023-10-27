package Part1;

public interface WheelOfFortunePlayer {
    //get the next guess from the player
    public char nextGuess();
    //an id for the player
    public String playerId();
    //reset the player to start a new game
    public void reset();
}
