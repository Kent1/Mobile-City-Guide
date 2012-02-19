package be.ac.umons.gl.mobilecityguide.test.poi;

import android.test.AndroidTestCase;
import be.ac.umons.gl.mobilecityguide.db.POIDB;
import be.ac.umons.gl.mobilecityguide.poi.POIOverlayItem;

/**
 * @author Quentin Loos
 */
public class POIOverlayItemTest extends AndroidTestCase {
  POIOverlayItem poiOverlayItem;
  POIDB poiDB;

  /*
   * (non-Javadoc)
   * 
   * @see android.test.AndroidTestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    poiDB = new POIDB(this.mContext);
    poiOverlayItem = new POIOverlayItem(poiDB.getPOI(1), "", "");
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
   * {@link be.ac.umons.gl.mobilecityguide.poi.POIOverlayItem#getName()}.
   */
  public final void testGetName() {
    assertEquals(poiOverlayItem.getName(), poiDB.getPOI(1).getName());
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.poi.POIOverlayItem#getPoi()}.
   */
  public final void testGetPoi() {
    assertEquals(poiOverlayItem.getPoi(), poiDB.getPOI(1));
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.poi.POIOverlayItem#getPoint()}.
   */
  public final void testGetPoint() {
    assertEquals(poiOverlayItem.getPoint().getLatitudeE6(), poiDB.getPOI(1)
        .getLatitudeE6());
    assertEquals(poiOverlayItem.getPoint().getLongitudeE6(), poiDB.getPOI(1)
        .getLongitudeE6());
  }

}
