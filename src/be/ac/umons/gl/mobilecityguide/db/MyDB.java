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
  /** DB Name */
  private static final String DBName = "MobileCityGuideDB";
  /** Instance of myDB */
  private static MyDB instance;

  /** Request to create table POI */
  private static final String TABLE_POI = "POIDB";
  private static final String POI_COL_ID = "Id";
  private static final String POI_COL_NAME = "Name";
  private static final String POI_COL_LON = "Longitude";
  private static final String POI_COL_LAT = "Latitude";
  private static final String POI_COL_ADD = "Address";
  private static final String POI_COL_TAG = "Tag";
  private static final String POI_COL_PRI = "Price";
  private static final String POI_COL_DUR = "Duration";
  private static final String POI_COL_RAN = "Ranking";
  private static final String POI_COL_DES = "Description";
  private static final String POI_COL_VIS = "Visited";

  private static final String CREATE_TABLEPOI = "CREATE TABLE " + TABLE_POI
      + " (" + POI_COL_ID + " INTEGER PRIMARY KEY, " + POI_COL_NAME
      + " VARCHAR(30), " + POI_COL_LON + " DOUBLE, " + POI_COL_LAT
      + " DOUBLE, " + POI_COL_ADD + " VARCHAR(300), " + POI_COL_TAG
      + " VARCHAR(30), " + POI_COL_PRI + " DOUBLE, " + POI_COL_DUR
      + " INTEGER, " + POI_COL_RAN + " DOUBLE, " + POI_COL_DES
      + " VARCHAR(500), " + POI_COL_VIS + " BOOLEAN );";

  /** Request to create table Tag */
  private static final String TABLE_TAG = "TagDB";
  private static final String TAG_COL_TAG = "Tag";
  private static final String TAG_COL_BOOL = "Bool";

  private static final String CREATE_TABLETAG = "CREATE TABLE " + TABLE_TAG
      + " (" + TAG_COL_TAG + " varchar(30), " + TAG_COL_BOOL + " BOOLEAN );";

  /** Request to create table Rank */
  private static final String TABLE_RANK = "RankingDB";
  private static final String RANK_COL_ID = "Id";
  private static final String RANK_COL_RANK = "Rank";

  private static final String CREATE_TABLERANK = "CREATE TABLE " + TABLE_RANK
      + " (" + RANK_COL_ID + " INTEGER PRIMARY KEY, " + RANK_COL_RANK
      + " DOUBLE );";

  /**
   * Constructor
   * 
   * @param context
   *          The context of application
   */
  private MyDB(Context context) {
    super(context, DBName, null, 1);
  }

  /**
   * Return an instance of MyDB
   * 
   * @param context
   *          The context of application
   * @return The instance of MyDB
   */
  public static MyDB getInstance(Context context) {
    if (instance == null) {
      instance = new MyDB(context.getApplicationContext());
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
    db.execSQL(CREATE_TABLEPOI);
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
    db.execSQL("DROP TABLE POIDB");
    onCreate(db);
  }
}
