package be.ac.umons.gl.mobilecityguide.test.db;

import android.test.AndroidTestCase;
import be.ac.umons.gl.mobilecityguide.db.RankingDB;

/**
 * @author Quentin Loos
 */
public class RankingDBTest extends AndroidTestCase {
  RankingDB db;

  /*
   * (non-Javadoc)
   * 
   * @see android.test.AndroidTestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    db = new RankingDB(this.mContext);
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
   * {@link be.ac.umons.gl.mobilecityguide.db.RankingDB#getRank(int)}.
   */
  public final void testGetRank() {
    double rank = db.getRank(1);
    assertTrue(rank >= 0);
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.db.RankingDB#getNBVote(int)}.
   */
  public final void testGetNBVote() {
    int nb = db.getNBVote(1);
    assertTrue(nb >= 0);
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.db.RankingDB#getMyRank(int)}.
   */
  public final void testGetMyRank() {
    double nb = db.getMyRank(1);
    assertTrue(nb >= 0);
    assertTrue(nb <= 5);
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.db.RankingDB#rank(int, double, double)}
   */
  public final void testRank() {
    db.rank(1, 1, 0);
    db.rank(1, 5, 1);
    assertTrue(true);
  }
}
