package be.ac.umons.gl.mobilecityguide.poi;

import com.google.android.maps.GeoPoint;

/**
 * A <code>POI</code> is a place to visit.
 * 
 * @author Allard Hugo & Quentin Loos
 */
public class POI extends GeoPoint {

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

  /** The name of this <code>POI</code>. */
  private String name;

  /** The address of this <code>POI</code>. */
  private String address;

  /** The description of this <code>POI</code>. */
  private String description;

  /** The tag of this <code>POI</code>. */
  private String tag;

  /** Is we have already visite this <code>POI</code> or not */
  private boolean visited;

  /**
   * Constructs a new instance of <code>POI</code> with the given ID and
   * location.
   * 
   * @param id
   *          the id of this <code>POI</code>.
   * @param latitude
   *          the latitude (in degrees) of this <code>POI</code>.
   * @param longitude
   *          the longitude (in degrees) of this <code>POI</code>.
   */
  public POI(int id, double latitude, double longitude) {
    super((int) (latitude * 1E6), (int) (longitude * 1E6));
    this.id = id;
  }

  /**
   * Gets the unique ID for this <code>POI</code>.
   * 
   * @return the Id of this <code>POI</code>.
   */
  public int getId() {

    return id;
  }

  /**
   * Sets the Id of this <code>POI</code>.
   * 
   * @param id
   *          Id the ID in the database.
   */
  public void setId(int id) {

    this.id = id;
  }

  /**
   * Gets the duration for this <code>POI</code>.
   * 
   * @return the duration in minutes.
   */
  public int getDuration() {

    return duration;
  }

  /**
   * Sets the duration of this <code>POI</code>.
   * 
   * @param duration
   *          the duration in minutes.
   */
  public void setDuration(int duration) {

    this.duration = duration;
  }

  /**
   * Gets the total number of votes for this <code>POI</code>.
   * 
   * @return the number of votes.
   */
  public int getVotes() {

    return votes;
  }

  /**
   * Sets the total number of votes of this <code>POI</code>.
   * 
   * @param votes
   *          the number of votes.
   */
  public void setVotes(int votes) {

    this.votes = votes;
  }

  /**
   * Gets the price for this <code>POI</code> in euros.
   * 
   * @return the price in euros.
   */
  public double getPrice() {

    return price;
  }

  /**
   * Sets the price of this <code>POI</code>.
   * 
   * @param price
   *          the price in euros.
   */
  public void setPrice(double price) {

    this.price = price;
  }

  /**
   * Gets the global ranking of this <code>POI</code>.<br>
   * 
   * @return the global ranking.
   */
  public double getRank() {

    return rank;
  }

  /**
   * Sets the global ranking of this <code>POI</code>.
   * 
   * @param rank
   *          the global ranking.
   */
  public void setRank(double rank) {

    if (rank < 0)
      rank = 0;
    else if (rank > 5)
      rank = 5;

    this.rank = rank;
  }

  /**
   * Gets the name of this <code>POI</code>.
   * 
   * @return the name of this <code>POI</code>.
   */
  public String getName() {

    return name;
  }

  /**
   * Sets the name of this <code>POI</code>.
   * 
   * @param name
   *          the name this <code>POI</code>.
   */
  public void setName(String name) {

    this.name = name;
  }

  /**
   * Gets the description of this <code>POI</code>.
   * 
   * @return the description of this <code>POI</code>.
   */
  public String getDescription() {

    return description;
  }

  /**
   * Sets the description of this <code>POI</code>.
   * 
   * @param description
   *          the description.
   */
  public void setDescription(String description) {

    this.description = description;
  }

  /**
   * Gets the address of this <code>POI</code>
   * 
   * @return the address of this <code>POI</code>.
   */
  public String getAddress() {

    return address;
  }

  /**
   * Sets the address of this <code>POI</code>.
   * 
   * @param address
   *          the address of this <code>POI</code>.
   */
  public void setAddress(String address) {

    this.address = address;
  }

  /**
   * Gets the <code>List</code> of tags of this <code>POI</code>.
   * 
   * @return the <code>List</code> of tags of this <code>POI</code>.
   */
  public String getTag() {

    return tag;
  }

  /**
   * Sets the <code>List</code> of tags of this <code>POI</code>.
   * 
   * @param tag
   *          a tag
   */
  public void setTag(String tag) {

    this.tag = tag;
  }

  /**
   * Gets the latitude of this <code>POI</code>.
   * 
   * @return the latitude of this <code>POI</code> in degrees.
   */
  public double getLatitude() {

    return this.getLatitudeE6() / 1E6;
  }

  /**
   * Gets the longitude of this <code>POI</code>.
   * 
   * @return the longitude of this <code>POI</code> in degrees.
   */
  public double getLongitude() {

    return this.getLongitudeE6() / 1E6;
  }

  /**
   * Gets if we have already visited this <code>POI</code>.
   * 
   * @return visited
   */
  public boolean isVisited() {
    return visited;
  }

  /**
   * Sets if we have already visited this <code>POI</code>.
   * 
   * @param visited
   */
  public void setVisited(boolean visited) {
    this.visited = visited;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals()
   */
  @Override
  public boolean equals(Object o) {

    if (o != null && o instanceof POI)
      return this.id == ((POI) o).getId();
    else
      return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return name;
  }
}