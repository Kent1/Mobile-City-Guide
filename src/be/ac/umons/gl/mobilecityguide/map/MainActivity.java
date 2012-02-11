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
import be.ac.umons.gl.mobilecityguide.gui.ItineraryActivity;
import be.ac.umons.gl.mobilecityguide.gui.POIDisplayActivity;
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

  /** Refresh value in km */
  private int refreshValue;

  /** The radius in which we load POIs in km */
  private int radius;

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

  /** The preference of the user */
  private SharedPreferences prefs;

  /** Initial location */
  private GeoPoint initLocation;

  @Override
  public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    prefs = getSharedPreferences("MobileCityGuide", MODE_WORLD_READABLE);
    radius = Integer.parseInt(prefs.getString("radius", "5"));
    refreshValue = Integer.parseInt(prefs.getString("refreshvalue", "1"));

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

    initLocation = mapView.getMapCenter();

    locationHelper = new LocationHelper(this);
  }

  @Override
  public void onResume() {
    super.onResume();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    locationHelper.disableLocation();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    switch (requestCode) {
    case R.id.itemPOIDisplay:
    case R.id.itemPOIList:
    case R.id.itemItinerary:
      itinerary = ((ItineraryParcelable) data.getExtras().getParcelable(
          "itinerary")).getItinerary();
      return;
    case R.id.itemFilter:
      loadPOIs();
      return;
    case R.id.itemPreferences:
      radius = Integer.parseInt(prefs.getString("radius", "5"));
      refreshValue = Integer.parseInt(prefs.getString("refreshvalue", "1"));
      return;
    }
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
    case R.id.itemItinerary:
      Intent intent = new Intent(this, ItineraryActivity.class);
      ItineraryParcelable ip = new ItineraryParcelable(itinerary);
      intent.putExtra("itinerary", ip);
      this.startActivityForResult(intent, R.id.itemItinerary);
      return true;
    case R.id.itemPOIList:
      Intent i = new Intent(this, POIListActivity.class);
      ArrayList<POIParcelable> list = new ArrayList<POIParcelable>();
      for (POI poi : pois)
        list.add(new POIParcelable(poi));
      i.putExtra("POIs", list);
      i.putExtra("itinerary", new ItineraryParcelable(itinerary));
      this.startActivityForResult(i, R.id.itemPOIList);
      return true;
    case R.id.itemFilter:
      this.startActivityForResult(new Intent(this, FilterActivity.class),
          R.id.itemFilter);
      return true;
    case R.id.itemPreferences:
      this.startActivityForResult(new Intent(this, PreferencesActivity.class),
          R.id.itemPreferences);
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

    pois = poidb.getPOI(latitude, longitude, radius);

    mapOverlays.remove(itemizedOverlay);
    itemizedOverlay = new POIItemizedOverlay(marker, this);

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

  public void displayPOI(POI poi) {
    Intent intent = new Intent(this, POIDisplayActivity.class);
    intent.putExtra("poi", new POIParcelable(poi));
    intent.putExtra("itinerary", new ItineraryParcelable(itinerary));
    this.startActivityForResult(intent, R.id.itemPOIDisplay);
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
          initLocation = myLocation.getMyLocation();
          loadPOIs();
        }
      });
    }

    @Override
    public void onLocationChanged(Location location) {
      if (myLocation.getMyLocation() == null)
        return;

      if (DistanceHelper.distance(initLocation.getLongitudeE6() / 1E6,
          initLocation.getLatitudeE6() / 1E6, myLocation.getMyLocation()
              .getLongitudeE6() / 1E6, myLocation.getMyLocation()
              .getLatitudeE6() / 1E6) > refreshValue) {

        loadPOIs();
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