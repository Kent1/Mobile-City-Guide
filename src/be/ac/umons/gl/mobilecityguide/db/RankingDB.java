package be.ac.umons.gl.mobilecityguide.db;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * Class for using the Ranking Table in the DB
 * 
 * @author Quentin Loos
 */
public class RankingDB extends DB {
  /** Tag for log */
  private static final String tag = "RankingDB";

  /**
   * Constructor
   */
  public RankingDB() {
  }

  /**
   * Return the rank of a specified POI
   * 
   * @param id
   *          the id of a POI
   * @return the rank of the POI
   */
  public double getRank(int id) {
    String query = "SELECT Rank FROM Ranking WHERE Id = " + id + " LIMIT 0,1";
    JSONArray jsonArray = this.query(query);
    JSONObject json = null;
    double retour = 0;
    if (jsonArray != null) {
      try {
        json = jsonArray.getJSONObject(0);
        retour = json.getDouble("Rank");
      } catch (JSONException e) {
        Log.e(tag, "JSONException : " + e.getMessage());
      }
    }
    return retour;
  }

  /**
   * Return the rank of a specified POI
   * 
   * @param id
   *          the id of a POI
   * @return the rank of the POI
   */
  public void rank(int id, double rank) {
    String newRank = "((Rank*NBVote)" + rank + ")/(NBVote+1)";
    String query = "UPDATE Ranking SET Rank= " + newRank
        + ", NBVote=NBVote+1 WHERE Id = " + id + "";
    this.query(query);
  }

  /**
   * Return the number of vote for the specified POI
   * 
   * @param id
   *          the id of a POI
   * @return The number of vote of the POI
   */
  public int getNBVote(int id) {
    String query = "SELECT NBVote FROM Ranking WHERE Id = " + id + " LIMIT 0,1";
    JSONArray jsonArray = this.query(query);
    JSONObject json = null;
    int retour = 0;
    if (jsonArray != null) {
      try {
        json = jsonArray.getJSONObject(0);
        retour = json.getInt("NBVote");
      } catch (JSONException e) {
        Log.e(tag, "JSONException : " + e.getMessage());
      }
    }
    return retour;
  }
}