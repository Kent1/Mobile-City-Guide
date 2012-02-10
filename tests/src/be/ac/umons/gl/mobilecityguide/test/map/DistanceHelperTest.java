package be.ac.umons.gl.mobilecityguide.test.map;

import android.test.AndroidTestCase;
import be.ac.umons.gl.mobilecityguide.map.DistanceHelper;

/**
 * @author kent
 * 
 */
public class DistanceHelperTest extends AndroidTestCase {

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.map.DistanceHelper#distance(double, double, double, double)}
   * .
   */
  public final void testDistance() {
    // Données calculées sur internet..
    assertEquals(DistanceHelper.distance(0, 0, 1, 0), 111.195, 0.1);
    assertEquals(DistanceHelper.distance(0, 0, 0, 1), 111.195, 0.1);
    assertEquals(DistanceHelper.distance(0, 0, 87, 46), 9775.871, 0.1);
    assertEquals(DistanceHelper.distance(34, 92, -87, -46), 14033.311, 0.1);
  }
}
