package be.ac.umons.gl.mobilecityguide.gui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import be.ac.umons.gl.mobilecityguide.R;
import be.ac.umons.gl.mobilecityguide.poi.Itinerary;
import be.ac.umons.gl.mobilecityguide.poi.ItineraryParcelable;
import be.ac.umons.gl.mobilecityguide.poi.POI;
import be.ac.umons.gl.mobilecityguide.poi.POIParcelable;

/**
 * @author Quentin Loos
 */
public class ItineraryActivity extends ListActivity {
  Itinerary itinerary;
  ArrayAdapter<POI> array;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    itinerary = ((ItineraryParcelable) getIntent().getParcelableExtra(
        "itinerary")).getItinerary();

    this.registerForContextMenu(this.getListView());
  }

  @Override
  protected void onResume() {
    super.onResume();

    array = new ArrayAdapter<POI>(this, android.R.layout.simple_list_item_1,
        itinerary.getList());
    this.setListAdapter(array);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    itinerary = ((ItineraryParcelable) data.getExtras().getParcelable(
        "itinerary")).getItinerary();
  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    Intent i = new Intent(this, POIDisplayActivity.class);
    i.putExtra("poi", new POIParcelable(itinerary.getList().get(position)));
    i.putExtra("itinerary", new ItineraryParcelable(itinerary));
    startActivityForResult(i, 2);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.itinerarymenu, menu);
    return true;
  }

  @Override
  public boolean onMenuItemSelected(int featureId, MenuItem item) {
    switch (item.getItemId()) {
    case R.id.itemNewItinerary:
      // Start here activity creation
    case R.id.itemDeleteItinerary:
      itinerary = new Itinerary();
      array.clear();
      return true;
    }
    return super.onMenuItemSelected(featureId, item);
  }

  @Override
  public void onCreateContextMenu(ContextMenu menu, View v,
      ContextMenuInfo menuInfo) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.itinerarycontextmenu, menu);
  }

  @Override
  public boolean onContextItemSelected(MenuItem item) {
    AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
    switch (item.getItemId()) {
    case R.id.itemRemove:
      itinerary.remove(itinerary.getList().get(info.position));
      array.notifyDataSetChanged();
      return true;
    default:
      return super.onContextItemSelected(item);
    }
  }

  @Override
  public void finish() {
    Intent data = new Intent();
    data.putExtra("itinerary", new ItineraryParcelable(itinerary));
    setResult(1, data);

    super.finish();
  }
}
