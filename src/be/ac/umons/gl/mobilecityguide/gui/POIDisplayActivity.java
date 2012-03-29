package be.ac.umons.gl.mobilecityguide.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import be.ac.umons.gl.mobilecityguide.R;
import be.ac.umons.gl.mobilecityguide.db.POIDB;
import be.ac.umons.gl.mobilecityguide.db.RankingDB;
import be.ac.umons.gl.mobilecityguide.poi.Itinerary;
import be.ac.umons.gl.mobilecityguide.poi.POI;

/**
 * This <code>Activity</code> displays the informations for a specific
 * <code>POI</code>.
 * 
 * @author Allard Hugo
 */
public class POIDisplayActivity extends Activity {

  private POI poi;
  private TextView name, address, description, price, duration;
  private RatingBar ratingBar, ratingBarIndicator;
  private Button button, button2;
  private float myRank;
  private Itinerary itinerary;
  private RankingDB rdb;
  private POIDB pdb;

  @Override
  public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.poidisplayactivity);

    rdb = new RankingDB(this);
    pdb = new POIDB(this);

    poi = pdb.getPOI(getIntent().getIntExtra("poi", 0));
    itinerary = (Itinerary) getApplicationContext();

    name = (TextView) findViewById(R.id.name);
    name.setText(poi.getName());

    ratingBarIndicator = (RatingBar) findViewById(R.id.ratingIndicator);
    ratingBarIndicator.setRating((float) poi.getRank());

    address = (TextView) findViewById(R.id.address);
    address.setText(poi.getAddress());

    description = (TextView) findViewById(R.id.description);
    description.setText(poi.getDescription());

    price = (TextView) findViewById(R.id.price);
    price.setText(poi.getPrice() + " €");

    duration = (TextView) findViewById(R.id.duration);
    duration.setText(poi.getDuration() + " " + getString(R.string.minutes));

    myRank = (float) rdb.getMyRank(poi.getId());

    ratingBar = (RatingBar) findViewById(R.id.rating);
    ratingBar.setRating(myRank);
    ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {

      @Override
      public void onRatingChanged(RatingBar ratingBar, float rating,
          boolean fromUser) {

        Toast
            .makeText(POIDisplayActivity.this,
                getString(R.string.toast) + " " + rating + "/5",
                Toast.LENGTH_SHORT).show();
      }
    });

    button = (Button) findViewById(R.id.button);

    if (!itinerary.contains(poi)) {
      button.setText(R.string.add);
      button.setOnClickListener(new Add());
    } else {
      button.setText(R.string.remove);
      button.setOnClickListener(new Remove());
    }

    button2 = (Button) findViewById(R.id.button2);
    if (!poi.isVisited()) {
      button2.setText(R.string.visited);
      button2.setOnClickListener(new Visited());
    } else {
      button2.setText(R.string.noVisited);
      button2.setOnClickListener(new NoVisited());
    }
  }

  class Add implements OnClickListener {

    @Override
    public void onClick(View v) {

      itinerary.add(poi);
      button.setText(R.string.remove);
      button.setOnClickListener(new Remove());
    }
  }

  class Remove implements OnClickListener {

    @Override
    public void onClick(View v) {

      itinerary.remove(poi);
      button.setText(R.string.add);
      button.setOnClickListener(new Add());
    }
  }

  class Visited implements OnClickListener {

    @Override
    public void onClick(View v) {
      poi.setVisited(true);
      button2.setText(R.string.noVisited);
      button2.setOnClickListener(new NoVisited());
    }
  }

  class NoVisited implements OnClickListener {

    @Override
    public void onClick(View v) {
      poi.setVisited(false);
      button2.setText(R.string.visited);
      button2.setOnClickListener(new Visited());
    }
  }

  @Override
  public void onBackPressed() {
    if (myRank != ratingBar.getRating())
      rdb.rank(poi.getId(), ratingBar.getRating(), myRank);

    Intent data = new Intent();
    setResult(1, data);

    super.onBackPressed();
  }
}
