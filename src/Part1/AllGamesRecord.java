package Part1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * Class to store all records
 * @author Yi
 */
public class AllGamesRecord {
    private ArrayList<GamesRecord> gamesRecords;
    /**
     * constructor ofAllGamesRecord
     * @param
     */
    public AllGamesRecord() {
        gamesRecords = new ArrayList<>();
    }
    /**
     * Add new gamesRecord in gamesRecord List gamesRecords
     * @param gamesRecord
     */
    public int add(GamesRecord gamesRecord){
        gamesRecords.add(gamesRecord);
        return 0;
    }
    /**
     * Get average in all gamesRecords
     * @param
     */
    public int average(){
        int sum=0;
        for(int i=0;i<gamesRecords.size();i++){
            sum+=gamesRecords.get(i).getScore();
        }
        if(gamesRecords.isEmpty()){
            return 0;
        }
        return sum/gamesRecords.size();
    }
    /**
     * Get average in playerId's gamesRecords
     * @param  playerId
     * @return an number of int which means the average
     */
    public int average(String playerId){
        int sum=0;
        int count=0;
        for(int i=0;i<gamesRecords.size();i++){
            if(gamesRecords.get(i).getId().equals(playerId)){
                sum+=gamesRecords.get(i).getScore();
                count++;
            }
        }
        if(0==count){
            return 0;
        }
        return sum/count;
    }
    /**
     * Get top n gamesRecords in all records
     * @param  n
     * @return a AllGamesRecord object
     */
    public AllGamesRecord highGameList(int n){
        AllGamesRecord topN=new AllGamesRecord();
        Collections.sort(gamesRecords);
        for(int i=0;i<n;i++){
            topN.add(gamesRecords.get(i));
        }
        return topN;
    }
    /**
     * Get top n gamesRecords in playerId's records
     * @param  playerId
     * @param  n
     * @return a AllGamesRecord object
     */
    public AllGamesRecord highGameList(String playerId, int n){
        int count=0;
        AllGamesRecord topN=new AllGamesRecord();
        Collections.sort(gamesRecords);
        for(int i=0;i<gamesRecords.size();i++){
            if(gamesRecords.get(i).getId().equals(playerId)){
                topN.add(gamesRecords.get(i));
                count++;
            }
            if(count>=n){
                return topN;
            }
        }
        return topN;
    }
    /**
     * print all records
     */
    public void printRecords (){
        for(int i=0;i<gamesRecords.size();i++){
            System.out.println(gamesRecords.get(i));
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllGamesRecord that = (AllGamesRecord) o;
        return Objects.equals(gamesRecords, that.gamesRecords);
    }

    @Override
    public String toString() {
        return "AllGamesRecord{" +
                "gamesRecords=" + gamesRecords +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(gamesRecords);
    }

}
