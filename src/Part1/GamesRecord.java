package Part1;

import java.util.Objects;

/**
 * Class to store each game record
 * @author Yi
 */
public class GamesRecord implements Comparable<GamesRecord>{
    //GameRecord keeps track of the score (integer) and player id (String) for a single play of a game.
    //It must implement Comparable and provide a default implementation of compareTo which compares scores.
    private int score;
    private String id;
    public int getScore(){
        return score;
    }
    public String getId(){
        return id;
    }

    public GamesRecord(int score, String id) {
        this.score = score;
        this.id = id;
    }

    @Override
    public int compareTo(GamesRecord o) {
        if(score<o.score){
            return 1;
        }
        else if(score>o.score){
            return -1;
        }
        return 0;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GamesRecord that = (GamesRecord) o;
        return score == that.score && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, id);
    }

    @Override
    public String toString() {
        return "GamesRecord{" +
                "score=" + score +
                ", id='" + id + '\'' +
                '}';
    }
}
