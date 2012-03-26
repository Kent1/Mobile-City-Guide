package be.ac.umons.gl.mobilecityguide.poi;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import be.ac.umons.gl.mobilecityguide.map.MainActivity;

import com.google.android.maps.ItemizedOverlay;

/**
 * @author Allard Hugo
 */
public class POIItemizedOverlay extends ItemizedOverlay<POIOverlayItem> {

  /** Contains all the <code>POIOverlayItem</code>. */
  private final ArrayList<POIOverlayItem> mOverlays;

  /** The <code>Context</code> of the application. */
  private final Context context;

  /**
   * Constructs a <code>POIItemizedOverlay</code> with the specified marker.
   * 
   * @param defaultMarker
   *          the default marker to draw.
   * @param context
   *          the <code>Context</code> of the application.
   */
  public POIItemizedOverlay(Drawable defaultMarker, Context context) {

    super(boundCenterBottom(defaultMarker));
    this.context = context;
    mOverlays = new ArrayList<POIOverlayItem>();
  }

  /**
   * Adds an item to this <code>POIItemizedOverlay</code>.
   * 
   * @param item
   *          the new POIOverlayItem to add.
   */
  public void addOverlay(POIOverlayItem item) {

    mOverlays.add(item);
    setLastFocusedIndex(-1);
    populate();
  }

  @Override
  protected POIOverlayItem createItem(int i) {

    return mOverlays.get(i);
  }

  @Override
  public int size() {

    return mOverlays.size();
  }

  /**
   * Remove all POIOverlayItem
   */
  public void clear() {
    mOverlays.clear();
    setLastFocusedIndex(-1);
    populate();
  }

  @Override
  protected boolean onTap(int index) {

    MainActivity map = (MainActivity) context;
    map.displayPOI(mOverlays.get(index).getPoi());

    return true;
  }
}