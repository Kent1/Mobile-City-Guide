package be.ac.umons.gl.mobilecityguide.route;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;

/**
 * @author Quentin Loos
 */
public class Leg {

  private final List<Step> steps;
  private final GeoPoint from, to;
  // private final String startAddress, endAddress;
  private final int distance, duration;

  public Leg(GeoPoint from, GeoPoint to, // String startAddress, String
                                         // endAddress,
      int distance, int duration) {
    this.from = from;
    this.to = to;
    // this.startAddress = startAddress;
    // this.endAddress = endAddress;
    this.distance = distance;
    this.duration = duration;
    steps = new ArrayList<Step>();
  }

  /**
   * @return the from
   */
  public GeoPoint getFrom() {
    return from;
  }

  /**
   * @return the to
   */
  public GeoPoint getTo() {
    return to;
  }

  public void addStep(Step step) {
    steps.add(step);
  }

  /**
   * @return the distance
   */
  public int getDistance() {
    return distance;
  }

  /**
   * @return the duration
   */
  public int getDuration() {
    return duration;
  }

}
