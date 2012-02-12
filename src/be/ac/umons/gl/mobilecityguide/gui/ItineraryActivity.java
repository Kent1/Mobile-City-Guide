package be.ac.umons.gl.mobilecityguide.gui;

import java.util.ArrayList;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import be.ac.umons.gl.mobilecityguide.R;
import be.ac.umons.gl.mobilecityguide.poi.Itinerary;
import be.ac.umons.gl.mobilecityguide.poi.ItineraryParcelable;
import be.ac.umons.gl.mobilecityguide.poi.POI;
import be.ac.umons.gl.mobilecityguide.poi.POIParcelable;

/**
 * @author Quentin Loos
 */
public class ItineraryActivity extends ListActivity{
  
  private Itinerary itinerary;
  private ArrayAdapter<POI> array;
  private ArrayList<POIParcelable> pois = new ArrayList<POIParcelable>();
  private Button info, display;
  private TextView empty;

  @Override
  protected void onCreate(Bundle savedInstanceState){
    
    super.onCreate(savedInstanceState);

    setContentView(R.layout.itineraryactivity);
    
    empty = (TextView) findViewById(R.id.empty);
    info = (Button) findViewById(R.id.info);
    display = (Button) findViewById(R.id.display);

    itinerary = ((ItineraryParcelable) getIntent().getParcelableExtra("itinerary")).getItinerary();

    info.setOnClickListener(new OnClickListener(){
      @Override
      public void onClick(View v){
        
        if(itinerary.size() > 0)
          Toast.makeText(ItineraryActivity.this, getString(R.string.totalprice) + " " + itinerary.getPrice() + "€ \n" + getString(R.string.totaltime) + " " + getString(R.string.minutes), Toast.LENGTH_SHORT).show();
        else
          Toast.makeText(ItineraryActivity.this, getString(R.string.empty), Toast.LENGTH_SHORT).show();
      }
    });
    
    display.setOnClickListener(new OnClickListener(){
      @Override
      public void onClick(View v){

        if(itinerary.size() > 0)
          ;//TODO display itinerary on map!
        else
          Toast.makeText(ItineraryActivity.this, getString(R.string.empty), Toast.LENGTH_SHORT).show();
      }
    });
    pois = getIntent().getExtras().getParcelableArrayList("pois");
    
    this.registerForContextMenu(this.getListView());
  }

  @Override
  protected void onResume(){
    
    super.onResume();

    array = new ArrayAdapter<POI>(this, android.R.layout.simple_list_item_1,
        itinerary.getList());
    this.setListAdapter(array);
    
    if(itinerary.size() > 0)
      empty.setVisibility(View.INVISIBLE);
    else
      empty.setVisibility(View.VISIBLE);
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
    startActivityForResult(i, R.id.itemPOIDisplay);
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
      Intent data = new Intent(this, ItineraryCreationActivity.class);
      for(POIParcelable poi : pois)
        data.putExtra("pois", poi);
      data.putParcelableArrayListExtra("pois", pois);
      startActivityForResult(data, 1);
    case R.id.itemDeleteItinerary:
      itinerary.clear();
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
