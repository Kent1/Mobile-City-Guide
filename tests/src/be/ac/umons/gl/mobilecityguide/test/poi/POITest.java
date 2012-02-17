package be.ac.umons.gl.mobilecityguide.test.poi;

import android.test.AndroidTestCase;
import be.ac.umons.gl.mobilecityguide.poi.POI;

/**
 * @author Allard Hugo
 */
public class POITest extends AndroidTestCase {

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#getId()}.
   */
  public void testGetId() {
    POI poi = new POI(1, 1, 1);
    poi.setId(1);
    assertEquals(poi.getId(), 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#setId(int)}.
   */
  public void testSetId() {
    POI poi = new POI(1, 1, 1);
    poi.setId(1);
    assertEquals(poi.getId(), 1);
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.poi.POI#getDuration()}.
   */
  public void testGetDuration() {
    POI poi = new POI(1, 1, 1);
    poi.setDuration(1);
    assertEquals(poi.getDuration(), 1);
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.poi.POI#setDuration(int)}.
   */
  public void testSetDuration() {
    POI poi = new POI(1, 1, 1);
    poi.setDuration(1);
    assertEquals(poi.getDuration(), 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#getVotes()}.
   */
  public void testGetVotes() {
    POI poi = new POI(1, 1, 1);
    poi.setVotes(1);
    assertEquals(poi.getVotes(), 1);
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.poi.POI#setVotes(int)}.
   */
  public void testSetVotes() {
    POI poi = new POI(1, 1, 1);
    poi.setVotes(1);
    assertEquals(poi.getVotes(), 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#getPrice()}.
   */
  public void testGetPrice() {
    POI poi = new POI(1, 1, 1);
    poi.setPrice(1);
    assertTrue(poi.getPrice() == 1);
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.poi.POI#setPrice(double)}.
   */
  public void testSetPrice() {
    POI poi = new POI(1, 1, 1);
    poi.setPrice(1);
    assertTrue(poi.getPrice() == 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#getRank()}.
   */
  public void testGetRank() {
    POI poi = new POI(1, 1, 1);
    poi.setRank(1);
    assertTrue(poi.getRank() == 1);
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.poi.POI#setRank(double)}.
   */
  public void testSetRank() {
    POI poi = new POI(1, 1, 1);
    poi.setRank(1);
    assertTrue(poi.getRank() == 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#getName()}.
   */
  public void testGetName() {
    POI poi = new POI(1, 1, 1);
    poi.setName("test");
    assertEquals(poi.getName(), "test");
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.poi.POI#setName(java.lang.String)}.
   */
  public void testSetName() {
    POI poi = new POI(1, 1, 1);
    poi.setName("test");
    assertEquals(poi.getName(), "test");
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.poi.POI#getDescription()}.
   */
  public void testGetDescription() {
    POI poi = new POI(1, 1, 1);
    poi.setDescription("test");
    assertEquals(poi.getDescription(), "test");
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.poi.POI#setDescription(java.lang.String)}
   * .
   */
  public void testSetDescription() {
    POI poi = new POI(1, 1, 1);
    poi.setDescription("test");
    assertEquals(poi.getDescription(), "test");
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#getAddress()}
   * .
   */
  public void testGetAddress() {
    POI poi = new POI(1, 1, 1);
    poi.setAddress("test");
    assertEquals(poi.getAddress(), "test");
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.poi.POI#setAddress(java.lang.String)}
   * .
   */
  public void testSetAddress() {
    POI poi = new POI(1, 1, 1);
    poi.setAddress("test");
    assertEquals(poi.getAddress(), "test");
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.poi.POI#getLatitude()}.
   */
  public void testGetLatitude() {
    POI poi = new POI(1, 1, 1);
    assertTrue(poi.getLatitude() == 1);
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.poi.POI#getLongitude()}.
   */
  public void testGetLongitude() {
    POI poi = new POI(1, 1, 1);
    assertTrue(poi.getLongitude() == 1);
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.poi.POI#equals(java.lang.Object)}.
   */
  public void testEqualsObject() {
    POI poi = new POI(1, 1, 1);
    POI poi2 = new POI(1, 1, 1);
    assertTrue(poi.equals(poi2));
  }
}