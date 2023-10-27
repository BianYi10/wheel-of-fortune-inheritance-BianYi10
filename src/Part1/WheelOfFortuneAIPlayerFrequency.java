package Part1;

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
}
