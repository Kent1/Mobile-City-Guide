package be.ac.umons.gl.mobilecityguide.gui;

import be.ac.umons.gl.mobilecityguide.poi.Itinerary;
import be.ac.umons.gl.mobilecityguide.poi.ItineraryParcelable;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * This <code>Activity</code> is use to generate a new <code>Itinerary</code>.
 * 
 * @author Allard Hugo
 */
public class ItineraryCreationActivity extends Activity{

  Itinerary itinerary;
  
  @Override
  protected void onCreate(Bundle savedInstanceState){
    
    super.onCreate(savedInstanceState);
  }
  
  @Override
  public void finish(){
    
    Intent data = new Intent();
    data.putExtra("itinerary", new ItineraryParcelable(itinerary));
    setResult(1, data);
    
    super.finish();
  }
}
