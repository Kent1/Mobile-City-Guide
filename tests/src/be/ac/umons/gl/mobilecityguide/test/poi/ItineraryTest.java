/**
 * 
 */
package be.ac.umons.gl.mobilecityguide.test.poi;

import java.util.ArrayList;

import android.test.AndroidTestCase;
import be.ac.umons.gl.mobilecityguide.poi.Itinerary;
import be.ac.umons.gl.mobilecityguide.poi.POI;

/**
 * @author kent
 * 
 */
public class ItineraryTest extends AndroidTestCase {
  Itinerary itinerary;

  /*
   * (non-Javadoc)
   * 
   * @see android.test.AndroidTestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    itinerary = new Itinerary();
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
   * {@link be.ac.umons.gl.mobilecityguide.poi.Itinerary#optimize()}.
   */
  public final void testOptimize() {
    // TODO
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.poi.Itinerary#add(be.ac.umons.gl.mobilecityguide.poi.POI)}
   * .
   */
  public final void testAdd() {
    itinerary.add(new POI());
    assertEquals(itinerary.size(), 1);
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.poi.Itinerary#clear()}.
   */
  public final void testClear() {
    itinerary.add(new POI());
    itinerary.clear();
    assertEquals(itinerary.size(), 0);
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.poi.Itinerary#contains(be.ac.umons.gl.mobilecityguide.poi.POI)}
   * .
   */
  public final void testContains() {
    itinerary.add(new POI(1));
    assertTrue(itinerary.contains(new POI(1)));
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.poi.Itinerary#remove(be.ac.umons.gl.mobilecityguide.poi.POI)}
   * .
   */
  public final void testRemove() {
    itinerary.add(new POI(1));
    itinerary.remove(new POI(1));
    assertTrue(!itinerary.contains(new POI(1)));
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.Itinerary#size()}
   * .
   */
  public final void testSize() {
    itinerary.add(new POI(1));
    itinerary.add(new POI(2));
    assertEquals(itinerary.size(), 2);
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.poi.Itinerary#setList(java.util.List)}
   * .
   */
  public final void testSetList() {
    ArrayList<POI> list = new ArrayList<POI>();
    list.add(new POI(1));
    itinerary.setList(list);
    assertEquals(itinerary.size(), 1);
    assertTrue(itinerary.contains(new POI(1)));
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.poi.Itinerary#getList()}.
   */
  public final void testGetList() {
    ArrayList<POI> list = new ArrayList<POI>();
    list.add(new POI(1));
    itinerary.setList(list);
    assertEquals(itinerary.getList(), list);
  }
}
