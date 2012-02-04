package be.ac.umons.gl.mobilecityguide.poi;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

/**
 * @author Hugo Allard
 */
public class POIItemizedOverlay extends ItemizedOverlay<OverlayItem> {

  /** Contains all the <code>POIOverlayItem</code> */
  private final ArrayList<POIOverlayItem> mOverlays = new ArrayList<POIOverlayItem>();

  /** The context of the application */
  private final Context context;

  /**
   * Constructs a <code>POIItemizedOverlay</code> with the specified marker.
   * 
   * @param defaultMarker
   *          the default marker to draw.
   */
  public POIItemizedOverlay(Drawable defaultMarker, Context context) {

    super(boundCenterBottom(defaultMarker));
    this.context = context;
  }

  /**
   * Adds an item to this <code>POIItemizedOverlay</code>.
   * 
   * @param overlay
   *          the new item to add.
   */
  public void addOverlay(POIOverlayItem item) {

    mOverlays.add(item);
    populate();
  }

  @Override
  protected OverlayItem createItem(int i) {

    return mOverlays.get(i);
  }

  @Override
  public int size() {

    return mOverlays.size();
  }

  /*
   * Maybe a different way to draw the marker.
   * 
   * @Override public void draw(Canvas canvas, MapView mapView, boolean shadow){
   * 
   * if(!shadow){
   * 
   * for(int i = 0; i < mOverlays.size(); i++){
   * 
   * POIOverlayItem item = mOverlays.get(i);
   * 
   * Point point = mapView.getProjection().toPixels(item.getPoint(), null);
   * 
   * Paint paint = new Paint(); paint.setTextAlign(Paint.Align.CENTER);
   * paint.setARGB(150, 0, 0, 0);
   * 
   * canvas.drawText(item.getName(), point.x, point.y, paint); } } }
   */

  @Override
  protected boolean onTap(int index) {

    // TODO

    /*
     * Intent intent = new Intent(context, POIDisplayActivity.class);
     * intent.putExtra("poi", new POIParcelable(mOverlays.get(index).getPoi()));
     * context.startActivity(intent);
     */

    return true;
  }
}