package Part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
/**
 * Class for WheelOfFortune extends from Game
 * @author Yi
 * @see Game
 * @see WheelOfFortuneUserGame
 * @see WheelOfFortuneAIGame
 */
public abstract class WheelOfFortune extends Game{
    private List<String> phraseList;
    private String phrase;
    protected String guessLetter;
    protected String playerId;
    protected int score;
    private StringBuilder hiddenPhase;
    private int n=26;
    private HashSet<Character> misses;
    public abstract char getGuess();

    public WheelOfFortune() {
        misses = new HashSet<>();
        readPhrases();
        playerId = "";
    }

    public void getPlayerId() {
        if (playerId.isEmpty()) {
            System.out.println("input a player ID :");
            Scanner scanner = new Scanner(System.in);
            playerId = scanner.next();
        }
    }
    // Get a phrases list from a file of phrases
    public void readPhrases(){
        phraseList=new ArrayList<>();
        try {
            phraseList = Files.readAllLines(Paths.get("phrases.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    // Get random phrase from a phrases list.
    public boolean randomPhrase(){
        Random rand = new Random();
        if(phraseList.size()<=0){
            return false;
        }
        int r= rand.nextInt(phraseList.size());
        phrase = phraseList.get(r);
        phraseList.remove(r);
        return true;
    }
    public void getHiddenPhrase(){
        hiddenPhase= new StringBuilder(phrase);
        for(int i=0;i<hiddenPhase.length();i++){
            if(Character.isLetter(hiddenPhase.charAt(i))) {
                hiddenPhase.setCharAt(i, '*');
            }
        }
    }
    public boolean comparisonIgnoresCase(int index) {
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
    public int processGuess(){
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
            return -1;
        }
        else{
            return 0;
        }
    }
    public boolean checkGuess(){
        if(guessLetter.length()!=1 || !(Character.isLetter(guessLetter.charAt(0)))) {
            System.out.println("you should only input one letter ");
            return false;
        }
        return true;
    }
    public boolean checkGameResult(){
        for(int j=0;j<hiddenPhase.length();j++){
            if(hiddenPhase.charAt(j)=='*'){
                return false;
            }
        }
        return true;
    }
    public void reset(){
        score=0;
        misses.clear();
    }

    public GamesRecord play(){
        GamesRecord gamesRecord;

        System.out.println("Instructions: In this game,you will see a hidden phrase with '*'.");
        System.out.println("You can only guess"+n+"times.You can ignore the case of letter.");
        System.out.println("Once the letter you put in is in this phrase,you will unlock the position of the letter in the phrase.");
        System.out.println("***************************************");
        System.out.println("******wheel of fortune V1.0.i begin******");
        System.out.println("***************************************");
        System.out.println("random phrase:"+phrase);

        getHiddenPhrase();

        for(int i=0;i<n;i++){
            System.out.println("chances left:" + (n-i));
            // Show the hidden phrase with input letter(Define the hidden phrase as a StringBuilder)
            System.out.println("encrypted answer:"+hiddenPhase);
            // Number of misses
            System.out.println("number of misses:"+misses.size());
            // Previous misses
            System.out.println("previous misses:"+misses);
            System.out.println("player ID is ["+playerId+"]");
            System.out.println("guess letter is ["+getGuess()+"]");

            if(!checkGuess()){
                i--;
                continue;
            }

            int resultOfGuess=processGuess();
            if(0==resultOfGuess){
                i--;
                score++;
                score++;
                System.out.println("letter matches");
            }
            else if(-1==resultOfGuess){
                if(misses.contains(guessLetter.toLowerCase().charAt(0))){
                    i--;
                    System.out.println("you have guessed this wrong letter ["+guessLetter.charAt(0)+"] before.");
                    continue;
                }
                score--;
                misses.add(guessLetter.toLowerCase().charAt(0));
                System.out.println("letter doesn't match");
            }
            else if(1==resultOfGuess){
                i--;
                System.out.println("you have guessed this right letter ["+guessLetter.charAt(0)+"] before.");
            }
            // Check whether the game is over
            if(checkGameResult()){
                System.out.println("You Win!");
                gamesRecord=new GamesRecord(score,playerId);
                return gamesRecord;
            }
        }
        System.out.println("Sorry,You Lose!");
        gamesRecord=new GamesRecord(score,playerId);
        return gamesRecord;
    }
    public boolean playNext(){
        if(!randomPhrase()){
            System.out.println("use up all phrases");
            System.out.println("Part1.Game Over");
            return false;
        }
        getPlayerId();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Continue playing game Y/N");
        String choice =scanner.next();
        reset();
        return choice.equals("Y")||choice.equals("y");
    }

}
