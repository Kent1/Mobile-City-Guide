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
import be.ac.umons.gl.mobilecityguide.poi.ItineraryParcelable;
import be.ac.umons.gl.mobilecityguide.poi.POI;
import be.ac.umons.gl.mobilecityguide.poi.POIItemizedOverlay;
import be.ac.umons.gl.mobilecityguide.poi.POIOverlayItem;
import be.ac.umons.gl.mobilecityguide.poi.POIParcelable;

import com.google.android.maps.GeoPoint;
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

  /** The marker to indicates <code>POI</code>s one the map. */
  private Drawable marker;

  /** The <code>List</code> with the <code>Overlay</code>s of this map. */
  private List<Overlay> mapOverlays;

  /** The preference of the user */
  private SharedPreferences prefs;

  /** The <code>LocationHelperr</code> for GPS localization. */
  private LocationHelper locationHelper;

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
    mapView.setSatellite(prefs.getBoolean("satellite", false));
    mapOverlays = mapView.getOverlays();
    marker = getResources().getDrawable(R.drawable.map_marker);
  }

  @Override
  public void onStart() {
    super.onStart();
    locationHelper = new LocationHelper(this, mapView);
    this.loadPOIs();
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
  public boolean onMenuItemSelected(int featureId, MenuItem item) {
    switch (item.getItemId()) {
    case R.id.itemItinerary:
      Intent intent = new Intent(this, ItineraryActivity.class);
      ItineraryParcelable ip = new ItineraryParcelable(itinerary);
      intent.putExtra("itinerary", ip);
      ArrayList<POIParcelable> parcel = new ArrayList<POIParcelable>();
      for (POI poi : pois)
        parcel.add(new POIParcelable(poi));
      intent.putParcelableArrayListExtra("pois", parcel);
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

    GeoPoint p = locationHelper.getMyLocation();

    double latitude = p.getLatitudeE6() / 1E6;
    double longitude = p.getLongitudeE6() / 1E6;

    pois = poidb.getPOI(latitude, longitude,
        Integer.parseInt(prefs.getString("radius", "5")));

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
}