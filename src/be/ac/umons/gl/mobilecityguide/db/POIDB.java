package be.ac.umons.gl.mobilecityguide.db;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import be.ac.umons.gl.mobilecityguide.poi.POI;

/**
 * Class for using the POI Table
 * in the DB
 * 
 * @author Quentin Loos
 */
public class POIDB extends DB{
  /** Tag for log */
  private static final String tag = "POIDB";
  
  /**
   * Construtor
   */
  public POIDB(){
  }

  /**
   * get the POI with id specified
   * 
   * @param id
   * @return POI with id specified
   */
  public POI getPOI(int id){
    JSONArray jsonArray = query("SELECT * FROM POI WHERE Id = " + id + " LIMIT 0,1");
    JSONObject json = null;
    POI retour = null;
    if(jsonArray!=null){
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
   *    The name of POI
   * @return 
   *    POI with name specified or null
   */
  public POI getPOI(String name){
    JSONArray jsonArray = this.query("SELECT * FROM POI WHERE Name = \"" + name + "\" LIMIT 0,1");
    JSONObject json = null;
    POI retour = null;
    if(jsonArray!=null){
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
   * Get the POIs where is in the range specified
   * 
   * @param latitude
   * @param longitude
   * @param range
   * @return
   */
  public ArrayList<POI> getPOI(int latitude, int longitude, int range){
    return null;
  }

  /**
   * Create POI from JSONObject
   * 
   * @param json
   *    the JSONObject
   * @throws JSONException 
   */
  private POI toPOI(JSONObject json) throws JSONException{
    POI poi = new POI();
    poi.setId(json.getInt("Id"));
    poi.setName(json.getString("Name"));
    poi.setLongitude(json.getDouble("Longitude"));
    poi.setLatitude(json.getDouble("Latitude"));
    poi.setAddress(json.getString("Address"));
    return poi;
  }
}