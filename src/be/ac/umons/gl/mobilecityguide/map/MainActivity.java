package be.ac.umons.gl.mobilecityguide.map;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import be.ac.umons.gl.mobilecityguide.R;
import be.ac.umons.gl.mobilecityguide.db.POIDB;
import be.ac.umons.gl.mobilecityguide.poi.POIItemizedOverlay;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

/**
 * @author Quentin Loos & Hugo Allard
 * 
 */
public class MainActivity extends MapActivity implements LocationListener {
  // Map Items
  private MapController mapController;
  private MapView mapView;
  private GeoPoint point;
  // Location
  private LocationManager locationManager;
  private MyLocationOverlay myLocation;
  // Overlay items
  private List<Overlay> mapOverlays;
  // POI Items
  private POIItemizedOverlay itemizedOverlay;
  // private POIOverlayItem overlayitem;
  POIDB poidb;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    mapView = (MapView) findViewById(R.id.mapview);
    mapView.setBuiltInZoomControls(true);

    // Get Location
    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000,
        0, this);
    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
        10000, 0, this);

    myLocation = new MyLocationOverlay(getApplicationContext(), mapView);
    myLocation.runOnFirstFix(new Runnable() {
      @Override
      public void run() {
        mapController.animateTo(myLocation.getMyLocation());
        mapController.setZoom(14);
      }
    });
    myLocation.enableMyLocation();
    mapOverlays = mapView.getOverlays();
    mapOverlays.add(myLocation);

    // Overlay Stuff
    poidb = new POIDB();

    Drawable poimarker = this.getResources().getDrawable(R.drawable.poimarker);
    itemizedOverlay = new POIItemizedOverlay(poimarker, this);
    // itemizedOverlay.addOverlay() //TODO Add POIOVerlayItem
    mapOverlays.add(itemizedOverlay);
    mapView.invalidate();

    mapController = mapView.getController();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.android.maps.MapActivity#isRouteDisplayed()
   */
  @Override
  protected boolean isRouteDisplayed() {
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * android.location.LocationListener#onLocationChanged(android.location.Location
   * )
   */
  @Override
  public void onLocationChanged(Location location) {
    // mapView.getOverlays().clear();
    double lat = location.getLatitude();
    double lng = location.getLongitude();
    GeoPoint p = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
    // overlayitem = new OverlayItem(point, "", "");
    mapController.animateTo(p);
    // mapController.setCenter(p);
  }

  /*
   * (non-Javadoc)
   * 
   * @see android.location.LocationListener#onProviderDisabled(java.lang.String)
   */
  @Override
  public void onProviderDisabled(String provider) {
  }

  /*
   * (non-Javadoc)
   * 
   * @see android.location.LocationListener#onProviderEnabled(java.lang.String)
   */
  @Override
  public void onProviderEnabled(String provider) {
  }

  /*
   * (non-Javadoc)
   * 
   * @see android.location.LocationListener#onStatusChanged(java.lang.String,
   * int, android.os.Bundle)
   */
  @Override
  public void onStatusChanged(String provider, int status, Bundle extras) {
  }
}
