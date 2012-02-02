package be.ac.umons.gl.mobilecityguide.db;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class for using the TAG Table
 * in the DB
 * 
 * @author Quentin Loos
 */
public class TagDB extends DB{

  /**
   * Constructor
   */
  public TagDB() {
  }
  
  /**
   * Return the list of tags available
   * 
   * @return List of tags
   */
  public ArrayList<String> getTagList(){
    JSONArray jsonArray = this.query("SELECT * FROM TAGList");
    ArrayList<String> str = new ArrayList<String>();
    JSONObject json = null;
    if(jsonArray != null){
      try {
        for(int i = 0; i<jsonArray.length(); i++){
          json = jsonArray.getJSONObject(i);
          str.add(json.getString("TAG"));
        }
      }
      catch (JSONException e){
        e.printStackTrace();
      }
    }
    return str;
  }
  
  /**
   * Return the tag of a specified POI
   * 
   * @param id
   *    the id of the POI
   * @return
   *    tag of the POI
   */
  public String getTag(int id){
    JSONArray jsonArray = this.query("SELECT TAG FROM TAG WHERE id = " + id + " LIMIT 0,1");
    JSONObject json = null;
    String retour = null;
    if(jsonArray!=null){
      try {
        json = jsonArray.getJSONObject(0);
        retour = json.getString("TAG");
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
    return retour;
  }
}