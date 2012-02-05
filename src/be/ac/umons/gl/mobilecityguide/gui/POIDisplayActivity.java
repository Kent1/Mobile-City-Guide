package be.ac.umons.gl.mobilecityguide.gui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import be.ac.umons.gl.mobilecityguide.R;
import be.ac.umons.gl.mobilecityguide.db.InfosDB;
import be.ac.umons.gl.mobilecityguide.db.RankingDB;
import be.ac.umons.gl.mobilecityguide.poi.POI;
import be.ac.umons.gl.mobilecityguide.poi.POIParcelable;

public class POIDisplayActivity extends Activity {

  private POI poi;
  private TextView name, address, description, tag, price, duration;
  private RatingBar ratingBar;
  private final RankingDB rdb = new RankingDB();
  private final InfosDB idb = new InfosDB();

  @Override
  public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.display_poi);

    if (getIntent().getParcelableExtra("poi") == null)
      this.finish();

    poi = ((POIParcelable) (getIntent().getParcelableExtra("poi"))).getPoi();

    poi.setPrice(idb.getPrice(poi.getId()));
    poi.setDuration(idb.getDuration(poi.getId()));

    poi.setRank(rdb.getRank(poi.getId()));

    name = (TextView) findViewById(R.id.name);
    name.setText(poi.getName());

    address = (TextView) findViewById(R.id.address);
    address.setText(poi.getAddress());

    description = (TextView) findViewById(R.id.description);
    description.setText(poi.getDescription());

    price = (TextView) findViewById(R.id.price);
    price.setText(getString(R.string.price) + " " + poi.getPrice() + "€");

    duration = (TextView) findViewById(R.id.duration);
    duration.setText(getString(R.string.duration) + " " + poi.getDuration()
        + getString(R.string.minutes));

    ratingBar = (RatingBar) findViewById(R.id.rating);
    ratingBar.setRating((float) poi.getRank());
  }
}
