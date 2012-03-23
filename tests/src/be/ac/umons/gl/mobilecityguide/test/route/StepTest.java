package be.ac.umons.gl.mobilecityguide.test.route;

import android.test.AndroidTestCase;
import be.ac.umons.gl.mobilecityguide.db.POIDB;
import be.ac.umons.gl.mobilecityguide.route.Step;

/**
 * @author Quentin Loos
 */
public class StepTest extends AndroidTestCase {
  Step step;
  POIDB db;

  /*
   * (non-Javadoc)
   * 
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    db = new POIDB(this.mContext);
    step = new Step(db.getPOI(1), db.getPOI(2), 976, 575, "Prenez a droite");
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.route.Step#getFrom()}
   * .
   */
  public final void testGetFrom() {
    assertEquals(step.getFrom(), db.getPOI(1));
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.route.Step#getTo()}.
   */
  public final void testGetTo() {
    assertEquals(step.getTo(), db.getPOI(2));
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.route.Step#getDistance()}.
   */
  public final void testGetDistance() {
    assertEquals(step.getDistance(), 976);
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.route.Step#getDuration()}.
   */
  public final void testGetDuration() {
    assertEquals(step.getDuration(), 575);
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.route.Step#getInstruction()}.
   */
  public final void testGetInstruction() {
    assertEquals(step.getInstruction(), "Prenez a droite");
  }

}
