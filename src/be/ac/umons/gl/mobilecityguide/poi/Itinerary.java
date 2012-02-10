package be.ac.umons.gl.mobilecityguide.poi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * An <code>Itinerary</code> is a <code>List</code> of <code>POI</code>s.
 */
public class Itinerary implements Iterable<POI> {

  /** Contains all the <code>POI</code>s for this <code>Itinerary</code>. */
  private List<POI> list;

  /**
   * Constructs a new empty instance of <code>Itinerary</code>.
   */
  public Itinerary() {

    list = new ArrayList<POI>();
  }

  public void optimize() {

    // TODO TSP
  }

  /**
   * Adds the specified <code>POI</code> to this <code>Itinerary</code>.
   * 
   * @param poi
   *          the <code>POI</code> to add.
   */
  public void add(POI poi) {

    list.add(poi);
  }

  /**
   * Empties this <code>Itinerary</code>.
   */
  public void clear() {

    list.clear();
  }

  /**
   * Searches this <code>Itinerary</code> for the specified <code>POI</code>.
   * 
   * @param poi
   *          the <code>POI</code> to search for.
   * @return <code>true</code> if the <code>POI</code> is present,
   *         <code>false</code> otherwise.
   */
  public boolean contains(POI poi) {

    return list.contains(poi);
  }

  /**
   * Removes the specified <code>POI</code> from this <code>Itinerary</code>.
   * 
   * @param poi
   *          the <code>POI</code> to remove.
   */
  public void remove(POI poi) {

    list.remove(poi);
  }

  /**
   * Returns the number of <code>POI</code>s in this <code>Itinerary</code>.
   * 
   * @return the number of <code>POI</code>s in this <code>Itinerary</code>.
   */
  public int size() {

    return list.size();
  }

  /**
   * Replaces the <code>List</code> of <code>POI</code>s.
   * 
   * @param list
   *          a <code>List</code> representing an <code>Itinerary</code>.
   */
  public void setList(List<POI> list) {

    this.list = list;
  }

  /**
   * Returns the <code>List</code> with all the <code>POI</code>s in this
   * <code>Itinerary</code>.
   * 
   * @return a <code>Set</code> with all the <code>POI</code>s in this
   *         <code>Itinerary</code>.
   */
  public List<POI> getList() {

    return list;
  }

  @Override
  public Iterator<POI> iterator() {
    return list.iterator();
  }
}