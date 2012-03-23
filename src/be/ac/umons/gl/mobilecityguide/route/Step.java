package be.ac.umons.gl.mobilecityguide.route;

import com.google.android.maps.GeoPoint;

public class Step {
  private final GeoPoint from, to;
  private final int distance, duration;
  private final String instruction;

  public Step(GeoPoint from, GeoPoint to, int distance, int duration,
      String instruction) {
    this.from = from;
    this.to = to;
    this.distance = distance;
    this.duration = duration;
    this.instruction = instruction;
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

  /**
   * @return the instruction
   */
  public String getInstruction() {
    return instruction;
  }
}
