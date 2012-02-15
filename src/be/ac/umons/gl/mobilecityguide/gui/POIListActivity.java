package be.ac.umons.gl.mobilecityguide.gui;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import be.ac.umons.gl.mobilecityguide.db.POIDB;
import be.ac.umons.gl.mobilecityguide.db.TagDB;
import be.ac.umons.gl.mobilecityguide.poi.Itinerary;
import be.ac.umons.gl.mobilecityguide.poi.POI;

public class POIListActivity extends ListActivity {

  private List<POI> pois;
  private Itinerary itinerary;
  private POIDB poiDB;

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    TagDB tagDB = new TagDB(this);
    SharedPreferences prefs = getSharedPreferences("MobileCityGuide",
        MODE_WORLD_READABLE);

    itinerary = (Itinerary) getApplicationContext();

    poiDB = new POIDB(this.getApplicationContext());
    pois = poiDB.getPOIList();

    this.setListAdapter(new ArrayAdapter<POI>(this,
        android.R.layout.simple_list_item_1, pois));
  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {

    Intent i = new Intent(this, POIDisplayActivity.class);
    i.putExtra("poi", pois.get(position).getId());
    startActivityForResult(i, 1);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {

  }

  @Override
  public void onBackPressed() {
    Intent data = new Intent();
    setResult(1, data);

    super.onBackPressed();
  }
}
