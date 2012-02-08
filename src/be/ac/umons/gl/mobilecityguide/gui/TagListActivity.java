package be.ac.umons.gl.mobilecityguide.gui;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import be.ac.umons.gl.mobilecityguide.R;

/**
 * @author Quentin Loos
 */
public class TagListActivity extends ListActivity {

  private HashMap<String, Boolean> filter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.taglistactivity);

    if (getIntent().getSerializableExtra("filter") == null)
      this.finish();

    filter = (HashMap<String, Boolean>) getIntent().getSerializableExtra(
        "filter");
    ArrayList<HashMap<String, Boolean>> list = new ArrayList<HashMap<String, Boolean>>();
    list.add(filter);

    ArrayAdapter tags = new ArrayAdapter(this,
        android.R.layout.simple_list_item_checked, list);

    this.setListAdapter(tags);
  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    CheckedTextView textview = (CheckedTextView) v;
    textview.setChecked(!textview.isChecked());
  }
}
