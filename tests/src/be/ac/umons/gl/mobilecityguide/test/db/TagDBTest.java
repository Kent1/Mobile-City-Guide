package be.ac.umons.gl.mobilecityguide.test.db;

import java.util.ArrayList;

import android.test.AndroidTestCase;
import be.ac.umons.gl.mobilecityguide.db.TagDB;

/**
 * @author Quentin Loos
 */
public class TagDBTest extends AndroidTestCase {
  TagDB db;

  /*
   * (non-Javadoc)
   * 
   * @see junit.framework.TestCase#setUp()
   */
  protected void setUp() throws Exception {
    super.setUp();
    db = new TagDB(this.mContext);
  }

  /*
   * (non-Javadoc)
   * 
   * @see junit.framework.TestCase#tearDown()
   */
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.db.TagDB#getTagList()}.
   */
  public final void testGetTagList() {
    ArrayList<String> tags = db.getTagList();
    assertTrue(tags.size() > 0);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.db.TagDB#getTag()}.
   */
  public final void testGetTag() {
    String tag = db.getTag(1);
    assertEquals(tag, "education");
  }
}
