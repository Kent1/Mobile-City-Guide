package be.ac.umons.gl.mobilecityguide.gui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import be.ac.umons.gl.mobilecityguide.R;
import be.ac.umons.gl.mobilecityguide.poi.POI;
import be.ac.umons.gl.mobilecityguide.poi.POIParcelable;

public class POIDisplayActivity extends Activity {

  private POI poi;
  private TextView name, address, description, tag, price, duration;
  private RatingBar ratingBar, ratingBarIndicator;
  private double myRank;

  @Override
  public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.display_poi);

    if (getIntent().getParcelableExtra("poi") == null)
      this.finish();

    poi = ((POIParcelable) (getIntent().getParcelableExtra("poi"))).getPoi();

    name = (TextView) findViewById(R.id.name);
    name.setText(poi.getName());

    ratingBarIndicator = (RatingBar) findViewById(R.id.ratingIndicator);
    ratingBarIndicator.setRating((float) poi.getRank());

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
    // TODO myRank
    ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
      @Override
      public void onRatingChanged(RatingBar ratingBar, float rating,
          boolean fromUser) {
        myRank = rating;
        Toast.makeText(POIDisplayActivity.this,
            "Vous attribuez une note de " + rating + "/5", Toast.LENGTH_SHORT)
            .show();
      }
    });
  }
}
