package be.ac.umons.gl.mobilecityguide.test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import com.google.android.maps.GeoPoint;
import be.ac.umons.gl.mobilecityguide.poi.POI;


/**
 * @author Allard Hugo
 */
public class POITest{

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#POI()}.
   */
  @Test
  public void testPOI(){

    POI poi = new POI();
    assertNotNull(poi.getTags());
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#getId()}.
   */
  @Test
  public void testGetId(){

    POI poi = new POI();
    poi.setId(1);
    assertEquals(poi.getId(), 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#setId(int)}.
   */
  @Test
  public void testSetId(){

    POI poi = new POI();
    poi.setId(1);
    assertEquals(poi.getId(), 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#getDuration()}.
   */
  @Test
  public void testGetDuration(){

    POI poi = new POI();
    poi.setDuration(1);
    assertEquals(poi.getDuration(), 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#setDuration(int)}.
   */
  @Test
  public void testSetDuration(){

    POI poi = new POI();
    poi.setDuration(1);
    assertEquals(poi.getDuration(), 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#getVotes()}.
   */
  @Test
  public void testGetVotes(){

    POI poi = new POI();
    poi.setVotes(1);
    assertEquals(poi.getVotes(), 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#setVotes(int)}.
   */
  @Test
  public void testSetVotes(){

    POI poi = new POI();
    poi.setVotes(1);
    assertEquals(poi.getVotes(), 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#getPrice()}.
   */
  @Test
  public void testGetPrice(){

    POI poi = new POI();
    poi.setPrice(1);
    assertTrue(poi.getPrice() == 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#setPrice(double)}.
   */
  @Test
  public void testSetPrice(){

    POI poi = new POI();
    poi.setPrice(1);
    assertTrue(poi.getPrice() == 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#getRank()}.
   */
  @Test
  public void testGetRank(){

    POI poi = new POI();
    poi.setRank(1);
    assertTrue(poi.getRank() == 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#setRank(double)}.
   */
  @Test
  public void testSetRank(){

    POI poi = new POI();
    poi.setRank(1);
    assertTrue(poi.getRank() == 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#getMyRank()}.
   */
  @Test
  public void testGetMyRank(){

    POI poi = new POI();
    poi.setMyRank(1);
    assertTrue(poi.getMyRank() == 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#setMyRank(double)}.
   */
  @Test
  public void testSetMyRank(){

    POI poi = new POI();
    poi.setMyRank(1);
    assertTrue(poi.getMyRank() == 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#getName()}.
   */
  @Test
  public void testGetName(){

    POI poi = new POI();
    poi.setName("test");
    assertEquals(poi.getName(), "test");
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#setName(java.lang.String)}.
   */
  @Test
  public void testSetName(){

    POI poi = new POI();
    poi.setName("test");
    assertEquals(poi.getName(), "test");
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#getDescription()}.
   */
  @Test
  public void testGetDescription(){

    POI poi = new POI();
    poi.setDescription("test");
    assertEquals(poi.getDescription(), "test");
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#setDescription(java.lang.String)}.
   */
  @Test
  public void testSetDescription(){

    POI poi = new POI();
    poi.setDescription("test");
    assertEquals(poi.getDescription(), "test");
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#getAddress()}.
   */
  @Test
  public void testGetAddress(){

    POI poi = new POI();
    poi.setAddress("test");
    assertEquals(poi.getAddress(), "test");
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#setAddress(java.lang.String)}.
   */
  @Test
  public void testSetAddress(){

    POI poi = new POI();
    poi.setAddress("test");
    assertEquals(poi.getAddress(), "test");
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#getLocation()}.
   */
  @Test
  public void testGetLocation(){

    POI poi = new POI();
    poi.setLongitude(5);
    poi.setLatitude(5);
    assertEquals(poi.getLocation(), new GeoPoint((int) (5 * 1E6),(int) (5 * 1E6)));
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#hasTag(java.lang.String)}.
   */
  @Test
  public void testHasTag(){

    POI poi = new POI();
    ArrayList<String> list = new ArrayList<String>();
    list.add("test");
    poi.setTags(list);
    assertTrue(poi.hasTag("test"));
  }
  
  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#addTag(java.lang.String)}.
   */
  @Test
  public void testAddTag(String tag){
    
    POI poi = new POI();
    poi.addTag("test");
    assertTrue(poi.hasTag("test"));
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#getLatitude()}.
   */
  @Test
  public void testGetLatitude(){

    POI poi = new POI();
    poi.setLatitude(1);
    assertTrue(poi.getLatitude() == 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#setLatitude(double)}.
   */
  @Test
  public void testSetLatitude(){

    POI poi = new POI();
    poi.setLatitude(1);
    assertTrue(poi.getLatitude() == 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#getLongitude()}.
   */
  @Test
  public void testGetLongitude(){

    POI poi = new POI();
    poi.setLongitude(1);
    assertTrue(poi.getLongitude() == 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#setLongitude(double)}.
   */
  @Test
  public void testSetLongitude(){

    POI poi = new POI();
    poi.setLongitude(1);
    assertTrue(poi.getLongitude() == 1);
  }

  /**
   * Test method for {@link be.ac.umons.gl.mobilecityguide.poi.POI#equals(java.lang.Object)}.
   */
  @Test
  public void testEqualsObject(){

    POI poi = new POI();
    POI poi2 = new POI();
    poi.setId(1);
    poi2.setId(1);
    assertTrue(poi.equals(poi2));
  }
}