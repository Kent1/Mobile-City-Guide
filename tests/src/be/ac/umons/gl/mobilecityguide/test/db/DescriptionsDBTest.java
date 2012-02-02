package be.ac.umons.gl.mobilecityguide.test.db;

import be.ac.umons.gl.mobilecityguide.db.DescriptionsDB;
import android.test.AndroidTestCase;

/**
 * @author Quentin Loos
 */
public class DescriptionsDBTest extends AndroidTestCase {
  DescriptionsDB db;
  
  /* (non-Javadoc)
   * @see android.test.AndroidTestCase#setUp()
   */
  protected void setUp() throws Exception {
    super.setUp();
    db = new DescriptionsDB();
  }

  /* (non-Javadoc)
   * @see android.test.AndroidTestCase#tearDown()
   */
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.db.DescriptionsDB#getDescription(int)}.
   */
  public final void testGetDescription() {
    String description="Le pentagone est un b\u00e2timent appartenant " +
    		"\u00e0 l'Universit\u00e9 de Mons. L'institut d'informatique y est " +
    		"localis\u00e9 et certains cours d'informatique y sont dispens\u00e9s";
    String description2 = db.getDescription(1);
    assertEquals(description,description2);
  }
}
