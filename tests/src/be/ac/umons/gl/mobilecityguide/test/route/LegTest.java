package be.ac.umons.gl.mobilecityguide.test.route;

import android.test.AndroidTestCase;
import be.ac.umons.gl.mobilecityguide.db.POIDB;
import be.ac.umons.gl.mobilecityguide.route.Leg;

/**
 * @author Quentin Loos
 */
public class LegTest extends AndroidTestCase {
  Leg leg;
  POIDB db;

  /*
   * (non-Javadoc)
   * 
   * @see android.test.AndroidTestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    db = new POIDB(this.mContext);
    leg = new Leg(db.getPOI(1), db.getPOI(2), 123, 456);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.route.Leg#getFrom()}.
   */
  public final void testGetFrom() {
    assertEquals(leg.getFrom(), db.getPOI(1));
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.route.Leg#getTo()}.
   */
  public final void testGetTo() {
    assertEquals(leg.getTo(), db.getPOI(2));
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.route.Leg#addStep(be.ac.umons.gl.mobilecityguide.route.Step)}
   * .
   */
  public final void testAddStep() {

  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.route.Leg#getDistance()}.
   */
  public final void testGetDistance() {
    assertEquals(leg.getDistance(), 123);
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.route.Leg#getDuration()}.
   */
  public final void testGetDuration() {
    assertEquals(leg.getDuration(), 456);
  }
}
