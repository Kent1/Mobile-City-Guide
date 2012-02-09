package be.ac.umons.gl.mobilecityguide.db;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

/**
 * Class for using the TAG Table in the DB
 * 
 * @author Quentin Loos
 */
public class TagDB extends DB {
  /** Tag for log */
  private static final String tag = "TagDB";

  /** MyDB */
  private static final String TABLE_TAG = "TagDB";
  private static final String COL_TAG = "Tag";
  private static final String COL_BOOL = "Bool";

  /** Request to create table */
  private static final String CREATE_BDD = "CREATE TABLE IF NOT EXISTS "
      + TABLE_TAG + " (" + COL_TAG + " varchar(30), " + COL_BOOL
      + " BOOLEAN );";

  /**
   * Constructor
   */
  public TagDB(Context context) {
    super(context, CREATE_BDD);
    // Android fais de la merde dans les DB ou quoi ?
    this.open();
    myDB.onCreate(db);
    this.close();
  }

  /**
   * get the tag list from the distant DB and retrieve it in the SQLiteDB
   */
  public void retrieveTagList() {
    ArrayList<String> list = this.getTagList();
    this.open();
    Cursor cursor = db.query(TABLE_TAG, new String[] { COL_TAG, COL_BOOL },
        null, null, null, null, null);
    cursor.moveToFirst();
    for (int i = 0; i < cursor.getCount(); i++) {
      String tag = cursor.getString(0);
      if (!list.remove(tag)) {
        ContentValues values = new ContentValues();
        values.put(COL_TAG, tag);
        values.put(COL_BOOL, true);
        db.insert(TABLE_TAG, null, values);
      }
      cursor.moveToNext();
    }
    cursor.close();
    for (String tag : list) {
      ContentValues values = new ContentValues();
      values.put(COL_TAG, tag);
      values.put(COL_BOOL, true);
      db.insert(TABLE_TAG, null, values);
    }
    this.close();
  }

  /**
   * Return the list of tags available
   * 
   * @return List of tags
   */
  public ArrayList<String> getTagList() {
    String query = "SELECT * FROM TAGList";
    JSONArray jsonArray = this.query(query);
    ArrayList<String> list = new ArrayList<String>();
    JSONObject json = null;
    if (jsonArray != null) {
      try {
        for (int i = 0; i < jsonArray.length(); i++) {
          json = jsonArray.getJSONObject(i);
          list.add(json.getString("TAG"));
        }
      } catch (JSONException e) {
        Log.e(tag, "JSONException : " + e.getMessage());
      }
    }
    return list;
  }

  /**
   * Return the tag of a specified POI
   * 
   * @param id
   *          the id of the POI
   * @return tag of the POI
   */
  public String getTag(int id) {
    String query = "SELECT TAG FROM TAG WHERE id = " + id + " LIMIT 0,1";
    JSONArray jsonArray = this.query(query);
    JSONObject json = null;
    String retour = null;
    if (jsonArray != null) {
      try {
        json = jsonArray.getJSONObject(0);
        retour = json.getString("TAG");
      } catch (JSONException e) {
        Log.e(tag, "JSONException : " + e.getMessage());
      }
    }
    return retour;
  }

  /**
   * Return the list of tag in SQLiteDB
   * 
   * @return tag list
   */
  public ArrayList<String> getTagListMyDB() {
    ArrayList<String> list = new ArrayList<String>();
    this.open();
    Cursor cursor = db.query(TABLE_TAG, new String[] { COL_TAG, COL_BOOL },
        null, null, null, null, null);
    cursor.moveToFirst();
    for (int i = 0; i < cursor.getCount(); i++) {
      list.add(cursor.getString(0));
      cursor.moveToNext();
    }
    cursor.close();
    this.close();
    return list;
  }

  /**
   * Return if the tag if display or not, that's mean if the bool tag is true or
   * false
   * 
   * @param tag
   *          The tag
   * @return true if the tags is selected
   */
  public boolean isTagSelected(String tag) {
    if (tag == null)
      return true;
    this.open();
    Cursor cursor = db.query(TABLE_TAG, new String[] { COL_TAG, COL_BOOL },
        COL_TAG + " = \"" + tag + "\"", null, null, null, null);
    if (cursor.getCount() == 0)
      return true;
    cursor.moveToFirst();
    boolean retour = (cursor.getInt(1) > 0 ? true : false);
    cursor.close();
    this.close();
    return retour;
  }

  /**
   * Set if the tag is to display or not
   * 
   * @param tag
   *          The tag to change status
   * @param bool
   *          The status of the tag
   */
  public void selectTag(String tag, boolean bool) {
    this.open();
    ContentValues values = new ContentValues();
    values.put(COL_TAG, tag);
    values.put(COL_BOOL, bool);
    db.update(TABLE_TAG, values, COL_TAG + " = \"" + tag + "\"", null);
    this.close();
  }
}