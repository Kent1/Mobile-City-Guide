package be.ac.umons.gl.mobilecityguide.gui;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import be.ac.umons.gl.mobilecityguide.db.TagDB;

/**
 * @author Quentin Loos
 */
public class TagListActivity extends ListActivity {
  private LayoutInflater mInflater;
  private Intent i;
  private TagDB tagDB;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(this.getListView());

    i = new Intent();
    setResult(0, i);

    tagDB = new TagDB(this);

    mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    new TagDB(this);

    this.setListAdapter(new ArrayAdapter<String>(this,
        android.R.layout.simple_list_item_checked, tagDB.getTagListMyDB()) {
      @Override
      public View getView(int position, View convertView, ViewGroup parent) {
        View row;

        if (convertView == null) {
          row = mInflater.inflate(android.R.layout.simple_list_item_checked,
              null);
        } else {
          row = convertView;
        }

        CheckedTextView c = (CheckedTextView) row
            .findViewById(android.R.id.text1);
        c.setText(getItem(position));
        c.setChecked(tagDB.isTagSelected(getItem(position)));

        return row;
      }
    });
  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    CheckedTextView c = (CheckedTextView) v;
    c.setChecked(!c.isChecked());
    tagDB.selectTag((String) c.getText(), c.isChecked());
    setResult(1, i);
  }
}
