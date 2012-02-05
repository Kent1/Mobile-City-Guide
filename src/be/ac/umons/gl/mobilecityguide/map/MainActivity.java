package be.ac.umons.gl.mobilecityguide.map;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import be.ac.umons.gl.mobilecityguide.R;
import be.ac.umons.gl.mobilecityguide.db.POIDB;
import be.ac.umons.gl.mobilecityguide.poi.POI;
import be.ac.umons.gl.mobilecityguide.poi.POIItemizedOverlay;
import be.ac.umons.gl.mobilecityguide.poi.POIOverlayItem;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

public class MainActivity extends MapActivity implements LocationListener {

  private static final int RELOAD_RATE = 30;

  /** The <code>MapController</code> for this map. */
  private MapController mapController;

  /** The <code>MapView</code> for this map. */
  private MapView mapView;

  /** The <code>List</code> of every <code>POI</code>s around the user. */
  private List<POI> pois;

  /** The <code>List</code> with the non-wanted tags. */
  private List<String> filter;

  /** The <code>LocationManager</code> for GPS localization. */
  private LocationManager locationManager;

  /** The <code>Overlay</code> for user's location. */
  private MyLocationOverlay myLocation;

  /** The <code>POIDB</code> to fetch <code>POI</code>s from the database. */
  private POIDB poidb;

  /** The <code>POIItemizedOverlay</code> with the <code>POI</code>s to display. */
  private POIItemizedOverlay itemizedOverlay;

  /** The marker to indicates <code>POI</code>s one the map. */
  private Drawable marker;

  /** The <code>List</code> with the <code>Overlay</code>s of this map. */
  private List<Overlay> mapOverlays;

  /**
   * Used to reload the <code>POI</code>s every 6 call of
   * <code>onLocationChanged</code>.
   */
  private int iterator;

  @Override
  public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    filter = new ArrayList<String>();

    initMap();
    initGPS();
    mapView.invalidate();
  }

  @Override
  public void onResume() {
    super.onResume();
    myLocation.enableMyLocation();
  }

  @Override
  public void onPause() {
    super.onPause();
    myLocation.disableMyLocation();
  }

  /**
   * Initializes this Map.
   */
  private void initMap() {

    setContentView(R.layout.main);
    mapView = (MapView) findViewById(R.id.mapview);
    mapView.setBuiltInZoomControls(true);
    mapOverlays = mapView.getOverlays();
    mapController = mapView.getController();
    marker = getResources().getDrawable(R.drawable.map_marker);
  }

  /**
   * Initializes the GPS localization.
   */
  private void initGPS() {

    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000,
        0, this);
    myLocation = new MyLocationOverlay(getApplicationContext(), mapView);
    myLocation.runOnFirstFix(new Runnable() {
      @Override
      public void run() {
        mapController.animateTo(myLocation.getMyLocation());
        mapController.setZoom(14);
        loadPOIs();
      }
    });

    myLocation.enableMyLocation();
    mapOverlays.add(myLocation);
  }

  /**
   * Loads the <code>POI</code>s from the database.
   */
  private void loadPOIs() {

    poidb = new POIDB();

    double latitude = myLocation.getMyLocation().getLatitudeE6() / 1E6;
    double longitude = myLocation.getMyLocation().getLongitudeE6() / 1E6;

    double lat_span = mapView.getLatitudeSpan() / 1E6;
    double lon_span = mapView.getLongitudeSpan() / 1E6;

    pois = poidb.getPOI(latitude, longitude, lat_span, lon_span);

    itemizedOverlay = new POIItemizedOverlay(marker, this);

    for (POI poi : pois) {
      if (!filter.contains(poi.getTag())) {
        POIOverlayItem item = new POIOverlayItem(new GeoPoint(
            (int) (poi.getLatitude() * 1E6), (int) (poi.getLongitude() * 1E6)),
            "", "");
        item.setPoi(poi);
        itemizedOverlay.addOverlay(item);
      }
    }

    if (itemizedOverlay.size() != 0)
      mapOverlays.add(itemizedOverlay);

  }

  @Override
  public void onLocationChanged(Location location) {

    mapOverlays.remove(myLocation);
    mapOverlays.add(new MyLocationOverlay(this, mapView));

    // Reload the pois when the location has changed too many times.
    if (iterator++ == RELOAD_RATE) { // TODO reload rate from preferences?
      loadPOIs();
      iterator = 0;
    }

    mapView.invalidate();
  }

  @Override
  public void onProviderDisabled(String provider) {

  }

  @Override
  public void onProviderEnabled(String provider) {

  }

  @Override
  public void onStatusChanged(String provider, int status, Bundle extras) {

  }

  @Override
  protected boolean isRouteDisplayed() {

    return false;
  }
}