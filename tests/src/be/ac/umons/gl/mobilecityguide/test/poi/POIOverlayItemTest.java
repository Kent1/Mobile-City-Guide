package be.ac.umons.gl.mobilecityguide.test.poi;

import android.test.AndroidTestCase;
import be.ac.umons.gl.mobilecityguide.db.POIDB;
import be.ac.umons.gl.mobilecityguide.poi.POI;
import be.ac.umons.gl.mobilecityguide.poi.POIOverlayItem;

import com.google.android.maps.GeoPoint;

/**
 * @author Quentin Loos
 */
public class POIOverlayItemTest extends AndroidTestCase {
  POIOverlayItem poiOverlayItem;
  POIDB poiDB = new POIDB();

  /*
   * (non-Javadoc)
   * 
   * @see android.test.AndroidTestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    poiOverlayItem = new POIOverlayItem(new GeoPoint(1, 2), "", "");
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
   * {@link be.ac.umons.gl.mobilecityguide.poi.POIOverlayItem#setPoi(be.ac.umons.gl.mobilecityguide.poi.POI)}
   * .
   */
  public final void testSetPoi() {
    poiOverlayItem.setPoi(new POI(10, 3, 9));
    assertEquals(poiOverlayItem.getPoi(), new POI(10, 3, 9));
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.poi.POIOverlayItem#getName()}.
   */
  public final void testGetName() {
    poiOverlayItem.setPoi(poiDB.getPOI(1));
    assertEquals(poiOverlayItem.getName(), poiDB.getPOI(1).getName());
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.poi.POIOverlayItem#getPoi()}.
   */
  public final void testGetPoi() {
    poiOverlayItem.setPoi(poiDB.getPOI(2));
    assertEquals(poiOverlayItem.getPoi(), poiDB.getPOI(2));
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.poi.POIOverlayItem#getPoint()}.
   */
  public final void testGetPoint() {
    assertEquals(poiOverlayItem.getPoint().getLatitudeE6(), 1);
    assertEquals(poiOverlayItem.getPoint().getLongitudeE6(), 2);
  }

}
