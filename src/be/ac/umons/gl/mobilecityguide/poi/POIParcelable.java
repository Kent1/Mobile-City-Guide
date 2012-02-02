package be.ac.umons.gl.mobilecityguide.poi;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * A Parcelable implementation of POI
 * 
 * @author Allard Hugo & Quentin Loos
 */
public class POIParcelable implements Parcelable {
  /** POI to Parcel */
  private POI poi;
  
  /**
   * Constructor with POI
   * 
   * @param poi
   */
  public POIParcelable(POI poi){
    this.poi = poi;
  }
  
  /**
   * Constructor with Parcel
   * 
   * @param source
   */
  public POIParcelable(Parcel source) {
    poi = new POI();
    poi.setName(source.readString());
    poi.setAddress(source.readString());
    poi.setDescription(source.readString());
    poi.setId(source.readInt());
    poi.setDuration(source.readInt());
    poi.setVotes(source.readInt());
    poi.setPrice(source.readDouble());
    poi.setRank(source.readDouble());
    poi.setLatitude(source.readDouble());
    poi.setLongitude(source.readDouble());
    poi.setTag(source.readString());
  }

  /**
   * @return the poi
   */
  public POI getPoi() {
    return poi;
  }

  /**
   * @param poi the poi to set
   */
  public void setPoi(POI poi) {
    this.poi = poi;
  }

  /**
   * @see android.os.Parcelable#describeContents()
   */
  @Override
  public int describeContents() {
    return 0;
  }

  /**
   * @see android.os.Parcelable#writeToParcel(android.os.Parcel, int)
   */
  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(poi.getName());
    dest.writeString(poi.getAddress());
    dest.writeString(poi.getDescription());
    dest.writeInt(poi.getId());
    dest.writeInt(poi.getDuration());
    dest.writeInt(poi.getVotes());
    dest.writeDouble(poi.getPrice());
    dest.writeDouble(poi.getRank());
    dest.writeDouble(poi.getLatitude());
    dest.writeDouble(poi.getLongitude());
    dest.writeString(poi.getTag());
  }
  
  public static final Parcelable.Creator<POIParcelable> CREATOR = 
      new Parcelable.Creator<POIParcelable>(){
    @Override
    public POIParcelable createFromParcel(Parcel source){
      return new POIParcelable(source);
    }

    @Override
    public POIParcelable[] newArray(int size){
      return new POIParcelable[size];
    }   
  };
}