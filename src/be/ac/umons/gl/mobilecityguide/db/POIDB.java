package be.ac.umons.gl.mobilecityguide.db;

import java.util.ArrayList;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

  /**
   * Construtor
   */
  public POIDB() {
  }

  /**
   * get the POI with id specified
   * 
   * @param id
   * @return POI with id specified
   */
  public POI getPOI(int id) {
    String query = "SELECT * FROM POI JOIN TAG ON TAG.id=POI.id "
        + "JOIN Infos ON Infos.id=POI.id JOIN Ranking ON Ranking.id=POI.id "
        + "WHERE POI.Id = " + id + " LIMIT 0,1";
    JSONArray jsonArray = query(query);
    JSONObject json = null;
    POI retour = null;
    if (jsonArray != null) {
      try {
        json = jsonArray.getJSONObject(0);
        retour = this.toPOI(json);
      } catch (JSONException e) {
        Log.e(tag, "JSONException : " + e.getMessage());
      }
    }
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
    String query = "SELECT * FROM POI JOIN TAG ON TAG.id=POI.id "
        + "JOIN Infos ON Infos.id=POI.id JOIN Ranking ON Ranking.id=POI.id "
        + "WHERE Name = \"" + name + "\" LIMIT 0,1";
    JSONArray jsonArray = this.query(query);
    JSONObject json = null;
    POI retour = null;
    if (jsonArray != null) {
      try {
        json = jsonArray.getJSONObject(0);
        retour = this.toPOI(json);
      } catch (JSONException e) {
        Log.e(tag, "JSONException : " + e.getMessage());
      }
    }
    return retour;
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
    String locale = Locale.getDefault().getLanguage();

    String query = "SELECT * FROM Descriptions where id = " + id
        + " AND Language = \"" + locale + "\" LIMIT 0,1";

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

  /**
   * Get the POIs where is in the range specified
   * 
   * @param latitude
   * @param longitude
   * @param lon_span
   * @param range
   * @return
   */
  public ArrayList<POI> getPOI(double latitude, double longitude,
      double lat_span, double lon_span) {
    String query = "SELECT * FROM POI JOIN TAG ON TAG.id=POI.id "
        + "JOIN Infos ON Infos.id=POI.id JOIN Ranking ON Ranking.id=POI.id WHERE latitude <= "
        + (latitude + lat_span) + " AND latitude >= " + (latitude - lat_span)
        + " AND longitude <= " + (longitude + lon_span) + " AND longitude >= "
        + (longitude - lon_span);
    JSONArray jsonArray = this.query(query);
    JSONObject json = null;
    ArrayList<POI> retour = new ArrayList<POI>();
    if (jsonArray != null) {
      try {
        for (int i = 0; i < jsonArray.length(); i++) {
          json = jsonArray.getJSONObject(i);
          retour.add(this.toPOI(json));
        }
      } catch (JSONException e) {
        Log.e(tag, "JSONException : " + e.getMessage());
      }
    }
    return retour;
  }

  /**
   * Create POI from JSONObject
   * 
   * @param json
   *          the JSONObject
   * @throws JSONException
   */
  private POI toPOI(JSONObject json) throws JSONException {
    POI poi = new POI();
    int id = json.getInt("Id");
    poi.setId(id);
    poi.setName(json.getString("Name"));
    poi.setLongitude(json.getDouble("Longitude"));
    poi.setLatitude(json.getDouble("Latitude"));
    poi.setAddress(json.getString("Address"));
    poi.setTag(json.getString("TAG"));
    poi.setPrice(json.getDouble("Price"));
    poi.setDuration(json.getInt("Duration"));
    poi.setRank(json.getDouble("Rank"));
    return poi;
  }
}