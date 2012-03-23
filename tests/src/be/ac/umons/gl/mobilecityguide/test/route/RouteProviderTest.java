package be.ac.umons.gl.mobilecityguide.test.route;

import android.test.AndroidTestCase;
import android.util.Log;
import be.ac.umons.gl.mobilecityguide.db.POIDB;
import be.ac.umons.gl.mobilecityguide.poi.Itinerary;
import be.ac.umons.gl.mobilecityguide.route.RouteProvider;

/**
 * @author Quentin Loos
 */
public class RouteProviderTest extends AndroidTestCase {
  POIDB db;
  Itinerary itinerary;
  RouteProvider route;

  /*
   * (non-Javadoc)
   * 
   * @see android.test.AndroidTestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    db = new POIDB(this.mContext);
    db.retrievePOIList(50.463, 3.9551, 20);
    itinerary = new Itinerary();
    itinerary.add(db.getPOI(3));
    route = new RouteProvider(db.getPOI(1), itinerary);
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
   * {@link be.ac.umons.gl.mobilecityguide.map.RouteProvider#getUrl()} .
   */
  public final void testGetUrl() {
    assertEquals(
        route.getUrl(),
        "http://maps.googleapis.com/maps/api/directions/json?&origin=50.4632,3.955117&destination=&waypoints=optimize:true%7C50.462785,3.953863&modewalking&sensor=true");
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.map.RouteProvider#getJSON(java.lang.String)}
   * .
   */
  public final void testGetJSON() {
    Log.e("coucou", route.getJSON(route.getUrl()).toString());
  }

  /**
   * Test method for
   * {@link be.ac.umons.gl.mobilecityguide.map.RouteProvider#routeFromJSON()} .
   */
  public final void testRouteFromJSON() {
    Log.e("coucou", "" + route.routeFromJSON(route.getJSON(route.getUrl())));
  }
}
