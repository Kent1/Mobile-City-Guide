/**
 * 
 */
package be.ac.umons.gl.mobilecityguide.test.db;

import be.ac.umons.gl.mobilecityguide.db.InfosDB;
import android.test.AndroidTestCase;

/**
 * Test class for InfosDB
 * 
 * @author Quentin Loos
 */
public class InfosDBTest extends AndroidTestCase {
  InfosDB db;
  
  /* (non-Javadoc)
   * @see android.test.AndroidTestCase#setUp()
   */
  protected void setUp() throws Exception {
    super.setUp();
    db = new InfosDB();
  }

  /* (non-Javadoc)
   * @see android.test.AndroidTestCase#tearDown()
   */
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.db.InfosDB#getPrice(int)}.
   */
  public final void testGetPrice() {
    double price = db.getPrice(1);
    assertTrue(price==0);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.db.InfosDB#getDuration(int)}.
   */
  public final void testGetDuration() {
    int duration = db.getDuration(1);
    assertTrue(duration==30);
  }
}
