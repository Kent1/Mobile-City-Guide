package be.ac.umons.gl.mobilecityguide.db;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * Class for using the LangList Table in the DB
 * 
 * @author Quentin Loos
 */
public class LangDB extends DB {
  /** Tag for log */
  private static final String tag = "LangDB";

  /**
   * Constructor
   */
  public LangDB() {
  }

  /**
   * Return the list of languages available
   * 
   * @return the list of the languages
   */
  public ArrayList<String> getLangList() {
    String query = "SELECT * FROM LangList";
    JSONArray jsonArray = this.query(query);
    ArrayList<String> str = new ArrayList<String>();
    JSONObject json = null;
    if (jsonArray != null) {
      try {
        for (int i = 0; i < jsonArray.length(); i++) {
          json = jsonArray.getJSONObject(i);
          str.add(json.getString("Language"));
        }
      } catch (JSONException e) {
        Log.e(tag, "JSONException : " + e.getMessage());
      }
    }
    return str;
  }
}