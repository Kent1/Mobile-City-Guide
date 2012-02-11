package be.ac.umons.gl.mobilecityguide.gui;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import be.ac.umons.gl.mobilecityguide.db.TagDB;
import be.ac.umons.gl.mobilecityguide.poi.Itinerary;
import be.ac.umons.gl.mobilecityguide.poi.ItineraryParcelable;
import be.ac.umons.gl.mobilecityguide.poi.POI;
import be.ac.umons.gl.mobilecityguide.poi.POIParcelable;

public class POIListActivity extends ListActivity {

  private List<POI> pois;
  Itinerary itinerary;

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    TagDB tagDB = new TagDB(this);
    SharedPreferences prefs = getSharedPreferences("MobileCityGuide",
        MODE_WORLD_READABLE);

    List<POIParcelable> poisp = (getIntent()
        .getParcelableArrayListExtra("POIs"));
    itinerary = ((ItineraryParcelable) (getIntent()
        .getParcelableExtra("itinerary"))).getItinerary();
    pois = new ArrayList<POI>();

    for (POIParcelable poi : poisp) {

      POI p = poi.getPoi();
      // We apply filter or not ?
      // if (tagDB.isTagSelected(p.getTag()) && p.getRank() >=
      // prefs.getInt("rankRadioGroup", 0))
      pois.add(p);
    }
    this.setListAdapter(new ArrayAdapter<POI>(this,
        android.R.layout.simple_list_item_1, pois));
  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {

    Intent i = new Intent(this, POIDisplayActivity.class);
    i.putExtra("poi", new POIParcelable(pois.get(position)));
    i.putExtra("itinerary", new ItineraryParcelable(itinerary));
    startActivityForResult(i, 1);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    itinerary = ((ItineraryParcelable) data.getExtras().getParcelable(
        "itinerary")).getItinerary();
  }

  @Override
  public void finish() {

    Intent data = new Intent();
    data.putExtra("itinerary", new ItineraryParcelable(itinerary));
    setResult(1, data);

    super.finish();
  }
}
