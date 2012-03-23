package be.ac.umons.gl.mobilecityguide.route;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.android.maps.GeoPoint;

/**
 * @author Quentin Loos
 */
public class Route implements Iterable<Leg> {

  /** A route is a suite of legs */
  private final List<Leg> legs;

  private String polyline;

  /**
   * A List of legs. For routes that contain no waypoints, the route will
   * consist of a single "leg," but for routes that define one or more
   * waypoints, the route will consist of one or more legs, corresponding to the
   * specific legs of the journey.
   * 
   * @param destination
   * @param origin
   */
  public Route() {
    legs = new ArrayList<Leg>();
  }

  /**
   * @param legs
   *          the legs to set
   */
  public void addLeg(Leg leg) {
    legs.add(leg);
  }

  public int getDistance() {
    int distance = 0;
    for (Leg leg : legs)
      distance += leg.getDistance();
    return distance;
  }

  public int getDuration() {
    int duration = 0;
    for (Leg leg : legs)
      duration += leg.getDuration();
    return duration;
  }

  /**
   * @return the polyline
   */
  public String getPolyline() {
    return polyline;
  }

  /**
   * @param polyline
   *          the polyline to set
   */
  public void setPolyline(String polyline) {
    this.polyline = polyline;
  }

  /**
   * Decode the encoded overview polyline of Google Maps Direction in a List of
   * GeoPoint.
   * 
   * Algorithm found here :
   * http://jeffreysambells.com/posts/2010/05/27/decoding-
   * polylines-from-google-maps-direction-api-with-java/
   * 
   * @param encoded
   *          Overview Polyline
   * 
   * @return List of GeoPoint
   */
  public List<GeoPoint> decodePoly() {

    List<GeoPoint> poly = new ArrayList<GeoPoint>();
    int index = 0, len = polyline.length();
    int lat = 0, lng = 0;

    while (index < len) {
      int b, shift = 0, result = 0;
      do {
        b = polyline.charAt(index++) - 63;
        result |= (b & 0x1f) << shift;
        shift += 5;
      } while (b >= 0x20);
      int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
      lat += dlat;

      shift = 0;
      result = 0;
      do {
        b = polyline.charAt(index++) - 63;
        result |= (b & 0x1f) << shift;
        shift += 5;
      } while (b >= 0x20);
      int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
      lng += dlng;

      GeoPoint p = new GeoPoint((int) ((lat / 1E5) * 1E6),
          (int) ((lng / 1E5) * 1E6));
      poly.add(p);
    }

    return poly;
  }

  @Override
  public Iterator<Leg> iterator() {
    return legs.iterator();
  }
}
