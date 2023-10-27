package Part2;

import Part1.Game;
import Part1.GamesRecord;

import java.util.Scanner;

public abstract class GuessingGame extends Game {
    private int n=10;
    private String playerId;
    private int score;

    public void getPlayerId() {
        if (playerId.isEmpty()) {
            System.out.println("input a player ID :");
            Scanner scanner = new Scanner(System.in);
            playerId = scanner.next();
        }
    }
    public void setPlayerId(String id) {
        playerId = id;
    }

    @Override
    public GamesRecord play() {
        showPhrase();
        GamesRecord gamesRecord;
        getHiddenPhrase();
        for(int i=0;i<n;i++){
            System.out.println("chances left:" + (n-i));
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
                System.out.println("letter matches.");
            }
            else if(-1==resultOfGuess){
                score--;
                System.out.println("letter doesn't match.");
            }
            else if(1==resultOfGuess){
                i--;
                System.out.println("you have guessed this letter before.");
            }

            if(checkGameResult()){
                System.out.println("You Win!");
                gamesRecord=new GamesRecord(score,playerId);
                return gamesRecord;
            }
            showHiddenPhrase();
        }
        return null;
    }

    @Override
    public boolean playNext(){
        if(!randomPhrase()){
            System.out.println("use up all phrases");
            System.out.println("Game Over");
            return false;
        }
        getPlayerId();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Continue playing game Y/N");
        String choice =scanner.next();
        reset();
        return choice.equals("Y")||choice.equals("y");
    }
    public void clearScore(){
        score=0;
    }
    public abstract void getHiddenPhrase();
    public abstract boolean checkGuess();
    public abstract int processGuess();
    public abstract boolean checkGameResult();
    public abstract boolean randomPhrase();
    public abstract void reset();
    public abstract StringBuilder getGuess();
    public abstract void showPhrase();
    public abstract void showHiddenPhrase();
}
