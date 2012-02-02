package be.ac.umons.gl.mobilecityguide.test.poi;

import be.ac.umons.gl.mobilecityguide.poi.POI;
import android.test.*;

/**
 * @author Allard Hugo
 */
public class POITest extends AndroidTestCase{

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#getId()}.
   */
  public void testGetId(){
    POI poi = new POI();
    poi.setId(1);
    assertEquals(poi.getId(), 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#setId(int)}.
   */
  public void testSetId(){
    POI poi = new POI();
    poi.setId(1);
    assertEquals(poi.getId(), 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#getDuration()}.
   */
  public void testGetDuration(){
    POI poi = new POI();
    poi.setDuration(1);
    assertEquals(poi.getDuration(), 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#setDuration(int)}.
   */
  public void testSetDuration(){
    POI poi = new POI();
    poi.setDuration(1);
    assertEquals(poi.getDuration(), 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#getVotes()}.
   */
  public void testGetVotes(){
    POI poi = new POI();
    poi.setVotes(1);
    assertEquals(poi.getVotes(), 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#setVotes(int)}.
   */
  public void testSetVotes(){
    POI poi = new POI();
    poi.setVotes(1);
    assertEquals(poi.getVotes(), 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#getPrice()}.
   */
  public void testGetPrice(){
    POI poi = new POI();
    poi.setPrice(1);
    assertTrue(poi.getPrice() == 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#setPrice(double)}.
   */
  public void testSetPrice(){
    POI poi = new POI();
    poi.setPrice(1);
    assertTrue(poi.getPrice() == 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#getRank()}.
   */
  public void testGetRank(){
    POI poi = new POI();
    poi.setRank(1);
    assertTrue(poi.getRank() == 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#setRank(double)}.
   */
  public void testSetRank(){
    POI poi = new POI();
    poi.setRank(1);
    assertTrue(poi.getRank() == 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#getName()}.
   */
  public void testGetName(){
    POI poi = new POI();
    poi.setName("test");
    assertEquals(poi.getName(), "test");
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#setName(java.lang.String)}.
   */
  public void testSetName(){
    POI poi = new POI();
    poi.setName("test");
    assertEquals(poi.getName(), "test");
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#getDescription()}.
   */
  public void testGetDescription(){
    POI poi = new POI();
    poi.setDescription("test");
    assertEquals(poi.getDescription(), "test");
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#setDescription(java.lang.String)}.
   */
  public void testSetDescription(){
    POI poi = new POI();
    poi.setDescription("test");
    assertEquals(poi.getDescription(), "test");
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#getAddress()}.
   */
  public void testGetAddress(){
    POI poi = new POI();
    poi.setAddress("test");
    assertEquals(poi.getAddress(), "test");
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#setAddress(java.lang.String)}.
   */
  public void testSetAddress(){
    POI poi = new POI();
    poi.setAddress("test");
    assertEquals(poi.getAddress(), "test");
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#getLatitude()}.
   */
  public void testGetLatitude(){
    POI poi = new POI();
    poi.setLatitude(1);
    assertTrue(poi.getLatitude() == 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#setLatitude(double)}.
   */
  public void testSetLatitude(){
    POI poi = new POI();
    poi.setLatitude(1);
    assertTrue(poi.getLatitude() == 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#getLongitude()}.
   */
  public void testGetLongitude(){
    POI poi = new POI();
    poi.setLongitude(1);
    assertTrue(poi.getLongitude() == 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#setLongitude(double)}.
   */
  public void testSetLongitude(){
    POI poi = new POI();
    poi.setLongitude(1);
    assertTrue(poi.getLongitude() == 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#equals(java.lang.Object)}.
   */
  public void testEqualsObject(){
    POI poi = new POI();
    POI poi2 = new POI();
    poi.setId(1);
    poi2.setId(1);
    assertTrue(poi.equals(poi2));
  }
}