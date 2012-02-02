package be.ac.umons.gl.mobilecityguide.test.db;

import be.ac.umons.gl.mobilecityguide.db.RankingDB;
import android.test.AndroidTestCase;

/**
 * @author Quentin Loos
 */
public class RankingDBTest extends AndroidTestCase {
  RankingDB db;
  
  /* (non-Javadoc)
   * @see android.test.AndroidTestCase#setUp()
   */
  protected void setUp() throws Exception {
    super.setUp();
    db = new RankingDB();
  }

  /* (non-Javadoc)
   * @see android.test.AndroidTestCase#tearDown()
   */
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.db.RankingDB#getRank(int)}.
   */
  public final void testGetRank() {
    double rank = db.getRank(1);
    assertTrue(rank>=0);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.db.RankingDB#getNBVote(int)}.
   */
  public final void testGetNBVote() {
    int nb = db.getNBVote(1);
    assertTrue(nb>=0);
  }
}
