package be.ac.umons.gl.mobilecityguide.poi;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class POIOverlayItem extends OverlayItem {

  /** The <code>POI</code> represented by this <code>POIOverlayItem</code>. */
  private final POI poi;

  /**
   * Constructs a new instance of <code>POIOverlayItem</code>.
   * 
   * @param poi
   *          the poi of this <code>POIOverlayItem</code>.
   * @param title
   *          the title of this <code>POIOverlayItem</code>.
   * @param snippet
   *          the snippet of this <code>POIOverlayItem</code>.
   */
  public POIOverlayItem(POI poi, String title, String snippet) {
    super(poi, title, snippet);
    this.poi = poi;
  }

  /**
   * Gets the name of this <code>POIOverlayItem</code>.
   * 
   * @return the name of the <code>POI</code>
   */
  public String getName() {

    return poi.getName();
  }

  /**
   * Gets the <code>POI</code> represented by this <code>POIOverlayItem</code>.
   * 
   * @return the <code>POI</code>.
   */
  public POI getPoi() {

    return poi;
  }

  @Override
  public GeoPoint getPoint() {

    return super.getPoint();
  }
}
