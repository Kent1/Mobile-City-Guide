package be.ac.umons.gl.mobilecityguide.poi;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * A <code>Parcelable</code> implementation of <code>POI</code>.
 * 
 * @author Allard Hugo & Quentin Loos
 */
public class POIParcelable implements Parcelable {

  /** POI to Parcel */
  private POI poi;

  /**
   * Constructs a <code>POI</code> from another <code>POI</code>.
   * 
   * @param poi
   *          the <code>POI</code> source.
   */
  public POIParcelable(POI poi) {
    this.poi = poi;
  }

  /**
   * Constructs a <code>POI</code> from a <code>Parcel</code>.
   * 
   * @param source
   *          the <code>Parcel</code> source.
   */
  public POIParcelable(Parcel source) {
    poi = new POI();
    this.readFromParcel(source);
  }

  /**
   * @return the <code>POI</code>
   */
  public POI getPoi() {
    return poi;
  }

  /**
   * @param poi
   *          the <code>POI</code> to set
   */
  public void setPoi(POI poi) {
    this.poi = poi;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  private void readFromParcel(Parcel source) {
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

  public static final Parcelable.Creator<POIParcelable> CREATOR = new Parcelable.Creator<POIParcelable>() {

    @Override
    public POIParcelable createFromParcel(Parcel source) {

      return new POIParcelable(source);
    }

    @Override
    public POIParcelable[] newArray(int size) {

      return new POIParcelable[size];
    }
  };
}