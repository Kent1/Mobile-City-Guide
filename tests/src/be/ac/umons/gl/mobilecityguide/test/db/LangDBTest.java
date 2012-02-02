/**
 * 
 */
package be.ac.umons.gl.mobilecityguide.test.db;

import java.util.ArrayList;

import be.ac.umons.gl.mobilecityguide.db.LangDB;
import android.test.AndroidTestCase;

/**
 * Test class for LangDB
 * 
 * @author Quentin Loos
 */
public class LangDBTest extends AndroidTestCase {
  LangDB db;
  /* (non-Javadoc)
   * @see android.test.AndroidTestCase#setUp()
   */
  protected void setUp() throws Exception {
    super.setUp();
    db = new LangDB();
  }

  /* (non-Javadoc)
   * @see android.test.AndroidTestCase#tearDown()
   */
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.db.LangDB#getLangList()}.
   */
  public final void testGetLangList() {
    ArrayList<String> array = db.getLangList();
    assertEquals(array.get(0), "EN");
    assertEquals(array.get(1), "FR");
    assertEquals(array.get(2), "NL");
  }

}
