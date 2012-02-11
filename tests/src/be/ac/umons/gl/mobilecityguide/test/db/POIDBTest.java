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
    db = new POIDB();
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
   * {@link be.ac.umons.gl.mobilecityguide.db.POIDB#getDescription(int)}.
   */
  public final void testGetDescription() {
    String description = db.getDescription(1);
    assertTrue(description
        .contains("The pentagone is a building belonging to university of Mons."));
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.db.POIDB#getPOI(double, double, double, double)}
   * .
   */
  public final void testGetPOIDoubleDoubleDoubleDouble() {
    ArrayList<POI> poi = db.getPOI(50.463, 3.9551, 1);
    assertTrue(!poi.isEmpty());
  }

}
