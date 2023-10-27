package Part2;

import Part1.AllGamesRecord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Mastermind extends GuessingGame{
    private int CODESIZE;
    private StringBuilder secret;
    private StringBuilder guess;
    private StringBuilder hiddenPhase;
    private static String color ="RGBYOP";
    private HashSet<StringBuilder> misses;
    private List<String> phraseList;
    public Mastermind() {
        CODESIZE=4;
        secret=new StringBuilder();
        guess=new StringBuilder();
        misses = new HashSet<>();
        readPhrases();
        setPlayerId("");
    }

    public int checkPartials(StringBuilder secretSB, StringBuilder guessSB) {
        // compare secret to guess
        int i=0;

        int partials=0;
        while (i<CODESIZE) {
            int j=0;
            while (j<CODESIZE) {
                if (secretSB.charAt(i) == guessSB.charAt(j)) {
                    partials = partials + 1;
                    secretSB.setCharAt(i,'-');
                    guessSB.setCharAt(j,'*');
                }
                j++;
            }
            i++;
        }
        return partials;
    }
    public int checkExacts(StringBuilder secretSB, StringBuilder guessSB) {
        // compare secret to guess
        int i=0;
        int exacts=0;
        while (i<CODESIZE) {
            if (secretSB.charAt(i) == guessSB.charAt(i)) {
                exacts = exacts + 1;
                secretSB.setCharAt(i,'-');
                guessSB.setCharAt(i,'*');
            }
            i++;
        }

        return exacts;
    }

    @Override
    public void getHiddenPhrase() {
        hiddenPhase= new StringBuilder();
        for(int i=0;i<4;i++){
            hiddenPhase.append('*');
        }
    }

    @Override
    public boolean checkGuess() {
        if(4!=guess.length()){
            return false;
        }
        for(int i = 0;i<guess.length();i++){
            char c = guess.charAt(i);
            //R, G, B, Y, O, P
            if (c!='R'&&c!='G'&&c!='B'&&c!='Y'&&c!='O'&&c!='P') {
                System.out.println("4 color should be in R, G, B, Y, O, P");
                return false;
            }
        }
        return true;
    }

    @Override
    public int processGuess() {
        StringBuilder secretSB= new StringBuilder(secret);
        StringBuilder guessSB=new StringBuilder(guess);
        System.out.println("secretSB=["+secretSB+"]");
        System.out.println("guessSB=["+guessSB+"]");
        int exacts = checkExacts(guessSB,secretSB);
        int partials = checkPartials(guessSB,secretSB);
        System.out.println("exacts=["+exacts+"]");
        System.out.println("partials=["+partials+"]");
        if(4==exacts){
            System.out.println("you got it.");
            return 0;
        }
        else{
            if(misses.contains(guess)){
                System.out.println("you have guessed it.");
                return 1;
            }
            misses.add(guess);
            System.out.println("you guess wrong.");
            return -1;
        }
    }

    @Override
    public boolean checkGameResult() {
        StringBuilder secretSB= new StringBuilder(secret);
        StringBuilder guessSB=new StringBuilder(guess);
        if(4==checkExacts(guessSB,secretSB)){
            return true;
        }
        return false;
    }

    private void readPhrases(){
        phraseList=new ArrayList<>();
        try {
            phraseList = Files.readAllLines(Paths.get("secret.txt"));
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
        secret = new StringBuilder(phraseList.get(r));
        phraseList.remove(r);
        return true;
    }

    @Override
    public void reset() {
        clearScore();
        misses.clear();
    }

    @Override
    public StringBuilder getGuess() {
        System.out.println("input 4 guess color:");
        Scanner scanner = new Scanner(System.in);
        guess=new StringBuilder(scanner.next());
        return guess;
    }

    @Override
    public void showPhrase(){
        System.out.println("previous misses:"+misses);
        System.out.println("random secret:"+secret);
    }

    @Override
    public void showHiddenPhrase() {
        System.out.println("hidden secret:"+hiddenPhase);
    }
    public static void main(String[] args) {
        // here is main code for testing...
        // note that we copy the data before calling check functions
        // as those functions cross out in secret/guess and we want
        // to keep pristine copies of them.
        Mastermind mastermind = new Mastermind();
        AllGamesRecord record = mastermind.playAll();
        System.out.println("record = ["+record+"]");
        System.out.println("recordTop2 = ["+record.highGameList(2)+"]");
        System.out.println("recordTop2Y = ["+record.highGameList("Y",2)+"]");
        System.out.println("recordAllAverage = ["+record.average()+"]");
        System.out.println("recordYAverage = ["+record.average("Y")+"]");

    }
}
