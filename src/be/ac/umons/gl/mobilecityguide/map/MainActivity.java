package be.ac.umons.gl.mobilecityguide.map;

import android.os.Bundle;
import be.ac.umons.gl.mobilecityguide.R;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

/**
 * @author Quentin Loos & Hugo Allard
 *
 */
public class MainActivity extends MapActivity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.main);
      MapView mapView = (MapView) findViewById(R.id.mapview);
      mapView.setBuiltInZoomControls(true);
  }

  /* (non-Javadoc)
   * @see com.google.android.maps.MapActivity#isRouteDisplayed()
   */
  @Override
  protected boolean isRouteDisplayed() {
    return false;
  }
}
