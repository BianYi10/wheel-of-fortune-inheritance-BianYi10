package Part1;

import java.util.Scanner;
/**
 * Class using real user to play this game
 * @author Yi
 * @see Game
 * @see WheelOfFortune
 */
public class WheelOfFortuneUserGame extends WheelOfFortune{

    @Override
    public char getGuess() {
        System.out.println("input a guess letter:");
        Scanner scanner = new Scanner(System.in);
        guessLetter=scanner.next();
        return guessLetter.charAt(0);
    }
    public static void main(String[] args) {
        WheelOfFortuneUserGame wheelOfFortuneUserGame = new WheelOfFortuneUserGame();
        AllGamesRecord record = wheelOfFortuneUserGame.playAll();
        System.out.println("record = ["+record+"]");
        System.out.println("recordTop2 = ["+record.highGameList(2)+"]");
        System.out.println("recordTop2Y = ["+record.highGameList("Y",2)+"]");
        System.out.println("recordAllAverage = ["+record.average()+"]");
        System.out.println("recordYAverage = ["+record.average("Y")+"]");
    }
}
