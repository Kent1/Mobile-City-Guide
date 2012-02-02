package be.ac.umons.gl.mobilecityguide.db;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import be.ac.umons.gl.mobilecityguide.poi.POI;

/**
 * Class for using the POI Table
 * in the DB
 * 
 * @author Quentin Loos
 */
public class POIDB extends DB{
  
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
    try {
      json = jsonArray.getJSONObject(0);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    POI retour = null;
    if(jsonArray!=null)
      retour = this.toPOI(json);
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
    JSONArray jsonArray = query("SELECT * FROM POI WHERE Name = \"" + name + "\" LIMIT 0,1");
    JSONObject json = null;
    try {
      json = jsonArray.getJSONObject(0);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    POI retour = null;
    if(json!=null)
      retour = this.toPOI(json);
    return retour;
  }
  
  /**
   * Get the POIs where is in the range specified
   * 
   * @param longitude
   * @param latitude
   * @param range
   * @return
   */
  public ArrayList<POI> getPOI(int longitude, int latitude, int range){
    return null;
  }

  /**
   * Create POI from JSONObject
   * 
   * @param json
   *    the JSONObject
   */
  private POI toPOI(JSONObject json){
    POI poi = new POI();
    try {
      poi.setId(json.getInt("Id"));
      poi.setName(json.getString("Name"));
      poi.setLongitude(json.getDouble("Longitude"));
      poi.setLatitude(json.getDouble("Latitude"));
      poi.setAddress(json.getString("Adress"));
    } catch (JSONException e){
      e.printStackTrace();
    }
    return poi;
  }
}