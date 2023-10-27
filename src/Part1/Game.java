package Part1;

import java.util.Objects;

/**
 * Abstract class for Game
 * @author Yi
 * @see WheelOfFortune
 * @see AllGamesRecord
 */
public abstract class Game {
    //a method that plays a set of games and records and returns an Part1.AllGamesRecord object for the set.
    private AllGamesRecord allGamesRecord;
    public Game() {
        allGamesRecord=new AllGamesRecord();
    }

    public AllGamesRecord playAll(){
        while(playNext()){
            allGamesRecord.add(play());
        }
        return allGamesRecord ;
    }

    public abstract GamesRecord play();
    public abstract boolean playNext();

    @Override
    public String toString() {
        return "Game{" +
                "allGamesRecord=" + allGamesRecord +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(allGamesRecord, game.allGamesRecord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(allGamesRecord);
    }
}
