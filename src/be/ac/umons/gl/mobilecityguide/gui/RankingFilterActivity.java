package be.ac.umons.gl.mobilecityguide.gui;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import be.ac.umons.gl.mobilecityguide.R;

/**
 * @author Quentin Loos
 */
public class RankingFilterActivity extends Activity {
  private SharedPreferences prefs;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    prefs = getPreferences(Context.MODE_PRIVATE);
    int radio = prefs.getInt("rankRadioGroup", 0);

    setContentView(R.layout.rankingfilteractivity);
    RadioGroup rg = (RadioGroup) this.findViewById(R.id.rank);
    rg.check(radio);
    rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        Editor edit = prefs.edit();
        edit.putInt("rankRadioGroup", checkedId);
        edit.commit();
      }

    });
  }
}
