package be.ac.umons.gl.mobilecityguide.gui;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import be.ac.umons.gl.mobilecityguide.R;
import be.ac.umons.gl.mobilecityguide.db.TagDB;

/**
 * @author Quentin Loos & Allard Hugo
 */
public class TagFilterActivity extends ListActivity {

  private LayoutInflater mInflater;
  private TagDB tagDB;
  private ArrayList<String> list;
  private ArrayAdapter<String> adapter;
  private boolean[] state;
  private boolean mapFilter;
  private Button all, none;

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.tagfilteractivity);

    tagDB = new TagDB(this);
    mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    list = tagDB.getTagListMyDB();
    state = new boolean[list.size()];

    // On vient de ItineraryCreationActivity
    if (getIntent().hasExtra("tags")) {

      // On a déjà fait un premier filtre
      if (getIntent().hasExtra("flag")) {

        ArrayList<String> temp = getIntent().getExtras().getStringArrayList(
            "tags");

        for (int i = 0; i < temp.size(); i++)
          state[list.indexOf(temp.get(i))] = true;
      }

      // C'est la première fois qu'on vient
      else {
        for (int i = 0; i < list.size(); i++)
          state[i] = true;
      }

      mapFilter = false;
    }

    // On vient de la map
    else {

      mapFilter = true;
      for (int i = 0; i < list.size(); i++)
        state[i] = tagDB.isTagSelected(list.get(i));
    }

    adapter = new ArrayAdapter<String>(this,
        android.R.layout.simple_list_item_checked, list) {

      @Override
      public View getView(int position, View convertView, ViewGroup parent) {

        View row;

        if (convertView == null)
          row = mInflater.inflate(android.R.layout.simple_list_item_checked,
              null);
        else
          row = convertView;

        CheckedTextView c = (CheckedTextView) row
            .findViewById(android.R.id.text1);
        c.setText(getItem(position));
        c.setChecked(state[position]);

        return c;
      }
    };

    this.setListAdapter(adapter);

    none = (Button) findViewById(R.id.clear);
    all = (Button) findViewById(R.id.all);

    none.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {

        for (int i = 0; i < state.length; i++)
          state[i] = false;

        adapter.notifyDataSetChanged();
      }
    });

    all.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {

        for (int i = 0; i < state.length; i++)
          state[i] = true;

        adapter.notifyDataSetChanged();
      }
    });
  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {

    int i = tagDB.getTagListMyDB().indexOf(((CheckedTextView) v).getText());
    state[i] = !state[i];
    adapter.notifyDataSetChanged();
  }

  @Override
  public void onBackPressed() {
    if (mapFilter) {

      for (int i = 0; i < list.size(); i++)
        tagDB.selectTag(list.get(i), state[i]);
    } else {

      for (int i = list.size() - 1; i >= 0; i--)
        if (!state[i])
          list.remove(i);

      Intent data = new Intent();
      data.putStringArrayListExtra("tag", list);
      setResult(1, data);
    }

    super.onBackPressed();
  }
}
