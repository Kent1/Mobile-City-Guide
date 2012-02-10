package be.ac.umons.gl.mobilecityguide.gui;

import android.app.Activity;
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

    prefs = getSharedPreferences("MobileCityGuide", MODE_WORLD_READABLE);
    int radio = prefs.getInt("rankRadioGroup", 0);

    setContentView(R.layout.rankingfilteractivity);
    RadioGroup rg = (RadioGroup) this.findViewById(R.id.rank);
    rg.check(radio);
    rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        Editor edit = prefs.edit();
        int rank;
        switch (checkedId) {
        case R.id.radio1:
          rank = 1;
          break;
        case R.id.radio2:
          rank = 2;
          break;
        case R.id.radio3:
          rank = 3;
          break;
        case R.id.radio4:
          rank = 4;
          break;
        case R.id.radio5:
          rank = 5;
          break;
        default:
          rank = 0;
        }
        edit.putInt("rankRadioGroup", rank);
        edit.commit();
      }

    });
  }
}
