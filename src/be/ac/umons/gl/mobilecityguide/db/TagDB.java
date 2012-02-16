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

  /** MyDB Columns */
  private static final String TABLE_TAG = "TagDB";
  private static final String COL_TAG = "Tag";
  private static final String COL_BOOL = "Bool";

  /**
   * Constructor
   */
  public TagDB(Context context) {
    super(context);
  }

  /**
   * get the tag list from the distant DB and retrieve it in the SQLiteDB
   */
  public void retrieveTagList() {
    ArrayList<String> list = this.getTagList();
    db = myDB.getWritableDatabase();
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
    db.close();
  }

  /**
   * Return the list of tags available in the distant DB
   * 
   * @return List of tags
   */
  private ArrayList<String> getTagList() {
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
   * Return the list of tag in SQLiteDB
   * 
   * @return tag list
   */
  public ArrayList<String> getTagListMyDB() {
    ArrayList<String> list = new ArrayList<String>();
    db = myDB.getWritableDatabase();
    Cursor cursor = db.query(TABLE_TAG, new String[] { COL_TAG, COL_BOOL },
        null, null, null, null, null);
    cursor.moveToFirst();
    for (int i = 0; i < cursor.getCount(); i++) {
      list.add(cursor.getString(0));
      cursor.moveToNext();
    }
    cursor.close();
    db.close();
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
    db = myDB.getWritableDatabase();
    Cursor cursor = db.query(TABLE_TAG, new String[] { COL_TAG, COL_BOOL },
        COL_TAG + " = \"" + tag + "\"", null, null, null, null);
    if (cursor.getCount() == 0) {
      cursor.close();
      db.close();
      return true;
    }
    cursor.moveToFirst();
    boolean retour = (cursor.getInt(1) > 0 ? true : false);
    cursor.close();
    db.close();
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
    ContentValues values = new ContentValues();
    values.put(COL_TAG, tag);
    values.put(COL_BOOL, bool);
    db = myDB.getWritableDatabase();
    db.update(TABLE_TAG, values, COL_TAG + " = \"" + tag + "\"", null);
    db.close();
  }
}