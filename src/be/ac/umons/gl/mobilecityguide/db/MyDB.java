package be.ac.umons.gl.mobilecityguide.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Class who manage SQLiteDB.
 * 
 * @author Quentin Loos
 */
public class MyDB extends SQLiteOpenHelper {
  /** DBName */
  private static final String DBName = "MobileCityGuideDB";
  /** Instance of myDB */
  private static MyDB instance;

  /** Request to create table Tag */
  private static final String TABLE_TAG = "TagDB";
  private static final String COL_TAG = "Tag";
  private static final String COL_BOOL = "Bool";

  private static final String CREATE_TABLETAG = "CREATE TABLE " + TABLE_TAG
      + " (" + COL_TAG + " varchar(30), " + COL_BOOL + " BOOLEAN );";

  /** Request to create table Rank */
  private static final String TABLE_RANK = "RankingDB";
  private static final String COL_ID = "Id";
  private static final String COL_RANK = "Rank";

  private static final String CREATE_TABLERANK = "CREATE TABLE " + TABLE_RANK
      + " (" + COL_ID + " INTEGER PRIMARY KEY, " + COL_RANK + " DOUBLE );";

  /**
   * Constructor
   * 
   * @param context
   *          The context of activity
   * @param createDB
   *          The SQL query to createDB if it does'nt exist
   */
  private MyDB(Context context) {
    super(context, DBName, null, 1);
  }

  /**
   * Return an instance of MyDB
   * 
   * @param context
   *          The context of activity
   * @param createDB
   *          The SQL query to createDB if it does'nt exist
   * @return
   */
  public static MyDB getInstance(Context context) {
    if (instance == null) {
      instance = new MyDB(context);
    }
    return instance;
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
    db.execSQL(CREATE_TABLETAG);
    db.execSQL(CREATE_TABLERANK);
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
    db.execSQL("DROP TABLE TagDB");
    db.execSQL("DROP TABLE RankingDB");
    onCreate(db);
  }
}
