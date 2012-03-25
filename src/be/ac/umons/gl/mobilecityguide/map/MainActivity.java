package be.ac.umons.gl.mobilecityguide.map;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
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
import be.ac.umons.gl.mobilecityguide.poi.POI;
import be.ac.umons.gl.mobilecityguide.poi.POIItemizedOverlay;
import be.ac.umons.gl.mobilecityguide.poi.POIOverlayItem;
import be.ac.umons.gl.mobilecityguide.route.Route;
import be.ac.umons.gl.mobilecityguide.route.RouteOverlay;
import be.ac.umons.gl.mobilecityguide.route.RouteProvider;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class MainActivity extends MapActivity {

  /** The <code>MapView</code> for this map. */
  private MapView mapView;

  /** The <code>List</code> of every <code>POI</code>s around the user. */
  private List<POI> pois = new ArrayList<POI>();

  /** The current <code>Itinerary</code>. */
  private Itinerary itinerary;

  /** The <code>TagDB</code> for get the tag list. */
  private TagDB tagDB;

  /** The <code>POIDB</code> to fetch <code>POI</code>s from the database. */
  private POIDB poidb;

  /** The <code>POIItemizedOverlay</code> with the <code>POI</code>s to display. */
  private POIItemizedOverlay itemizedOverlay;
  private RouteOverlay routeOverlay;

  /** The marker to indicates <code>POI</code>s one the map. */
  private Drawable marker;

  /** The <code>List</code> with the <code>Overlay</code>s of this map. */
  private List<Overlay> mapOverlays;

  /** The preference of the user */
  private SharedPreferences prefs;

  /** The <code>LocationHelperr</code> for GPS localization. */
  private LocationHelper locationHelper;

  /**
   * The state of MainActivity - 0 : FREEWALK, 1 : ITINERARY
   */
  private boolean STATE = false;

  @Override
  public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    prefs = getSharedPreferences("MobileCityGuide", MODE_WORLD_READABLE);

    poidb = new POIDB(this);
    tagDB = new TagDB(this);
    tagDB.retrieveTagList();

    itinerary = new Itinerary();

    mapView = (MapView) findViewById(R.id.mapview);
    mapView.setBuiltInZoomControls(true);
    mapView.getController().setZoom(16);
    mapView.setSatellite(prefs.getBoolean("satellite", false));
    mapOverlays = mapView.getOverlays();
    marker = getResources().getDrawable(R.drawable.map_marker);

    itemizedOverlay = new POIItemizedOverlay(marker, this);

    locationHelper = new LocationHelper(this, mapView);
    this.displayOverlay();
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
      return;
    case R.id.itemFilter:
      displayOverlay();
      return;
    case R.id.itemPreferences:
      mapView.setSatellite(prefs.getBoolean("satellite", false));
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
  public boolean onPrepareOptionsMenu(Menu menu) {
    if (STATE) {
      menu.getItem(0).setVisible(true);
      menu.getItem(1).setVisible(false);
    } else {
      menu.getItem(0).setVisible(false);
      menu.getItem(1).setVisible(true);
    }
    return true;
  }

  @Override
  public boolean onMenuItemSelected(int featureId, MenuItem item) {
    switch (item.getItemId()) {
    case R.id.itemShowPOI:
      STATE = false;
      this.displayOverlay();
      return true;
    case R.id.itemShowItinerary:
      STATE = true;
      this.displayOverlay();
      return true;
    case R.id.itemItinerary:
      Intent intent = new Intent(this, ItineraryActivity.class);
      this.startActivityForResult(intent, R.id.itemItinerary);
      return true;
    case R.id.itemPOIList:
      Intent i = new Intent(this, POIListActivity.class);
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

    pois = poidb.getPOIList();

    itemizedOverlay.clear();

    if (STATE) {
      for (POI poi : (Itinerary) this.getApplication()) {
        POIOverlayItem item = new POIOverlayItem(poi, "", "");
        itemizedOverlay.addOverlay(item);
      }
    } else {
      for (POI poi : pois) {
        if (tagDB.isTagSelected(poi.getTag())
            && poi.getRank() >= prefs.getInt("rankRadioGroup", 0)) {
          POIOverlayItem item = new POIOverlayItem(poi, "", "");
          itemizedOverlay.addOverlay(item);
        }
      }
    }
  }

  public void displayOverlay() {
    loadPOIs();
    if (STATE) {
      mapOverlays.remove(itemizedOverlay);
      RouteProvider routeProvider = new RouteProvider(
          locationHelper.getMyLocation(), locationHelper.getMyLocation(),
          (Itinerary) this.getApplication());
      Route route = routeProvider.startDriving();
      routeOverlay = new RouteOverlay(route);
      mapOverlays.add(routeOverlay);
      mapOverlays.add(itemizedOverlay);
    } else {
      if (itemizedOverlay.size() != 0)
        mapOverlays.add(itemizedOverlay);
      mapOverlays.remove(routeOverlay);
    }
    mapView.invalidate();
  }

  public void displayPOI(POI poi) {
    Intent intent = new Intent(this, POIDisplayActivity.class);
    intent.putExtra("poi", poi.getId());
    this.startActivityForResult(intent, R.id.itemPOIDisplay);
  }

  @Override
  protected boolean isRouteDisplayed() {

    return false;
  }
}