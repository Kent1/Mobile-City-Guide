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
import be.ac.umons.gl.mobilecityguide.poi.POI;
import be.ac.umons.gl.mobilecityguide.poi.POIParcelable;
import be.ac.umons.gl.mobilecityguide.poi.Itinerary;
import be.ac.umons.gl.mobilecityguide.poi.ItineraryParcelable;

/**
 * This <code>Activity</code> displays the informations for a specific <code>POI</code>.
 * 
 * @author Allard Hugo
 */
public class POIDisplayActivity extends Activity{

  private POI poi;
  private TextView name, address, description, price, duration;
  private RatingBar ratingBar, ratingBarIndicator;
  private Button add, remove;
  private float myRank;
  private Itinerary itinerary;
  private RankingDB rdb;
  private POIDB pdb;

  @Override
  public void onCreate(Bundle savedInstanceState){

    super.onCreate(savedInstanceState);
    setContentView(R.layout.poidisplayactivity);

    rdb = new RankingDB(this);
    pdb = new POIDB();

    poi = ((POIParcelable) (getIntent().getParcelableExtra("poi"))).getPoi();
    itinerary = ((ItineraryParcelable) (getIntent().getParcelableExtra("itinerary"))).getItinerary();
    
    name = (TextView) findViewById(R.id.name);
    name.setText(poi.getName());

    ratingBarIndicator = (RatingBar) findViewById(R.id.ratingIndicator);
    ratingBarIndicator.setRating((float) poi.getRank());

    address = (TextView) findViewById(R.id.address);
    address.setText(poi.getAddress());

    description = (TextView) findViewById(R.id.description);
    description.setText(pdb.getDescription(poi.getId()));

    price = (TextView) findViewById(R.id.price);
    price.setText(poi.getPrice() + " €");

    duration = (TextView) findViewById(R.id.duration);
    duration.setText(poi.getDuration() + " " + getString(R.string.minutes));

    myRank = (float) rdb.getMyRank(poi.getId());

    ratingBar = (RatingBar) findViewById(R.id.rating);
    ratingBar.setRating(myRank);
    ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener(){
      
      @Override
      public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser){
        
        Toast.makeText(POIDisplayActivity.this,
            getString(R.string.toast) + rating + "/5", Toast.LENGTH_SHORT)
            .show();
      }
    });
    
    add = (Button) findViewById(R.id.add);
    remove = (Button) findViewById(R.id.remove);
    
    add.setOnClickListener(new OnClickListener(){
      
      @Override
      public void onClick(View v){
        
        itinerary.add(poi);
        remove.setVisibility(View.VISIBLE);
        add.setVisibility(View.INVISIBLE);
      }
    });

    remove.setOnClickListener(new OnClickListener(){
      
      @Override
      public void onClick(View v){
        
        itinerary.remove(poi);
        add.setVisibility(View.VISIBLE);
        remove.setVisibility(View.INVISIBLE);
      }
    });
    
    if(itinerary.contains(poi))
      add.setVisibility(View.INVISIBLE);
    else
      remove.setVisibility(View.INVISIBLE);
  }

  @Override
  public void finish(){
    
    if(myRank != ratingBar.getRating())
      rdb.rank(poi.getId(), ratingBar.getRating(), myRank);
    
    Intent data = new Intent();
    data.putExtra("itinerary", new ItineraryParcelable(itinerary));
    setResult(1, data);
    
    super.finish();
  }
}
