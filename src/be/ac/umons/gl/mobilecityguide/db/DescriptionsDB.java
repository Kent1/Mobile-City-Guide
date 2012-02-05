package be.ac.umons.gl.mobilecityguide.db;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * Class for using the Descriptions Table in the DB
 * 
 * @author Quentin Loos
 */
public class DescriptionsDB extends DB {
  /** Tag for log */
  private static final String tag = "DescriptionsDB";

  /**
   * Constructor
   */
  public DescriptionsDB() {
  }

  /**
   * Get the description of a POI with id
   * 
   * @param id
   *          the POI id
   * @return the description
   */
  public String getDescription(int id) {
    String retour = "No description";
    // TO DO
    // Language
    String query = "SELECT * FROM Descriptions where id = " + id
        + " AND Language = \"FR\" LIMIT 0,1";
    JSONArray jsonArray = this.query(query);
    JSONObject json = null;
    if (jsonArray != null)
      try {
        json = jsonArray.getJSONObject(0);
        retour = json.getString("Description");
      } catch (JSONException e) {
        Log.e(tag, "JSONException : " + e.getMessage());
      }
    return retour;
  }
}