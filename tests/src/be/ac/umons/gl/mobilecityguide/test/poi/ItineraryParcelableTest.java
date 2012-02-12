package be.ac.umons.gl.mobilecityguide.test.poi;

import android.os.Parcel;
import android.test.AndroidTestCase;
import be.ac.umons.gl.mobilecityguide.poi.Itinerary;
import be.ac.umons.gl.mobilecityguide.poi.ItineraryParcelable;

/**
 * @author Quentin Loos
 */
public class ItineraryParcelableTest extends AndroidTestCase {
  Itinerary itinerary;
  ItineraryParcelable itineraryP;

  /*
   * (non-Javadoc)
   * 
   * @see android.test.AndroidTestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    itinerary = new Itinerary();
    itineraryP = new ItineraryParcelable(itinerary);
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
   * {@link be.ac.umons.gl.mobilecityguide.poi.ItineraryParcelable#ItineraryParcelable(android.os.Parcel)}
   * .
   */
  public final void testItineraryParcelableParcel() {
    fail("Not yet implemented"); // TODO
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.poi.ItineraryParcelable#getItinerary()}
   * .
   */
  public final void testGetItinerary() {
    assertEquals(itineraryP.getItinerary(), itinerary);
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.poi.ItineraryParcelable#setItinerary(be.ac.umons.gl.mobilecityguide.poi.Itinerary)}
   * .
   */
  public final void testSetItinerary() {
    Itinerary itinerary2 = new Itinerary();
    itineraryP.setItinerary(itinerary2);
    assertFalse(itinerary.equals(itinerary2));
    assertEquals(itinerary2, itineraryP.getItinerary());
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.poi.ItineraryParcelable#writeToParcel(android.os.Parcel, int)}
   * .
   */
  public final void testWriteToParcel() {
    Parcel dest = Parcel.obtain();
    itineraryP.writeToParcel(dest, 0);
    dest.setDataPosition(0);
    ItineraryParcelable itineraryP2 = ItineraryParcelable.CREATOR
        .createFromParcel(dest);
    assertEquals(itinerary.getList(), itineraryP2.getItinerary().getList());
    dest.setDataPosition(0);
    ItineraryParcelable itineraryP3 = new ItineraryParcelable(dest);
    assertEquals(itinerary.getList(), itineraryP3.getItinerary().getList());
  }
}
