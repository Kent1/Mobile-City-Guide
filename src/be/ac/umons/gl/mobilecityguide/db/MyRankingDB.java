package be.ac.umons.gl.mobilecityguide.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Class who manage SQLite DB storing the ranking made.
 * 
 * @author Quentin Loos
 */
public class MyRankingDB extends SQLiteOpenHelper {
  private static final String TABLE_RANK = "Ranking";
  private static final String COL_ID = "Id";
  private static final String COL_RANK = "Rank";

  /** Request to create table */
  private static final String CREATE_BDD = "CREATE TABLE " + TABLE_RANK + " ("
      + COL_ID + " INTEGER PRIMARY KEY, " + COL_RANK + " DOUBLE );";

  /**
   * Constructor
   * 
   * @param context
   * @param dbname
   * @param factory
   * @param dbversion
   */
  public MyRankingDB(Context context, String dbname, CursorFactory factory,
      int dbversion) {
    super(context, dbname, factory, dbversion);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite
   * .SQLiteDatabase)
   */
  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(CREATE_BDD);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite
   * .SQLiteDatabase, int, int)
   */
  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
  }

}
