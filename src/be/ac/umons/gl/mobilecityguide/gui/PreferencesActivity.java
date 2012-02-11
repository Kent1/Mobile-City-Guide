package be.ac.umons.gl.mobilecityguide.gui;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import be.ac.umons.gl.mobilecityguide.R;

/**
 * Preferences/Settings Activity
 * 
 * @author Quentin Loos
 */
public class PreferencesActivity extends PreferenceActivity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    PreferenceManager manager = getPreferenceManager();
    manager.setSharedPreferencesName("MobileCityGuide");
    addPreferencesFromResource(R.xml.preferencesactivity);
  }
}
