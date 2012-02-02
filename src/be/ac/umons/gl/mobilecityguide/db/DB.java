package be.ac.umons.gl.mobilecityguide.db;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Class for using the DB.
 * 
 * @author Quentin Loos
 */
public class DB {
  /** DB adress */
  private final String adress = "http://sgl.umons.ac.be/mobilecityguide/cible.php";

  /**
   * Execute query and return the result in a JSONObject
   * 
   * @param query
   * @return
   */
  public JSONArray query(String query){
    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
    nameValuePairs.add(new BasicNameValuePair("request", query));
    String result = "";
    HttpClient httpclient = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(adress);
    try{
      httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
      HttpResponse response = httpclient.execute(httppost);
      HttpEntity entity = response.getEntity();
      InputStream is = entity.getContent();
    
      BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
      String line = null;
      while ((line = reader.readLine()) != null)
        result = result + line + "\n";
      is.close();
    }catch(Exception e){
      System.out.println("[Error] POIDB.query ");
      e.printStackTrace();
    }
    
    JSONArray jsonArray = null;
    try {
      jsonArray = new JSONArray(result);
    } catch (JSONException e) {
      System.out.println("[Error] POIDB.query ");
      e.printStackTrace();
    }
    return jsonArray;
  }
}