package be.ac.umons.gl.mobilecityguide.map;

/**
 * Class helper for compute distance with longitude & latitude Code inspired
 * 
 * @author Quentin Loos
 */
public class DistanceHelper {
  /** The earth’s radius */
  public final static int R = 6371;

  /**
   * Conert degree to radian
   * 
   * @param d
   *          degree
   * @return radian
   */
  private static double rad(double d) {
    return (d * Math.PI / 180);
  }

  /**
   * Compute the distance in kilometer between two points
   * 
   * @param lat1
   *          firt point's latitude
   * @param lon1
   *          firt point's longitude
   * @param lat2
   *          second point's latitude
   * @param lon2
   *          second point's longitude
   * @return distance in km between the two points
   */
  public static double distance(double lat1, double lon1, double lat2,
      double lon2) {
    double theta = lon1 - lon2;
    double dist = Math.sin(rad(lat1)) * Math.sin(rad(lat2))
        + Math.cos(rad(lat1)) * Math.cos(rad(lat2)) * Math.cos(rad(theta));
    dist = Math.acos(dist);
    dist = dist * R;
    return (dist);
  }
}
