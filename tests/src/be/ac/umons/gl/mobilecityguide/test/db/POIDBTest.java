/**
 * 
 */
package be.ac.umons.gl.mobilecityguide.test.db;

import java.util.ArrayList;

import android.test.AndroidTestCase;
import be.ac.umons.gl.mobilecityguide.db.POIDB;
import be.ac.umons.gl.mobilecityguide.poi.POI;

/**
 * Test class for POIDB
 * 
 * @author Quentin Loos
 */
public class POIDBTest extends AndroidTestCase {
  POIDB db;
  POI poi;

  /*
   * (non-Javadoc)
   * 
   * @see android.test.AndroidTestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    db = new POIDB(this.mContext);
    db.retrievePOIList(50.463, 3.9551, 20);
    poi = null;
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
   * {@link be.ac.umons.gl.mobilecityguide.db.POIDB#retrievePOIList(double, double, int)}
   * .
   */
  public final void testRetrievePOIList() {
    ArrayList<POI> list = db.retrievePOIList(50.463, 3.9551, 20);
    assertTrue(!list.isEmpty());
    assertEquals(list.get(0).getName(), "Pentagone");
    assertEquals(list.get(1).getName(), "Warocqu\u00e9");
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.db.POIDB#getPOI(int)}
   * .
   */
  public final void testGetPOIInt() {
    poi = db.getPOI(1);
    assertTrue(poi != null);
    assertTrue(poi.getId() == 1);
    assertTrue(poi.getTag().equals("education"));
    assertTrue(poi.getDuration() == 30);
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.db.POIDB#getPOI(java.lang.String)}.
   */
  public final void testGetPOIString() {
    poi = db.getPOI("Pentagone");
    assertTrue(poi != null);
    assertTrue(poi.getId() == 1);
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.db.POIDB#getPOIList()} .
   */
  public final void testGetPOIList() {
    ArrayList<POI> poi = db.getPOIList();
    assertTrue(!poi.isEmpty());
    assertEquals(poi.get(0).getName(), "Pentagone");
    assertEquals(db.getPOI(2).getName(), "Warocqu\u00e9");
  }

}
