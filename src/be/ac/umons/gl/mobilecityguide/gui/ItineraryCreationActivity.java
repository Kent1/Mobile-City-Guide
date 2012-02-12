package be.ac.umons.gl.mobilecityguide.gui;

import java.util.List;
import be.ac.umons.gl.mobilecityguide.R;
import be.ac.umons.gl.mobilecityguide.db.TagDB;
import be.ac.umons.gl.mobilecityguide.poi.Itinerary;
import be.ac.umons.gl.mobilecityguide.poi.ItineraryParcelable;
import be.ac.umons.gl.mobilecityguide.poi.POI;
import be.ac.umons.gl.mobilecityguide.poi.POIParcelable;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;

/**
 * This <code>Activity</code> is use to generate a new <code>Itinerary</code>.
 * 
 * @author Allard Hugo
 */
public class ItineraryCreationActivity extends ListActivity{

  /** The generated <code>Itinerary</code>. */
  private Itinerary itinerary;
  
  /** The <code>List</code> of the <code>POI</code>s around the user. */
  private List<POI> pois;
  
  /** The tag filter. */
  private List<String> filter;
  
  /** The list of tags. */
  private TagDB tagDB;
  
  private LayoutInflater mInflater;
  private Button generate, all, none;
  private RatingBar minRank;
  private EditText maxTime;
  
  @Override
  protected void onCreate(Bundle savedInstanceState){
    
    super.onCreate(savedInstanceState);
    
    setContentView(R.layout.itinerarycreationactivity);

    itinerary = new Itinerary();
    
    List<POIParcelable> temp = (List<POIParcelable>) getIntent().getExtras().getParcelable("pois");
    for(POIParcelable parcel : temp)
      pois.add(parcel.getPoi());
    
    tagDB = new TagDB(this);

    mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, tagDB.getTagListMyDB()){
      
      @Override
      public View getView(int position, View convertView, ViewGroup parent){
  
        View row;
        if (convertView == null)
          row = mInflater.inflate(android.R.layout.simple_list_item_checked, null);
        else
          row = convertView;

        CheckedTextView c = (CheckedTextView) row.findViewById(android.R.id.text1);
        c.setText(getItem(position));
        
        return c;
      }
    });

        
    minRank = (RatingBar) findViewById(R.id.rating);
    maxTime = (EditText) findViewById(R.id.time);
    generate = (Button) findViewById(R.id.generate);
    all = (Button) findViewById(R.id.all);
    none = (Button) findViewById(R.id.clear);
    
    generate.setOnClickListener(new OnClickListener(){

      @Override
      public void onClick(View v){

        generate();
      }
    });
    
    all.setOnClickListener(new OnClickListener(){

      @Override
      public void onClick(View v){

        int size = getListView().getAdapter().getCount();
        
        for(int i = 0; i < size; i++)
          getListView().setItemChecked(i, true);
      }
    });
    
    none.setOnClickListener(new OnClickListener(){

      @Override
      public void onClick(View v){

        int size = getListView().getAdapter().getCount();
        
        for(int i = 0; i < size; i++)
          getListView().setItemChecked(i, false);
      }
    });
  }
  
  @Override
  protected void onListItemClick(ListView l, View v, int position, long id){
    
    CheckedTextView c = (CheckedTextView) v;
    tagDB.selectTag((String) c.getText(), !c.isChecked());
    c.setChecked(!c.isChecked());
  }
  
  /**
   * Generates a new <code>Itinerary</code> with the specified preferences.
   */
  private void generate(){
    
    int i = 0;
    int time = Integer.parseInt(maxTime.getText().toString());
    
    // Filter the POIs
    while(i < pois.size()){
      
      if(pois.get(i).getRank() < minRank.getRating())
        pois.remove(i);
      else if(filter.contains(pois.get(i).getTag()))
        pois.remove(i);
      else
        i++;
    }
    
    // Generate from the filtered POIs
    while(time > 0 && pois.size() > 0){
      
      i = (int) (Math.random() * pois.size());
      
      if(pois.get(i).getDuration() <= time){
        
        itinerary.add(pois.get(i));
        time -= pois.get(i).getDuration();
      }
      else
        pois.remove(i);
    }
  }
  
  @Override
  public void finish(){
    
    Intent data = new Intent();
    data.putExtra("itinerary", new ItineraryParcelable(itinerary));
    setResult(1, data);
    
    super.finish();
  }
}
