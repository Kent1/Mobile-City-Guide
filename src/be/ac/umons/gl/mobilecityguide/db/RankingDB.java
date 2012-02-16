package be.ac.umons.gl.mobilecityguide.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Class for using the Ranking Table in the DB
 * 
 * @author Quentin Loos
 */
public class RankingDB extends DB {
  /** Tag for log */
  private static final String tag = "RankingDB";

  /** MyDB Columns */
  private static final String TABLE_RANK = "RankingDB";
  private static final String COL_ID = "Id";
  private static final String COL_RANK = "Rank";

  /**
   * Constructor
   */
  public RankingDB(Context context) {
    super(context);
  }

  /**
   * Return my rank of a specified POI
   * 
   * @param id
   *          The id of the POI
   * @return My rank of the POI. 0 if we haven't already vote
   */
  public double getMyRank(int id) {
    db = myDB.getWritableDatabase();
    Cursor cursor = db.query(TABLE_RANK, new String[] { COL_ID, COL_RANK },
        COL_ID + " = " + id, null, null, null, null);
    if (cursor.getCount() == 0) {
      db.close();
      return 0.;
    }
    cursor.moveToFirst();
    double retour = cursor.getDouble(1);
    cursor.close();
    db.close();
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
    db = myDB.getWritableDatabase();
    if (oldRank == 0)
      db.insert(TABLE_RANK, null, values);
    else
      db.update(TABLE_RANK, values, COL_ID + " = " + id, null);
    db.close();
  }
}