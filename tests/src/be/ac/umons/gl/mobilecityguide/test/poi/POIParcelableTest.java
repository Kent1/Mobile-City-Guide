package be.ac.umons.gl.mobilecityguide.test.poi;

import android.os.Parcel;
import android.test.AndroidTestCase;
import be.ac.umons.gl.mobilecityguide.poi.POI;
import be.ac.umons.gl.mobilecityguide.poi.POIParcelable;

/**
 * Test Class for POIParcelable
 * 
 * @author Quentin Loos
 */
public class POIParcelableTest extends AndroidTestCase {
  POI poi;
  POIParcelable poip;

  /*
   * (non-Javadoc)
   * 
   * @see android.test.AndroidTestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    poi = new POI(1);
    poip = new POIParcelable(poi);
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
   * {@link be.ac.umons.gl.mobilecityguide.poi.POIParcelable#getPOI()}.
   */
  public void testGetPOI() {
    assertEquals(poi, poip.getPoi());
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.poi.POIParcelable#setPOI()}.
   */
  public void testSetPOI() {
    POI poi2 = new POI(2);
    poip.setPoi(poi2);
    assertFalse(poi.equals(poi2));
    assertEquals(poi2, poip.getPoi());
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.poi.POIParcelable#writeToParcel()}.
   */
  public void testWriteToParcel() {
    Parcel dest = Parcel.obtain();
    poip.writeToParcel(dest, 0);
    dest.setDataPosition(0);
    POIParcelable poip2 = POIParcelable.CREATOR.createFromParcel(dest);
    assertEquals(poi, poip2.getPoi());
    dest.setDataPosition(0);
    POIParcelable poip3 = new POIParcelable(dest);
    assertEquals(poi, poip3.getPoi());
  }
}