package be.ac.umons.gl.mobilecityguide.gui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckedTextView;
import android.widget.TextView;
import be.ac.umons.gl.mobilecityguide.R;

/**
 * @author kent
 * 
 */
public class FilterActivity extends Activity {
  private Context context;
  private SharedPreferences prefs;

  @Override
  public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.filteractivity);

    context = this;

    prefs = getPreferences(Context.MODE_PRIVATE);

    // Tag Filter
    TextView tag = (TextView) this.findViewById(R.id.tag);
    tag.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        context.startActivity(new Intent(context, TagFilterActivity.class));
      }

    });

    // Ranking Filter

    TextView rank = (TextView) this.findViewById(R.id.rank);
    rank.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        context.startActivity(new Intent(context, RankingFilterActivity.class));
      }

    });

    // Histo Filter

    CheckedTextView histo = (CheckedTextView) this.findViewById(R.id.histo);
    histo.setChecked(prefs.getBoolean("histo", false));
    histo.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        CheckedTextView c = (CheckedTextView) v;
        Editor edit = prefs.edit();
        edit.putBoolean("histo", !c.isChecked());
        edit.commit();
        c.setChecked(!c.isChecked());
      }
    });
  }
}
