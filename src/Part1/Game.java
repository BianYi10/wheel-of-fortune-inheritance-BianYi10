package Part1;
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
}
