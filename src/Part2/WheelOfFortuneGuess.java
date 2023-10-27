package Part2;

import Part1.AllGamesRecord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
/**
 * Class for game WheelOfFortuneGuess extends from GuessingGame
 * @author Yi
 * @see GuessingGame
 */
public class WheelOfFortuneGuess extends GuessingGame{
    private String guessLetter;
    private StringBuilder hiddenPhase;
    private String phrase;
    private List<String> phraseList;
    private HashSet<Character> misses;
    @Override
    public void showPhrase(){
        System.out.println("random phrase:"+phrase);
    }
    @Override
    public void showHiddenPhrase(){
        System.out.println("previous misses:"+misses);
        System.out.println("hidden phrase:"+hiddenPhase);
    }
    @Override
    public void getHiddenPhrase() {
        hiddenPhase= new StringBuilder(phrase);
        for(int i=0;i<hiddenPhase.length();i++){
            if(Character.isLetter(hiddenPhase.charAt(i))) {
                hiddenPhase.setCharAt(i, '*');
            }
        }
    }

    @Override
    public boolean checkGuess() {
        if(guessLetter.length()!=1 || !(Character.isLetter(guessLetter.charAt(0)))) {
            System.out.println("you should only input one letter ");
            return false;
        }
        return true;
    }
    @Override
    public StringBuilder getGuess() {
        System.out.println("input a guess letter:");
        Scanner scanner = new Scanner(System.in);
        guessLetter=scanner.next();
        StringBuilder s=new StringBuilder(guessLetter);
        return s;
    }
    private boolean comparisonIgnoresCase(int index) {
        char charOfPhase=' ';
        char charOfGuess=' ';
        //check if it is space return false
        if(!Character.isLetter(phrase.charAt(index))){
            return false;
        }
        charOfPhase = phrase.toLowerCase().charAt(index);
        charOfGuess = guessLetter.toLowerCase().charAt(0);

        return charOfPhase==charOfGuess;
    }
    @Override
    public int processGuess() {
        int countOfMiss=0;
        for(int i=0;i<this.hiddenPhase.length();i++){
            // Correct
            if (comparisonIgnoresCase(i)) {
                // The user should NOT be penalized for guessing a previously correct or incorrect guess, but should be notified.
                // Check with previous guess
                if(Character.isLetter(this.hiddenPhase.charAt(i))){
                    //you have guessed this right letter before.
                    return 1;
                }
                // Change the result（show the letter in the phrase）
                hiddenPhase.setCharAt(i, phrase.charAt(i));
            } else { // Wrong
                // Result will not be changed
                // Missed count ++
                countOfMiss++;
            }
        }

        if(countOfMiss==this.hiddenPhase.length()){
            if(misses.contains(guessLetter.toLowerCase().charAt(0))){
                return 1;
            }
            misses.add(guessLetter.toLowerCase().charAt(0));
            return -1;
        }
        else{
            return 0;
        }
    }

    @Override
    public boolean checkGameResult() {
        for(int j=0;j<hiddenPhase.length();j++){
            if(hiddenPhase.charAt(j)=='*'){
                return false;
            }
        }
        return true;
    }
    private void readPhrases(){
        phraseList=new ArrayList<>();
        try {
            phraseList = Files.readAllLines(Paths.get("phrases.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    @Override
    public boolean randomPhrase() {
        Random rand = new Random();
        if(phraseList.size()<=0){
            return false;
        }
        int r= rand.nextInt(phraseList.size());
        phrase = phraseList.get(r);
        phraseList.remove(r);
        return true;
    }

    @Override
    public void reset() {
        clearScore();
        misses.clear();
    }

    public WheelOfFortuneGuess() {
        misses = new HashSet<>();
        readPhrases();
        setPlayerId("");
    }

    public static void main(String[] args) {
        WheelOfFortuneGuess wheelOfFortuneGuess = new WheelOfFortuneGuess();
        AllGamesRecord record = wheelOfFortuneGuess.playAll();
        System.out.println("record = ["+record+"]");
        System.out.println("recordTop2 = ["+record.highGameList(2)+"]");
        System.out.println("recordTop2Y = ["+record.highGameList("Y",2)+"]");
        System.out.println("recordAllAverage = ["+record.average()+"]");
        System.out.println("recordYAverage = ["+record.average("Y")+"]");
    }
}
