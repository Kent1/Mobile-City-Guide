package be.ac.umons.gl.mobilecityguide.gui;

import java.util.ArrayList;
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
import android.widget.Toast;

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
  private boolean[] state;
  
  /** The list of tags. */
  private TagDB tagDB;
  
  private ArrayAdapter<String> adapter;
  private LayoutInflater mInflater;
  private Button generate, all, none;
  private RatingBar minRank;
  private EditText maxTime, maxPrice;
  
  @Override
  protected void onCreate(Bundle savedInstanceState){
    
    super.onCreate(savedInstanceState);
    
    setContentView(R.layout.itinerarycreationactivity);

    tagDB = new TagDB(this);
    
    itinerary = new Itinerary();
    pois = new ArrayList<POI>();
    filter = new ArrayList<String>();
    state = new boolean[tagDB.getTagListMyDB().size()];

    List<POIParcelable> temp = getIntent().getExtras().getParcelableArrayList("pois");
    for(POIParcelable parcel : temp)
      pois.add(parcel.getPoi());

    mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, tagDB.getTagListMyDB()){
      
      @Override
      public View getView(int position, View convertView, ViewGroup parent){
  
        View row;
        if (convertView == null)
          row = mInflater.inflate(android.R.layout.simple_list_item_checked, null);
        else
          row = convertView;

        CheckedTextView c = (CheckedTextView) row.findViewById(android.R.id.text1);
        c.setText(getItem(position));
        c.setChecked(state[position]);
        
        return c;
      }
    };
    
    this.setListAdapter(adapter);


    minRank = (RatingBar) findViewById(R.id.rating);
    maxTime = (EditText) findViewById(R.id.time);
    maxPrice = (EditText) findViewById(R.id.price);
    generate = (Button) findViewById(R.id.generate);
    all = (Button) findViewById(R.id.all);
    none = (Button) findViewById(R.id.clear);
    
    generate.setOnClickListener(new OnClickListener(){

      @Override
      public void onClick(View v){

        if(maxTime.length() > 0 && maxPrice.length() > 0){
          
          generate();
          finish();
        }
        else
          Toast.makeText(ItineraryCreationActivity.this, getString(R.string.error), Toast.LENGTH_SHORT).show();
      }
    });
    
    all.setOnClickListener(new OnClickListener(){

      @Override
      public void onClick(View v){

        int size = getListView().getAdapter().getCount();

        for(int i = 0; i < size; i++)
          state[i] = true;
        
        filter = new ArrayList<String>();
        adapter.notifyDataSetChanged();
      }
    });
    
    none.setOnClickListener(new OnClickListener(){

      @Override
      public void onClick(View v){

        int size = getListView().getAdapter().getCount();
        
        for(int i = 0; i < size; i++)
          state[i] = false;
        
        filter = tagDB.getTagListMyDB();
        adapter.notifyDataSetChanged();
      }
    });
  }
  
  @Override
  protected void onListItemClick(ListView l, View v, int position, long id){
    
    CheckedTextView c = (CheckedTextView) v;
    String tag = (String) c.getText();
    int i = tagDB.getTagListMyDB().indexOf((String) c.getText());
    
    if(!state[i])
      filter.add(tag);
    else
      filter.remove(tag);
    
    state[i] = !state[i];
    
    adapter.notifyDataSetChanged();
  }

  /**
   * Generates a new <code>Itinerary</code> with the specified preferences.
   */
  private void generate(){
    
    int i = 0;
    int time = 0;
    double price = 0;
    
    try{
      time = Integer.parseInt(maxTime.getText().toString());
      price = Double.parseDouble(maxPrice.getText().toString());
    }
    catch(Exception e){
      finish();
    }
    
    // Filter the POIs
    while(i < pois.size()){
      
      if(pois.get(i).getRank() < minRank.getRating() || filter.contains(pois.get(i).getTag()))
        pois.remove(i);
      else
        i++;
    }
    
    // Generate from the filtered POIs
    while(time > 0 && pois.size() > 0 && itinerary.size() < 10){
      
      i = (int) (Math.random() * pois.size());
      
      if(pois.get(i).getDuration() <= time && pois.get(i).getPrice() <= price){
        
        itinerary.add(pois.get(i));
        time -= pois.get(i).getDuration();
        price -= pois.get(i).getPrice();
        pois.remove(i);
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
