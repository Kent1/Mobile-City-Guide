package be.ac.umons.gl.mobilecityguide.db;

import java.util.ArrayList;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import be.ac.umons.gl.mobilecityguide.poi.POI;

/**
 * Class for using the POI Table in the DB
 * 
 * @author Quentin Loos
 */
public class POIDB extends DB {
  /** Tag for log */
  private static final String tag = "POIDB";

  /** MyDB Columns */
  private static final String TABLE_POI = "POIDB";
  private static final String COL_ID = "Id";
  private static final String COL_NAME = "Name";
  private static final String COL_LAT = "Latitude";
  private static final String COL_LON = "Longitude";
  private static final String COL_ADD = "Address";
  private static final String COL_TAG = "Tag";
  private static final String COL_PRI = "Price";
  private static final String COL_DUR = "Duration";
  private static final String COL_RAN = "Ranking";
  private static final String COL_DES = "Description";
  private static final String COL_VIS = "Visited";

  /**
   * Construtor
   */
  public POIDB(Context context) {
    super(context);
  }

  /**
   * get the poi list from the distant DB and retrieve it in the SQLiteDB
   * 
   * @return list of POIs
   */
  public ArrayList<POI> retrievePOIList(double latitude, double longitude,
      int radius) {
    ArrayList<POI> list = this.getPOI(latitude, longitude, radius);
    db = myDB.getWritableDatabase();
    db.execSQL("DELETE FROM " + TABLE_POI);
    for (POI poi : list) {
      ContentValues values = new ContentValues();
      values.put(COL_ID, poi.getId());
      values.put(COL_NAME, poi.getName());
      values.put(COL_LAT, poi.getLatitude());
      values.put(COL_LON, poi.getLongitude());
      values.put(COL_ADD, poi.getAddress());
      values.put(COL_TAG, poi.getTag());
      values.put(COL_PRI, poi.getPrice());
      values.put(COL_DUR, poi.getDuration());
      values.put(COL_RAN, poi.getRank());
      values.put(COL_DES, poi.getDescription());
      values.put(COL_VIS, poi.isVisited());
      db.insert(TABLE_POI, null, values);
    }
    db.close();
    return list;
  }

  /**
   * get the POI with specified id in the SQLite DB
   * 
   * @param id
   * @return POI with id specified
   */
  public POI getPOI(int id) {
    db = myDB.getWritableDatabase();
    Cursor cursor = db.query(TABLE_POI, new String[] { COL_ID, COL_NAME,
        COL_LAT, COL_LON, COL_ADD, COL_TAG, COL_PRI, COL_DUR, COL_RAN, COL_DES,
        COL_VIS }, COL_ID + " = " + id, null, null, null, null);
    if (cursor.getCount() == 0) {
      cursor.close();
      db.close();
      return null;
    }
    cursor.moveToFirst();
    POI retour = cursorToPOI(cursor);
    cursor.close();
    db.close();
    return retour;
  }

  /**
   * get the POI with name specified
   * 
   * @param name
   *          The name of POI
   * @return POI with name specified or null
   */
  public POI getPOI(String name) {
    String locale = Locale.getDefault().getLanguage();
    String query = "SELECT * FROM POI JOIN TAG ON TAG.id=POI.id "
        + "JOIN Infos ON Infos.id=POI.id JOIN Ranking ON Ranking.id=POI.id "
        + "JOIN Descriptions ON Descriptions.id=POI.id AND Descriptions.Language = \""
        + locale + "\" WHERE Name = \"" + name + "\" LIMIT 0,1";
    JSONArray jsonArray = this.query(query);
    JSONObject json = null;
    POI retour = null;
    if (jsonArray != null) {
      try {
        json = jsonArray.getJSONObject(0);
        retour = this.JSONToPOI(json);
      } catch (JSONException e) {
        Log.e(tag, "JSONException : " + e.getMessage());
      }
    }
    return retour;
  }

  /**
   * Get the POIs where is in the range specified
   * 
   * @param latitude
   * @param longitude
   * @param radius
   * @return A list of POI in the range specified
   */
  private ArrayList<POI> getPOI(double latitude, double longitude, int radius) {
    String whereClause = "(6371*acos(sin(radians(" + latitude
        + ")) * sin(radians(`latitude`)) + cos(radians(" + latitude
        + ")) * cos(radians(`latitude`)) * cos(radians(" + longitude
        + " - `longitude`)))) <= " + radius;
    String locale = Locale.getDefault().getLanguage();
    String query = "SELECT * FROM POI JOIN TAG ON TAG.id=POI.id "
        + "JOIN Infos ON Infos.id=POI.id JOIN Ranking ON Ranking.id=POI.id "
        + "JOIN Descriptions ON Descriptions.id=POI.id AND Descriptions.Language = \""
        + locale + "\" WHERE " + whereClause;
    JSONArray jsonArray = this.query(query);
    JSONObject json = null;
    ArrayList<POI> retour = new ArrayList<POI>();
    if (jsonArray != null) {
      try {
        for (int i = 0; i < jsonArray.length(); i++) {
          json = jsonArray.getJSONObject(i);
          retour.add(this.JSONToPOI(json));
        }
      } catch (JSONException e) {
        Log.e(tag, "JSONException : " + e.getMessage());
      }
    }
    return retour;
  }

  /**
   * Return All the POI in the SQLite DB in a list
   * 
   * @return list of POI
   */
  public ArrayList<POI> getPOIList() {
    ArrayList<POI> list = new ArrayList<POI>();
    db = myDB.getWritableDatabase();
    Cursor cursor = db.query(TABLE_POI, new String[] { COL_ID, COL_NAME,
        COL_LON, COL_LAT, COL_ADD, COL_TAG, COL_PRI, COL_DUR, COL_RAN, COL_DES,
        COL_VIS }, null, null, null, null, null);
    cursor.moveToFirst();
    for (int i = 0; i < cursor.getCount(); i++) {
      list.add(cursorToPOI(cursor));
      cursor.moveToNext();
    }
    cursor.close();
    db.close();
    return list;
  }

  /**
   * Create POI from JSONObject
   * 
   * @param json
   *          the JSONObject
   * @throws JSONException
   */
  private POI JSONToPOI(JSONObject json) throws JSONException {
    int id = json.getInt("Id");
    double longitude = json.getDouble("Longitude");
    double latitude = json.getDouble("Latitude");

    POI poi = new POI(id, latitude, longitude);

    poi.setName(json.getString("Name"));
    poi.setAddress(json.getString("Address"));
    poi.setTag(json.getString("TAG"));
    poi.setPrice(json.getDouble("Price"));
    poi.setDuration(json.getInt("Duration"));
    poi.setRank(json.getDouble("Rank"));
    poi.setDescription(json.getString("Description"));
    poi.setVisited(false);
    return poi;
  }

  /**
   * Create POI from Cursor (SQLiteDB)
   * 
   * @param cursor
   */
  private POI cursorToPOI(Cursor cursor) {
    int id = cursor.getInt(0);
    double latitude = cursor.getDouble(3);
    double longitude = cursor.getDouble(2);

    POI poi = new POI(id, latitude, longitude);

    poi.setName(cursor.getString(1));
    poi.setAddress(cursor.getString(4));
    poi.setTag(cursor.getString(5));
    poi.setPrice(cursor.getDouble(6));
    poi.setDuration(cursor.getInt(7));
    poi.setRank(cursor.getDouble(8));
    poi.setDescription(cursor.getString(9));
    poi.setVisited((cursor.getInt(10) > 0 ? true : false));
    return poi;
  }
}