package be.ac.umons.gl.mobilecityguide.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

/**
 * Class for using the DB.
 * 
 * @author Quentin Loos
 */
public class DB {
  /** DB address */
  private final String adress = "http://sgl.umons.ac.be/mobilecityguide/cible.php";
  /** Tag for log */
  private static final String tag = "DB";

  /**
   * Execute query and return the result in a JSONArray
   * 
   * @param query
   *          the query submitted to the DB
   * @return The response in JSONArray
   */
  public JSONArray query(String query) {
    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
    nameValuePairs.add(new BasicNameValuePair("request", query));
    String result = "";
    HttpClient httpclient = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(adress);

    try {
      httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
    } catch (UnsupportedEncodingException e) {
    }

    HttpResponse response = null;
    try {
      response = httpclient.execute(httppost);
    } catch (ClientProtocolException e) {
      Log.e(tag, "ClientProtocolException : " + e.getMessage());
    } catch (IOException e) {
      Log.e(tag, "IOException : " + e.getMessage());
    }

    HttpEntity entity = response.getEntity();
    InputStream is = null;
    try {
      is = entity.getContent();
    } catch (IllegalStateException e) {
      Log.e(tag, "IllegalStateException : " + e.getMessage());
    } catch (IOException e) {
      Log.e(tag, "IOException : " + e.getMessage());
    }

    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
    } catch (UnsupportedEncodingException e) {
      Log.e(tag, "UnsupportedEncodingException : " + e.getMessage());
    }

    String line = null;
    try {
      while ((line = reader.readLine()) != null)
        result = result + line + "\n";
      is.close();
    } catch (IOException e) {
      Log.e(tag, "IOException : " + e.getMessage());
    }

    JSONArray jsonArray = null;
    try {
      jsonArray = new JSONArray(result);
    } catch (JSONException e) {
      Log.e(tag, "JSONException : " + e.getMessage());
    }
    return jsonArray;
  }
}