package be.ac.umons.gl.mobilecityguide.gui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import be.ac.umons.gl.mobilecityguide.R;
import be.ac.umons.gl.mobilecityguide.db.POIDB;
import be.ac.umons.gl.mobilecityguide.poi.Itinerary;
import be.ac.umons.gl.mobilecityguide.poi.POI;

/**
 * This <code>Activity</code> is use to generate a new <code>Itinerary</code>.
 * 
 * @author Allard Hugo
 */
public class ItineraryCreationActivity extends Activity {

  /** The generated <code>Itinerary</code>. */
  private Itinerary itinerary;

  /** The <code>List</code> of the <code>POI</code>s around the user. */
  private List<POI> pois;

  /** The wanted tags. */
  private ArrayList<String> tags;

  private Button generate, toFilter;
  private RatingBar minRank;
  private EditText maxTime, maxPrice;

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    setContentView(R.layout.itinerarycreationactivity);
    
    itinerary = (Itinerary) getApplicationContext();
    itinerary.clear();
    pois = new ArrayList<POI>();
    
    tags = new ArrayList<String>();
    
    POIDB db = new POIDB(getApplicationContext());
    pois = db.getPOIList();

    minRank = (RatingBar) findViewById(R.id.rating);
    maxTime = (EditText) findViewById(R.id.time);
    maxPrice = (EditText) findViewById(R.id.price);
    generate = (Button) findViewById(R.id.generate);
    toFilter = (Button) findViewById(R.id.filter);

    generate.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {

        if (maxTime.length() > 0 && maxPrice.length() > 0) {

          generate();
          finish();
        } else
          Toast.makeText(ItineraryCreationActivity.this,
              getString(R.string.error), Toast.LENGTH_SHORT).show();
      }
    });

    toFilter.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {

        Intent i = new Intent(getApplicationContext(), TagFilterActivity.class);
        i.putStringArrayListExtra("tags", tags);
        if (tags != null)
          i.putExtra("flag", true);
        startActivityForResult(i, 1);
      }
    });
  }

  /**
   * Generates a new <code>Itinerary</code> with the specified preferences.
   */
  private void generate() {

    int i = 0;
    int time = 0;
    double price = 0;

    try {
      time = Integer.parseInt(maxTime.getText().toString());
      price = Double.parseDouble(maxPrice.getText().toString());
    } catch (Exception e) {
      finish();
    }

    // Filter the POIs
    while (i < pois.size()) {

      if (pois.get(i).getRank() < minRank.getRating()
          || (tags.size() > 0 && !tags.contains(pois.get(i).getTag())))
        pois.remove(i);
      else
        i++;
    }

    // Generate from the filtered POIs
    while (time > 0 && pois.size() > 0 && itinerary.size() < 10) {

      i = (int) (Math.random() * pois.size());

      if (pois.get(i).getDuration() <= time && pois.get(i).getPrice() <= price) {

        itinerary.add(pois.get(i));
        time -= pois.get(i).getDuration();
        price -= pois.get(i).getPrice();
        pois.remove(i);
      } else
        pois.remove(i);
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    tags = data.getExtras().getStringArrayList("tag");

    toFilter.setText(R.string.modifyfilter);
  }

  @Override
  public void onBackPressed() {
    Intent data = new Intent();
    setResult(1, data);

    super.onBackPressed();
  }
}
