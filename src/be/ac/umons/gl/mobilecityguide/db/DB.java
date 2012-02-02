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
import org.json.JSONException;
import org.json.JSONObject;

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
  public JSONObject query(String query){
    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
    nameValuePairs.add(new BasicNameValuePair("request",query));
    String result = "";
    try{
      HttpClient httpclient = new DefaultHttpClient();
      HttpPost httppost = new HttpPost(adress);
      httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
      HttpResponse response = httpclient.execute(httppost);
      HttpEntity entity = response.getEntity();
      InputStream is = entity.getContent();
    
      BufferedReader reader = new BufferedReader(new InputStreamReader(is));
      String line = null;
      while ((line = reader.readLine()) != null)
        result = result + line + "\n";
      is.close();
    }catch(Exception e){
      System.out.println("[Error] POIDB.query ");
      e.printStackTrace();
    }
    
    JSONObject jsonObject = null;
    try {
      jsonObject = new JSONObject(result);
    } catch (JSONException e) {
      System.out.println("[Error] POIDB.query ");
      e.printStackTrace();
    }
    return jsonObject;
  }
}