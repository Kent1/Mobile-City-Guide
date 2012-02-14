package be.ac.umons.gl.mobilecityguide.map;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

public class LocationHelper implements LocationListener {
  /** The <code>LocationManager</code> for GPS localization. */
  private final LocationManager locationManager;

  /** The <code>Overlay</code> for user's location. */
  private final MyLocationOverlay myLocation;

  /** The initial location */
  private GeoPoint initLocation;

  /** The preference of the user */
  private final SharedPreferences prefs;

  /** Context of the MainActivity */
  private final Context context;

  public LocationHelper(Context _context, final MapView mapView) {

    this.context = _context;

    locationManager = (LocationManager) context
        .getSystemService(Context.LOCATION_SERVICE);

    initLocation = mapView.getMapCenter();

    prefs = context.getSharedPreferences("MobileCityGuide",
        Context.MODE_WORLD_READABLE);

    // locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
    // 60000, 0, this);
    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000,
        0, this);

    myLocation = new MyLocationOverlay(context, mapView);
    myLocation.enableMyLocation();
    myLocation.runOnFirstFix(new Runnable() {
      @Override
      public void run() {
        mapView.getOverlays().add(myLocation);
        mapView.getController().animateTo(myLocation.getMyLocation());
        initLocation = myLocation.getMyLocation();
        ((MainActivity) context).loadPOIs();
      }
    });
  }

  @Override
  public void onLocationChanged(Location location) {
    if (myLocation.getMyLocation() == null)
      return;

    if (DistanceHelper.distance(initLocation.getLongitudeE6() / 1E6,
        initLocation.getLatitudeE6() / 1E6, myLocation.getMyLocation()
            .getLongitudeE6() / 1E6,
        myLocation.getMyLocation().getLatitudeE6() / 1E6) > Integer
        .parseInt(prefs.getString("refreshvalue", "1"))) {

      ((MainActivity) context).loadPOIs();
      initLocation = myLocation.getMyLocation();
    }
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

  public void enableLocation() {
    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000,
        0, this);
    myLocation.enableMyLocation();
  }

  public void disableLocation() {
    locationManager.removeUpdates(this);
    myLocation.disableMyLocation();
  }

  public GeoPoint getMyLocation() {
    if (myLocation.getMyLocation() != null)
      return myLocation.getMyLocation();
    else
      return initLocation;
  }
}