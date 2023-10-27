package Part1;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Class for WheelOfFortuneAIGame using BOT to play this game
 * @author Yi
 * @see Game
 * @see WheelOfFortune
 */
public class WheelOfFortuneAIGame extends WheelOfFortune{
    private ArrayList<WheelOfFortunePlayer> wofList;
    private int index;
    public WheelOfFortuneAIGame() {
        index=0;
        wofList=new ArrayList<>();
        wofList.add(new WheelOfFortuneAIPlayerRandom());
    }
    public WheelOfFortuneAIGame(int mode) {
        index=0;
        wofList=new ArrayList<>();
        if(0==mode){
            wofList.add(new WheelOfFortuneAIPlayerRandom());
        }
        else if(1==mode){
            wofList.add(new WheelOfFortuneAIPlayerFrequency());
        }
        else if(2==mode){
            wofList.add(new WheelOfFortuneAIPlayerAlphabet());
        }
    }

    public WheelOfFortuneAIGame(ArrayList<WheelOfFortunePlayer> list) {
        index=0;
        wofList=list;
    }
    public boolean getNextIndex(){
        if(index>=wofList.size()){
            return false;
        }
        index++;
        return true;
    }
    @Override
    public char getGuess() {
        guessLetter=String.valueOf(wofList.get(index).nextGuess());
        System.out.println("input a guess letter:["+guessLetter.charAt(0)+"]");
        return guessLetter.charAt(0);
    }
    @Override
    public boolean playNext(){
        System.out.println("********index**********["+index+"]");
        if(!randomPhrase()){
            System.out.println(wofList.get(index).playerId()+" uses up all phrases");
            System.out.println(wofList.get(index).playerId()+" Is Part1.Game Over");
            index++;
            readPhrases();
            randomPhrase();
        }
        //guessLetter= String.valueOf(wofList.get(index).nextGuess());
        if(index<wofList.size()) playerId = wofList.get(index).playerId();
        else {
            return false;
        }
        reset();
        wofList.get(index).reset();
        return true;
    }
    public static void main(String[] args) {
        //Part1.WheelOfFortuneAIGame wheelOfFortuneAIGame = new Part1.WheelOfFortuneAIGame();
        //Part1.WheelOfFortuneAIGame wheelOfFortuneAIGame = new Part1.WheelOfFortuneAIGame(1);
        ArrayList<WheelOfFortunePlayer>List=new ArrayList<>();
        List.add(new WheelOfFortuneAIPlayerFrequency());
        List.add(new WheelOfFortuneAIPlayerRandom());
        List.add(new WheelOfFortuneAIPlayerAlphabet());
        WheelOfFortuneAIGame wheelOfFortuneAIGame = new WheelOfFortuneAIGame(List);
        AllGamesRecord record = wheelOfFortuneAIGame.playAll();
        System.out.println("record = ["+record+"]");
        System.out.println("recordTop2 = ["+record.highGameList(2)+"]");
        System.out.println("recordTop2Alphabet = ["+record.highGameList("Alphabet Bot",2)+"]");
        System.out.println("recordTop2Frequency = ["+record.highGameList("Frequency Bot",2)+"]");
        System.out.println("recordTop2Random = ["+record.highGameList("Random Bot",2)+"]");
        System.out.println("recordAllAverage = ["+record.average()+"]");
        System.out.println("recordAlphabetAverage = ["+record.average("Alphabet Bot")+"]");
        System.out.println("recordFrequencyAverage = ["+record.average("Frequency Bot")+"]");
        System.out.println("recordRandomAverage = ["+record.average("Random Bot")+"]");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        WheelOfFortuneAIGame that = (WheelOfFortuneAIGame) o;
        return index == that.index && Objects.equals(wofList, that.wofList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), wofList, index);
    }

    @Override
    public String toString() {
        return "WheelOfFortuneAIGame{" +
                "wofList=" + wofList +
                ", index=" + index +
                '}';
    }
}
