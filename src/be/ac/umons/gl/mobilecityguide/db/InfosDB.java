package be.ac.umons.gl.mobilecityguide.db;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * Class for using the Infos Table in the DB
 * 
 * @author Quentin Loos
 */
public class InfosDB extends DB {
  /** Tag for log */
  private static final String tag = "InfosDB";

  /**
   * Constructor
   */
  public InfosDB() {
  }

  /**
   * Get the price for a POI with id
   * 
   * @param id
   *          the id of the POI
   * @return the price of the POI
   */
  public double getPrice(int id) {
    String query = "SELECT Price FROM Infos WHERE Id = " + id + " LIMIT 0,1";
    JSONArray jsonArray = this.query(query);
    JSONObject json = null;
    double retour = 0;
    if (jsonArray != null)
      try {
        json = jsonArray.getJSONObject(0);
        retour = json.getDouble("Price");
      } catch (JSONException e) {
        Log.e(tag, "JSONException : " + e.getMessage());
      }
    return retour;
  }

  /**
   * Get the duration of a POI with id
   * 
   * @param id
   *          the id of the POI
   * @return the duration of the POI
   */
  public int getDuration(int id) {
    String query = "SELECT Duration FROM Infos WHERE Id = " + id + " LIMIT 0,1";
    JSONArray jsonArray = this.query(query);
    int retour = 0;
    JSONObject json = null;
    if (jsonArray != null)
      try {
        json = jsonArray.getJSONObject(0);
        retour = json.getInt("Duration");
      } catch (JSONException e) {
        Log.e(tag, "JSONException : " + e.getMessage());
      }
    return retour;
  }
}