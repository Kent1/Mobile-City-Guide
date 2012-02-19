package be.ac.umons.gl.mobilecityguide.test.poi;

import android.graphics.drawable.Drawable;
import android.test.AndroidTestCase;
import be.ac.umons.gl.mobilecityguide.R;
import be.ac.umons.gl.mobilecityguide.poi.POI;
import be.ac.umons.gl.mobilecityguide.poi.POIItemizedOverlay;
import be.ac.umons.gl.mobilecityguide.poi.POIOverlayItem;

/**
 * @author Quentin Loos
 */
public class POIItemizedOverlayTest extends AndroidTestCase {
  POIItemizedOverlay p;
  private Drawable marker;

  /*
   * (non-Javadoc)
   * 
   * @see android.test.AndroidTestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    marker = mContext.getResources().getDrawable(R.drawable.map_marker);
    p = new POIItemizedOverlay(marker, mContext);
  }

  /*
   * (non-Javadoc)
   * 
   * @see android.test.AndroidTestCase#tearDown()
   */
  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.poi.POIItemizedOverlay#size()}.
   */
  public final void testSize() {
    assertEquals(p.size(), 0);
    p.addOverlay(new POIOverlayItem(new POI(1, 0, 0), "", ""));
    assertEquals(p.size(), 1);
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.poi.POIItemizedOverlay#addOverlay(be.ac.umons.gl.mobilecityguide.poi.POIOverlayItem)}
   * .
   */
  public final void testAddOverlay() {
    p.addOverlay(new POIOverlayItem(new POI(1, 1, 2), "", ""));
    assertEquals(p.getItem(0).getPoi(), new POIOverlayItem(new POI(1, 1, 2),
        "", "").getPoi());
  }
}
