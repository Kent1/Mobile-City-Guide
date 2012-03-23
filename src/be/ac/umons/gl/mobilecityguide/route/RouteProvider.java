package be.ac.umons.gl.mobilecityguide.route;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import be.ac.umons.gl.mobilecityguide.poi.Itinerary;
import be.ac.umons.gl.mobilecityguide.poi.POI;

import com.google.android.maps.GeoPoint;

public class RouteProvider {
  /** Tag for log */
  private final String tag = "Route";
  private final String url = "http://maps.googleapis.com/maps/api/directions/json?";

  /** Location origin */
  private final GeoPoint origin;
  /** Location destination */
  private final GeoPoint destination;
  /** Itinerary of the route */
  private final Itinerary itinerary;

  private final List<GeoPoint> waypoints;

  /** mode of transport */
  private final String mode = "walking";

  public RouteProvider(GeoPoint origin, Itinerary itinerary) {
    this.origin = origin;
    this.destination = null;
    this.itinerary = itinerary;
    waypoints = new ArrayList<GeoPoint>();
    for (POI poi : itinerary.getList()) {
      waypoints.add(poi);
    }
  }

  public RouteProvider(GeoPoint origin, GeoPoint destination,
      Itinerary itinerary) {
    this.origin = origin;
    this.destination = destination;
    this.itinerary = itinerary;
    waypoints = new ArrayList<GeoPoint>();
    for (POI poi : itinerary.getList()) {
      waypoints.add(poi);
    }
  }

  public Route startDriving() {
    String urlget = getUrl();
    JSONObject JSONRoute = getJSON(urlget);
    return routeFromJSON(JSONRoute);
  }

  public String getUrl() {
    String from = (origin.getLatitudeE6() / 1E6) + ","
        + (origin.getLongitudeE6() / 1E6);
    String to = "";
    if (destination != null)
      to = (destination.getLatitudeE6() / 1E6) + ","
          + (destination.getLongitudeE6() / 1E6);

    StringBuilder urlget = new StringBuilder();
    urlget.append(url);
    urlget.append("&origin=" + from);
    urlget.append("&destination=" + to);

    // WayPoints
    StringBuilder waypoints = new StringBuilder("");
    for (POI poi : itinerary)
      waypoints.append(poi.getLatitude() + "," + poi.getLongitude());

    urlget.append("&waypoints=optimize:true%7C" + waypoints.toString());
    urlget.append("&mode" + mode);
    urlget.append("&sensor=true");

    return urlget.toString();
  }

  public JSONObject getJSON(String urlget) {

    HttpClient httpclient = new DefaultHttpClient();
    HttpGet httpGet = new HttpGet(urlget);

    HttpResponse response = null;
    try {
      response = httpclient.execute(httpGet);
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

    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    String result = "";
    String line = null;
    try {
      while ((line = reader.readLine()) != null)
        result = result + line + "\n";
      is.close();
    } catch (IOException e) {
      Log.e(tag, "IOException : " + e.getMessage());
    }

    if (result.equals(""))
      return null;

    JSONObject jsonObject = null;
    try {
      jsonObject = new JSONObject(result);
    } catch (JSONException e) {
      Log.e(tag, "JSONException : " + e.getMessage());
    }

    try {
      return jsonObject.getJSONArray("routes").getJSONObject(0);
    } catch (JSONException e) {
      return null;
    }
  }

  public Route routeFromJSON(JSONObject JSONRoute) {
    Route route = new Route();

    JSONArray legs = null;
    try {
      legs = JSONRoute.getJSONArray("legs");
    } catch (JSONException e) {
      e.printStackTrace();
    }

    for (int i = 0; i < legs.length(); i++) {
      try {
        route.addLeg(parseLeg(legs.getJSONObject(i)));
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }

    try {
      route.setPolyline(JSONRoute.getJSONObject("overview_polyline").getString(
          "points"));
    } catch (JSONException e) {
      e.printStackTrace();
    }

    return route;
  }

  public Leg parseLeg(JSONObject JSONleg) throws JSONException {

    // Création de l'objet Leg sans les steps
    int lat = (int) JSONleg.getJSONObject("start_location").getDouble("lat");
    int lon = (int) JSONleg.getJSONObject("start_location").getDouble("lng");
    GeoPoint from = new GeoPoint(lat, lon);

    lat = (int) JSONleg.getJSONObject("end_location").getDouble("lat");
    lon = (int) JSONleg.getJSONObject("end_location").getDouble("lng");
    GeoPoint to = new GeoPoint(lat, lon);

    int distance = Integer.parseInt(JSONleg.getJSONObject("distance")
        .getString("value"));
    int duration = Integer.parseInt(JSONleg.getJSONObject("duration")
        .getString("value"));

    Leg leg = new Leg(from, to, distance, duration);

    // Ajout des steps
    JSONArray steps = null;
    try {
      steps = JSONleg.getJSONArray("steps");

      for (int i = 0; i < steps.length(); i++) {
        leg.addStep(parseStep(steps.getJSONObject(i)));
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }

    return leg;
  }

  private Step parseStep(JSONObject step) throws JSONException {

    int lat = (int) step.getJSONObject("start_location").getDouble("lat");
    int lon = (int) step.getJSONObject("start_location").getDouble("lng");
    GeoPoint from = new GeoPoint(lat, lon);

    lat = (int) step.getJSONObject("end_location").getDouble("lat");
    lon = (int) step.getJSONObject("end_location").getDouble("lng");
    GeoPoint to = new GeoPoint(lat, lon);

    int distance = Integer.parseInt(step.getJSONObject("distance").getString(
        "value"));
    int duration = Integer.parseInt(step.getJSONObject("duration").getString(
        "value"));

    String instruction = step.getString("html_instructions");

    return new Step(from, to, distance, duration, instruction);
  }

}