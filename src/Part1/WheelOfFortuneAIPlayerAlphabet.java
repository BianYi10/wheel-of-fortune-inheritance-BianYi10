package Part1;
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



}
