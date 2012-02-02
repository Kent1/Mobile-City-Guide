package be.ac.umons.gl.mobilecityguide.db;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class for using the LangList Table
 * in the DB
 * 
 * @author Quentin Loos
 */
public class LangDB extends DB{
  
  public LangDB(){
  }
  
  public ArrayList<String> getLangList(){
    JSONArray jsonArray = this.query("SELECT * FROM LangList");
    ArrayList<String> str = new ArrayList<String>();
    JSONObject json = null;
    if(jsonArray != null){
      try {
        for(int i = 0; i<jsonArray.length(); i++){
          json = jsonArray.getJSONObject(i);
          str.add(json.getString("Language"));
        }
      }
      catch (JSONException e){
        e.printStackTrace();
      }
    }
    return str;
  }
}