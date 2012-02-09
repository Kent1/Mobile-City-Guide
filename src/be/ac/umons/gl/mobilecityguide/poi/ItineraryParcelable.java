package be.ac.umons.gl.mobilecityguide.poi;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * A <code>Parcelable</code> implementation of an <code>Itinerary</code>.
 * 
 * @author Allard Hugo
 */
public class ItineraryParcelable implements Parcelable{

  private Itinerary itinerary;
  
  /**
   * Constructs an <code>Itinerary</code> from another one.
   * 
   * @param itinerary The source one.
   */
  public ItineraryParcelable(Itinerary itinerary){
    
    this.itinerary = itinerary;
  }
  
  /**
   * Constructs an <code>Itinerary</code> from a <code>Parcel</code>.
   * 
   * @param source The <code>Parcel</code>.
   */
  public ItineraryParcelable(Parcel source){
    
    itinerary = new Itinerary();
    
    List<POIParcelable> list = source.readArrayList(null);
    List<POI> temp = new ArrayList<POI>();
    
    for(POIParcelable parcel : list)
      temp.add(parcel.getPoi());

    itinerary.setList(temp);
  }

  /**
   * @return The <code>Itinerary</code>.
   */
  public Itinerary getItinerary(){
    
    return itinerary;
  }

  /**
   * @param poi The <code>Itinerary</code> to set.
   */
  public void setItinerary(Itinerary itinerary){
    
    this.itinerary = itinerary;
  }
  
  @Override
  public int describeContents(){

    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags){
    
    List<POIParcelable> parcel = new ArrayList<POIParcelable>();
    
    for(POI poi : itinerary.getList())
      parcel.add(new POIParcelable(poi));
        
    dest.writeList(parcel);
  }
  
  public static final Parcelable.Creator<ItineraryParcelable> CREATOR = new Parcelable.Creator<ItineraryParcelable>() {

    @Override
    public ItineraryParcelable createFromParcel(Parcel source) {

      return new ItineraryParcelable(source);
    }

    @Override
    public ItineraryParcelable[] newArray(int size) {

      return new ItineraryParcelable[size];
    }
  };
}