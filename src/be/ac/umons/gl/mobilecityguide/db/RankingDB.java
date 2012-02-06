package be.ac.umons.gl.mobilecityguide.db;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Class for using the Ranking Table in the DB
 * 
 * @author Quentin Loos
 */
public class RankingDB extends DB {
  /** DBName & Tag for log */
  private static final String DBName = "RankingDB";

  /** MyDB */
  protected MyRankingDB myDB;
  protected SQLiteDatabase db;
  private static final String TABLE_RANK = "Ranking";
  private static final String COL_ID = "Id";
  private static final String COL_RANK = "Rank";

  /**
   * Constructor
   */
  public RankingDB(Context context) {
    myDB = new MyRankingDB(context, DBName, null, 1);
  }

  /**
   * Open the SQLite DB
   */
  public void open() {
    db = myDB.getWritableDatabase();
  }

  /**
   * Close the SQLite DB
   */
  public void close() {
    db.close();
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
        Log.e(DBName, "JSONException : " + e.getMessage());
      }
    }
    return retour;
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
        Log.e(DBName, "JSONException : " + e.getMessage());
      }
    }
    return retour;
  }

  /**
   * Return my rank of a specified POI
   * 
   * @param id
   *          The id of the POI
   * @return My rank of the POI. 0 if we haven't already vote
   */
  public double getMyRank(int id) {
    open();
    Cursor cursor = db.query(TABLE_RANK, new String[] { COL_ID, COL_RANK },
        COL_ID + " = " + id, null, null, null, null);
    if (cursor.getCount() == 0)
      return 0.;
    cursor.moveToFirst();
    double retour = cursor.getDouble(1);
    cursor.close();
    return retour;
  }

  /**
   * Rank for a POI
   * 
   * @param id
   *          The POI to rank
   * @param rank
   *          The rank that we want to put
   * @param oldRank
   *          The oldRank. 0 if we haven't already vote
   */
  public void rank(int id, double rank, double oldRank) {
    String newRank;
    String newNBVote;
    if (oldRank == 0) {
      newRank = "((Rank*NBVote)+" + rank + ")/(NBVote+1)";
      newNBVote = "NBVote+1";
    } else {
      // already vote
      newRank = "(((Rank*NBVote)-" + oldRank + ")+" + rank + ")/(NBVote)";
      newNBVote = "NBVote";
    }
    String query = "UPDATE Ranking SET Rank= " + newRank + ", NBVote="
        + newNBVote + " WHERE Id = " + id + "";
    this.query(query);
    ContentValues values = new ContentValues();
    values.put(COL_ID, id);
    values.put(COL_RANK, rank);
    if (oldRank == 0)
      db.insert(TABLE_RANK, null, values);
    else
      db.update(TABLE_RANK, values, COL_ID + " = " + id, null);
  }
}