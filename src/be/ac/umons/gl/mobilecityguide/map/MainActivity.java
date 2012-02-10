package be.ac.umons.gl.mobilecityguide.map;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import be.ac.umons.gl.mobilecityguide.R;
import be.ac.umons.gl.mobilecityguide.db.POIDB;
import be.ac.umons.gl.mobilecityguide.db.TagDB;
import be.ac.umons.gl.mobilecityguide.gui.FilterActivity;
import be.ac.umons.gl.mobilecityguide.gui.POIListActivity;
import be.ac.umons.gl.mobilecityguide.gui.PreferencesActivity;
import be.ac.umons.gl.mobilecityguide.poi.Itinerary;
import be.ac.umons.gl.mobilecityguide.poi.ItineraryParcelable;
import be.ac.umons.gl.mobilecityguide.poi.POI;
import be.ac.umons.gl.mobilecityguide.poi.POIItemizedOverlay;
import be.ac.umons.gl.mobilecityguide.poi.POIOverlayItem;
import be.ac.umons.gl.mobilecityguide.poi.POIParcelable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

public class MainActivity extends MapActivity {

  private static final int RELOAD_RATE = 20;

  /**
   * Used to reload the <code>POI</code>s every RELOAD_RATE call of
   * <code>onLocationChanged</code>.
   */
  private int iterator;

  /** The <code>MapView</code> for this map. */
  private MapView mapView;

  /** The <code>List</code> of every <code>POI</code>s around the user. */
  private List<POI> pois;

  /** The current <code>Itinerary</code>. */
  private Itinerary itinerary;

  /** The <code>TagDB</code> for get the tag list. */
  private TagDB tagDB;

  /** The <code>LocationHelperr</code> for GPS localization. */
  private LocationHelper locationHelper;

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

  /** The radius in which we load POIs */
  private double lat_span, lon_span;

  /** The preference of the user */
  private SharedPreferences prefs;

  @Override
  public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    prefs = getSharedPreferences("MobileCityGuide", MODE_WORLD_READABLE);

    itinerary = new Itinerary();

    tagDB = new TagDB(this);
    tagDB.retrieveTagList();

    poidb = new POIDB();

    setContentView(R.layout.main);
    mapView = (MapView) findViewById(R.id.mapview);
    mapView.setBuiltInZoomControls(true);
    mapView.getController().setZoom(16);
    mapOverlays = mapView.getOverlays();
    marker = getResources().getDrawable(R.drawable.map_marker);

    locationHelper = new LocationHelper(this);
  }

  @Override
  public void onResume() {
    super.onResume();
    locationHelper.enableLocation();
    loadPOIs();
  }

  @Override
  public void onStop() {
    super.onStop();
    locationHelper.disableLocation();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.mainmenu, menu);
    return true;
  }

  @Override
  public boolean onMenuItemSelected(int featureId, MenuItem item) {
    switch (item.getItemId()) {
    case R.id.itemPOIList:
      Intent i = new Intent(this, POIListActivity.class);
      ArrayList<POIParcelable> list = new ArrayList<POIParcelable>();
      for (POI poi : pois)
        list.add(new POIParcelable(poi));
      i.putExtra("POIs", list);
      this.startActivity(i);
      return true;
    case R.id.itemTagFilter:
      this.startActivity(new Intent(this, FilterActivity.class));
      return true;
    case R.id.itemPreferences:
      this.startActivity(new Intent(this, PreferencesActivity.class));
      return true;
    case R.id.itemClose:
      finish();
      return true;
    }
    return super.onMenuItemSelected(featureId, item);
  }

  public void loadPOIs() {

    GeoPoint p = myLocation.getMyLocation();
    if (p == null)
      p = mapView.getMapCenter();

    double latitude = p.getLatitudeE6() / 1E6;
    double longitude = p.getLongitudeE6() / 1E6;

    lat_span = mapView.getLatitudeSpan() / 1E6;
    lon_span = mapView.getLongitudeSpan() / 1E6;

    pois = poidb.getPOI(latitude, longitude, lat_span * 2, lon_span * 2);

    mapOverlays.remove(itemizedOverlay);
    itemizedOverlay = new POIItemizedOverlay(marker, this, itinerary);

    for (POI poi : pois) {
      if (tagDB.isTagSelected(poi.getTag())
          && poi.getRank() >= prefs.getInt("rankRadioGroup", 0)) {
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
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    itinerary = ((ItineraryParcelable) data.getExtras().getParcelable(
        "itinerary")).getItinerary();
  }

  @Override
  protected boolean isRouteDisplayed() {

    return false;
  }

  public class LocationHelper implements LocationListener {

    public LocationHelper(Context context) {

      locationManager = (LocationManager) context
          .getSystemService(Context.LOCATION_SERVICE);

      // locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
      // 60000, 0, this);
      locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
          10000, 0, this);

      myLocation = new MyLocationOverlay(context, mapView);
      myLocation.enableMyLocation();
      myLocation.runOnFirstFix(new Runnable() {
        @Override
        public void run() {
          mapOverlays.add(myLocation);
          mapView.getController().animateTo(myLocation.getMyLocation());
          loadPOIs();
        }
      });
    }

    @Override
    public void onLocationChanged(Location location) {
      if (iterator++ < RELOAD_RATE) // TODO reload rate from preferences?
        return;

      iterator = 0;

      GeoPoint p = myLocation.getMyLocation();

      double latitude = p.getLatitudeE6() / 1E6;
      double longitude = p.getLongitudeE6() / 1E6;

      List<POI> pois2 = poidb.getPOI(latitude, longitude, lat_span * 2,
          lon_span * 2);

      for (POI poi : pois2) {
        pois.add(poi);
        if (tagDB.isTagSelected(poi.getTag())) {
          POIOverlayItem item = new POIOverlayItem(
              new GeoPoint((int) (poi.getLatitude() * 1E6),
                  (int) (poi.getLongitude() * 1E6)), "", "");
          item.setPoi(poi);
          itemizedOverlay.addOverlay(item);
        }
      }

      if (itemizedOverlay.size() != 0)
        mapOverlays.add(itemizedOverlay);
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
      locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
          10000, 0, locationHelper);
      myLocation.enableMyLocation();
    }

    public void disableLocation() {
      locationManager.removeUpdates(locationHelper);
      myLocation.disableMyLocation();
    }
  }
}