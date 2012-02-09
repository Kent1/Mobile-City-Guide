package be.ac.umons.gl.mobilecityguide.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Class who manage SQLite DB storing the ranking made.
 * 
 * @author Quentin Loos
 */
public class MyDB extends SQLiteOpenHelper {
  /** DBName */
  private static final String DBName = "MobileCityGuideDB";

  private final String createDB;

  /**
   * Constructor
   * 
   * @param context
   * @param dbname
   * @param factory
   * @param dbversion
   */
  public MyDB(Context context, String createDB) {
    super(context, DBName, null, 1);
    this.createDB = createDB;
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
    db.execSQL(createDB);
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
  }
}
