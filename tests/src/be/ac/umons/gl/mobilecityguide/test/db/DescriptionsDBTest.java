package be.ac.umons.gl.mobilecityguide.test.db;

import android.test.AndroidTestCase;
import be.ac.umons.gl.mobilecityguide.db.DescriptionsDB;

/**
 * @author Quentin Loos
 */
public class DescriptionsDBTest extends AndroidTestCase {
  DescriptionsDB db;

  /*
   * (non-Javadoc)
   * 
   * @see android.test.AndroidTestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    db = new DescriptionsDB();
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
   * {@link be.ac.umons.gl.mobilecityguide.db.DescriptionsDB#getDescription(int)}
   * .
   */
  public final void testGetDescription() {
    String description = "Le pentagone est un ";
    String description2 = db.getDescription(1);
    assertTrue(description2.contains(description));
  }
}
