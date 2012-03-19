package be.ac.umons.gl.mobilecityguide.gui;

import java.util.List;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import be.ac.umons.gl.mobilecityguide.R;
import be.ac.umons.gl.mobilecityguide.db.POIDB;
import be.ac.umons.gl.mobilecityguide.poi.POI;
import be.ac.umons.gl.mobilecityguide.poi.POIAdapter;

public class POIListActivity extends ListActivity {

  private List<POI> pois;
  private POIDB poiDB;

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    poiDB = new POIDB(this.getApplicationContext());
    pois = poiDB.getPOIList();

    setContentView(R.layout.poilistactivity);
    this.setListAdapter(new POIAdapter(this, R.layout.poi_in_list, pois));
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
