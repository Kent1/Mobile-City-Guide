package be.ac.umons.gl.mobilecityguide.poi;

import java.util.ArrayList;
import java.util.List;
import com.google.android.maps.GeoPoint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * A <code>POI</code> is a place to visit.
 * 
 * @author Allard Hugo
 */
public class POI implements Parcelable{
  
  /** The Id of this <code>POI</code> in the database. */
  private int id;
  
  /** The duration of this <code>POI</code> in minutes. */
  private int duration;
  
  /** The number of votes for this <code>POI</code>. */
  private int votes;
  
  /** The price of this <code>POI</code>. */
  private double price;
  
  /** The global ranking for this <code>POI</code>. */
  private double rank;
  
  /** The local ranking for this <code>POI</code>. */
  private double myRank;
  
  /** The latitude for this <code>POI</code> in degrees. */
  private double latitude;
  
  /** The longitude for this <code>POI</code> in degrees. */
  private double longitude;
  
  /** The name of this <code>POI</code>. */
  private String name;
  
  /** The address of this <code>POI</code>. */
  private String address;
  
  /** The description of this <code>POI</code>. */
  private String description;
  
  /** The tags list of this <code>POI</code>. */
  private List<String> tags;
  
  /**
   * Constructs a new empty instance of <code>POI</code>.
   */
  public POI(){

    tags = new ArrayList<String>();
  }
  
  /**
   * Constructs a new instance of <code>POI</code> with the given ID.
   * 
   * @param id the id of this <code>POI</code>.
   */
  public POI(int id){
    
    this();
    this.id = id;
  }
  
  /**
   * Constructs a new instance of <code>POI</code> with the given ID and location.
   * 
   * @param id the id of this <code>POI</code>.
   * @param latitude the latitude (in degrees) of this <code>POI</code>.
   * @param longitude the longitude (in degrees) of this <code>POI</code>.
   */
  public POI(int id, double latitude, double longitude){
    
    this();
    this.id = id;
    this.setLatitude(latitude);
    this.setLongitude(longitude);
  }

  /**
   * Constructs a new instance of <code>POI</code> from a previous one.
   * 
   * @param in the <code>Parcel</code> to read.
   */
  public POI(Parcel in){

    this();
    this.name = in.readString();
    this.address = in.readString();
    this.description = in.readString();
    this.id = in.readInt();
    this.duration = in.readInt();
    this.votes = in.readInt();
    this.price = in.readDouble();
    this.rank = in.readDouble();
    this.myRank = in.readDouble();
    this.latitude = in.readDouble();
    this.longitude = in.readDouble();
    in.readList(tags, String.class.getClassLoader());
  }

  /**
   * Gets the unique ID for this <code>POI</code>.
   * 
   * @return the Id of this <code>POI</code>.
   */
  public int getId(){

    return id;
  }
  
  /**
   * Sets the Id of this <code>POI</code>.
   *
   * @param id Id the ID in the database.
   */
  public void setId(int id){

    this.id = id;
  }
  
  /**
   * Gets the duration for this <code>POI</code>.
   * 
   * @return the duration in minutes.
   */
  public int getDuration(){

    return duration;
  }

  /**
   * Sets the duration of this <code>POI</code>.
   * 
   * @param duration the duration in minutes.
   */
  public void setDuration(int duration){

    this.duration = duration;
  }

  /**
   * Gets the total number of votes for this <code>POI</code>.
   * 
   * @return the number of votes.
   */
  public int getVotes(){

    return votes;
  }

  /**
   * Sets the total number of votes of this <code>POI</code>.
   * 
   * @param votes the number of votes.
   */
  public void setVotes(int votes){

    this.votes = votes;
  }

  /**
   * Gets the price for this <code>POI</code> in euros.
   * 
   * @return the price in euros.
   */
  public double getPrice(){

    return price;
  }

  /**
   * Sets the price of this <code>POI</code>.
   * 
   * @param price the price in euros.
   */
  public void setPrice(double price){

    this.price = price;
  }

  /**
   * Gets the global ranking of this <code>POI</code>.<br>
   * The ranking is between 0 and 5.
   * 
   * @return the global ranking.
   */
  public double getRank(){

    return rank;
  }

  /**
   * Sets the global ranking of this <code>POI</code>.
   * 
   * @param rank the global ranking.
   */
  public void setRank(double rank){

    if(rank < 0)
      rank = 0;
    else if(rank > 5)
      rank = 5;
    
    this.rank = rank;
  }

  /**
   * Gets the local ranking of this <code>POI</code>.
   * 
   * @return the user's ranking of this <code>POI</code>.
   */
  public double getMyRank(){

    return myRank;
  }
  
  /**
   * Sets the local ranking for this <code>POI</code>.
   * 
   * @param myRank the user's ranking of this <code>POI</code>.
   */
  public void setMyRank(double myRank){

    this.myRank = myRank;
  }

  /**
   * Gets the name of this <code>POI</code>.
   * 
   * @return the name of this <code>POI</code>.
   */
  public String getName(){

    return name;
  }

  /**
   * Sets the name of this <code>POI</code>.
   * 
   * @param name the name this <code>POI</code>.
   */
  public void setName(String name){

    this.name = name;
  }

  /**
   * Gets the description of this <code>POI</code>.
   * 
   * @return the description of this <code>POI</code>.
   */
  public String getDescription(){

    return description;
  }

  /**
   * Sets the description of this <code>POI</code>.
   * 
   * @param description the description.
   */
  public void setDescription(String description){

    this.description = description;
  }

  /**
   * Gets the address of this <code>POI</code>
   * 
   * @return the address of this <code>POI</code>.
   */
  public String getAddress(){

    return address;
  }

  /**
   * Sets the address of this <code>POI</code>.
   * 
   * @param address the address of this <code>POI</code>.
   */
  public void setAddress(String address){

    this.address = address;
  }

  /**
   * Return the location of this <code>POI</code>.
   * 
   * @return a GeoPoints with the location of this <code>POI</code>.
   */
  public GeoPoint getLocation(){

    return new GeoPoint((int) (latitude * 1E6), (int) (longitude * 1E6));
  }

  /**
   * Gets the <code>List</code> of tags of this <code>POI</code>.
   * 
   * @return the <code>List</code> of tags of this <code>POI</code>.
   */
  public List<String> getTags(){

    return tags;
  }

  /**
   * Sets the <code>List</code> of tags of this <code>POI</code>.
   * 
   * @param tags a <code>List</code> of <code>String</code>.
   */
  public void setTags(List<String> tags){

    this.tags = tags;
  }
  
  /**
   * Checks if this <code>POI</code> has the given tag.
   * 
   * @param tag the wanted tag
   * @return <code>true</code> if this <code>POI</code> has the given tag, <code>false</code> otherwise.
   */
  public boolean hasTag(String tag){
    
    return tags.contains(tag);
  }
  
  /**
   * Adds a tag in the tag <code>List</code>.
   * 
   * @param tag the tag to add.
   */
  public void addTag(String tag){
    
    tags.add(tag);
  }
  
  /**
   * Gets the latitude of this <code>POI</code>.
   * 
   * @return the latitude of this <code>POI</code> in degrees.
   */
  public double getLatitude(){

    return latitude;
  }

  /**
   * Sets the latitude of this <code>POI</code>.
   * 
   * @param latitude the latitude of this <code>POI</code> in degrees.
   */
  public void setLatitude(double latitude){

    this.latitude = latitude;
  }

  /**
   * Gets the longitude of this <code>POI</code>.
   * 
   * @return the longitude of this <code>POI</code> in degrees.
   */
  public double getLongitude(){

    return longitude;
  }

  /**
   * Sets the longitude of this <code>POI</code>.
   * 
   * @param longitude the longitude of this <code>POI</code> in degrees.
   */
  public void setLongitude(double longitude){

    this.longitude = longitude;
  }
  
  @Override
  public boolean equals(Object o){
    
    if(o != null && o instanceof POI)
      return this.id == ((POI) o).getId();
    else
      return false;
  }

  @Override
  public int describeContents(){

    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags){

    dest.writeString(name);
    dest.writeString(address);
    dest.writeString(description);
    dest.writeInt(id);
    dest.writeInt(duration);
    dest.writeInt(votes);
    dest.writeDouble(price);
    dest.writeDouble(rank);
    dest.writeDouble(myRank);
    dest.writeDouble(latitude);
    dest.writeDouble(longitude);
    dest.writeList(tags);
  } 
  
  public static final Parcelable.Creator<POI> CREATOR = new Parcelable.Creator<POI>(){

    @Override
    public POI createFromParcel(Parcel source){

      return new POI(source);
    }

    @Override
    public POI[] newArray(int size){

      return new POI[size];
    }
    
  };
}