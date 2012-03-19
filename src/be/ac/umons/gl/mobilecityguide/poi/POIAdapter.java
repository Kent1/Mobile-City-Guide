package be.ac.umons.gl.mobilecityguide.poi;

import java.util.List;
import be.ac.umons.gl.mobilecityguide.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * The Adapter for <code>POI</code>.
 * 
 * @author Allard Hugo
 */
public class POIAdapter extends ArrayAdapter<POI>{

  /** */
  private List<POI> pois;
  
  /** */
  private LayoutInflater inflater;
  
  /**
   * Constructs a new instance of <code>POIAdapter</code> with the given context.
   * 
   * @param context The <code>Context</code> of the application.
   * @param textViewResourceId 
   * @param pois The <code>List</code> of <code>POI</code> to display.
   */
  public POIAdapter(Context context, int textViewResourceId, List<POI> pois){

    super(context, textViewResourceId, pois);
    inflater = LayoutInflater.from(context);
    this.pois = pois;
  }
  
  @Override
  public int getCount(){
    
    return pois.size();
  }
  
  @Override
  public POI getItem(int position){
    
    return pois.get(position);
  }
  
  @Override
  public long getItemId(int position){
    
    return position;
  }
  
  @Override
  public View getView(int position, View convertView, ViewGroup parent){
    
    ViewHolder holder;
    
    if(convertView == null){
      
      holder = new ViewHolder();
      convertView = inflater.inflate(R.layout.poi_in_list, null);
      holder.name = (TextView) convertView.findViewById(R.id.name);
      holder.address = (TextView) convertView.findViewById(R.id.address);
      convertView.setTag(holder);
    }
    else{
      
      holder = (ViewHolder) convertView.getTag();
    }
    
    holder.name.setText(pois.get(position).getName());
    holder.address.setText(pois.get(position).getTag()); //TODO modifier ce qu'on veut
    
    return convertView;
  }
  
  /**
   * Holds the different views to avoid too many access to <code>findViewById</code>.
   * 
   * @author Allard Hugo
   */
  private class ViewHolder{
    
    TextView name;
    TextView address;
  }
}
