package be.ac.umons.gl.mobilecityguide.db;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class for using the Infos Table
 * in the DB
 * 
 * @author Quentin Loos
 */
public class InfosDB extends DB{

  /**
   * Constructor
   */
  public InfosDB() {
  }
  
  /**
   * Get the price for a POI with id
   * 
   * @param id
   *    the id of the POI
   * @return
   *    the price of the POI
   */
  public double getPrice(int id){
    JSONObject json = this.query("SELECT Price FROM Infos WHERE Id = "+id+" LIMIT 0,1");
    double retour = 0;
    if(json!=null)
      try {
        retour = json.getDouble("Price");
      } catch (JSONException e) {
        e.printStackTrace();
      }
    return retour;
  }
  
  /**
   * Get the duration of a POI with id
   * 
   * @param id
   *    the id of the POI
   * @return
   *    the duration of the POI
   */
  public int getDuration(int id){
    JSONObject json = this.query("SELECT Duration FROM Infos WHERE Id = "+id+" LIMIT 0,1");
    int retour = 0;
    if(json!=null)
      try {
        retour = json.getInt("Duration");
      } catch (JSONException e) {
        e.printStackTrace();
      }
    return retour;
  }
}