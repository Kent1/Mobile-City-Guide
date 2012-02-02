package be.ac.umons.gl.mobilecityguide.db;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class for using the Ranking Table
 * in the DB
 * 
 * @author Quentin Loos
 */
public class RankingDB extends DB{

  public RankingDB() {
  }
  
  public double getRank(int id){
    JSONArray jsonArray = this.query("SELECT Rank FROM Ranking WHERE Id = "+id+" LIMIT 0,1");
    JSONObject json = null;
    double retour = 0;
    if(jsonArray!=null){
      try {
        json = jsonArray.getJSONObject(0);
        retour = json.getDouble("Rank");
      } catch (JSONException e) {
        e.printStackTrace();
      }
    } 
    return retour;
  }
  
  public int getNBVote(int id){
    JSONArray jsonArray = this.query("SELECT NBVote FROM Ranking WHERE Id = "+id+" LIMIT 0,1");
    JSONObject json = null;
    int retour = 0;
    if(jsonArray!=null){
      try {
        json = jsonArray.getJSONObject(0);
        retour = json.getInt("NBVote");
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
    return retour;
  }
}