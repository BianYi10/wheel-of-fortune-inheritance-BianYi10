package Part1;

import java.util.ArrayList;
import java.util.Collections;
/**
 * Class to store all records
 * @author Yi
 */
public class AllGamesRecord {
    private ArrayList<GamesRecord> gamesRecords;

    public AllGamesRecord() {
        gamesRecords = new ArrayList<>();
    }

    public int add(GamesRecord gamesRecord){
        gamesRecords.add(gamesRecord);
        return 0;
    }
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
    public AllGamesRecord highGameList(int n){
        AllGamesRecord topN=new AllGamesRecord();
        Collections.sort(gamesRecords);
        for(int i=0;i<n;i++){
            topN.add(gamesRecords.get(i));
        }
        return topN;
    }

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

    public void printRecords (){
        for(int i=0;i<gamesRecords.size();i++){
            System.out.println(gamesRecords.get(i));
        }
    }

    @Override
    public String toString() {
        return "AllGamesRecord{" +
                "gamesRecords=" + gamesRecords +
                '}';
    }

}
