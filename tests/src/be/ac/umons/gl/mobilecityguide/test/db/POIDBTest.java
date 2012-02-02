/**
 * 
 */
package be.ac.umons.gl.mobilecityguide.test.db;

import be.ac.umons.gl.mobilecityguide.db.POIDB;
import be.ac.umons.gl.mobilecityguide.poi.POI;
import android.test.AndroidTestCase;

/**
 * Test Class for POIDB
 * 
 * @author Quentin Loos
 */
public class POIDBTest extends AndroidTestCase {
  POIDB db;
  POI poi;
  
  /* (non-Javadoc)
   * @see android.test.AndroidTestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    db = new POIDB();
    poi = null;
  }

  /* (non-Javadoc)
   * @see android.test.AndroidTestCase#tearDown()
   */
  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.db.POIDB#getPOI(int)}.
   */
  public final void testGetPOIInt() {
    poi = db.getPOI(1);
    assertTrue(poi != null);
    assertTrue(poi.getId()==1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.db.POIDB#getPOI(java.lang.String)}.
   */
  public final void testGetPOIString() {
    poi = db.getPOI("Pentagone");
    assertTrue(poi != null);
    assertTrue(poi.getId()==1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.db.POIDB#getPOI(int, int, int)}.
   */
  public final void testGetPOIIntIntInt() {
    fail("Not yet implemented"); // TODO
  }

}
