package be.ac.umons.gl.mobilecityguide.db;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class for using the Descriptions Table
 * in the DB
 * 
 * @author Quentin Loos
 */
public class DescriptionsDB extends DB{
  
  /**
   * Constructor
   */
  public DescriptionsDB(){
  }
  
  /**
   * Get the description of a POI with id
   * 
   * @param id
   *    the POI id
   * @return the description
   */
  public String getDescription(int id){
    String retour = "No description";
    // TO DO
    // Language
    JSONObject json = this.query("SELECT * FROM Descriptions where id = "
        + id + " AND Language = \"FR\" LIMIT 0,1");
    if(json != null)
      try {
        retour = json.getString("Description");
      } catch (JSONException e) {
        e.printStackTrace();
      }
    return retour;
  }
}