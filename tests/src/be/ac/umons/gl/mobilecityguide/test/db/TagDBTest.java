/**
 * 
 */
package be.ac.umons.gl.mobilecityguide.test.db;

import java.util.ArrayList;

import android.test.AndroidTestCase;
import be.ac.umons.gl.mobilecityguide.db.TagDB;

/**
 * @author kent
 * 
 */
public class TagDBTest extends AndroidTestCase {
  TagDB db;

  /*
   * (non-Javadoc)
   * 
   * @see android.test.AndroidTestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    db = new TagDB(this.mContext);
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
   * {@link be.ac.umons.gl.mobilecityguide.db.TagDB#retrieveTagList()}.
   */
  public final void testRetrieveTagList() {
    db.retrieveTagList();
    assertTrue(!db.getTagListMyDB().isEmpty());
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.db.TagDB#getTagList()}.
   */
  public final void testGetTagList() {
    ArrayList<String> tags = db.getTagListMyDB();
    assertTrue(!tags.isEmpty());
    assertTrue(tags.get(0).equals("administration"));
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.db.TagDB#getTagListMyDB()}.
   */
  public final void testGetTagListMyDB() {
    ArrayList<String> list = db.getTagListMyDB();
    assertTrue(list.get(0).equals("administration"));
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.db.TagDB#isTagSelected(java.lang.String)}
   * .
   */
  public final void testIsTagSelected() {
    db.selectTag("education", true);
    assertTrue(db.isTagSelected("education"));
    db.selectTag("education", false);
    assertTrue(!db.isTagSelected("education"));
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.db.TagDB#selectTag(java.lang.String, boolean)}
   * .
   */
  public final void testSelectTag() {
    db.selectTag("administration", true);
    assertTrue(db.isTagSelected("administration"));
    db.selectTag("administration", false);
    assertTrue(!db.isTagSelected("administration"));
    db.selectTag("blabla", false);
    assertTrue(db.isTagSelected("blabla"));
  }

}
