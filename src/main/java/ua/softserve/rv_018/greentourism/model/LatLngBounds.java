package ua.softserve.rv_018.greentourism.model;

/**
 * A LatLngBounds instance represents a rectangle in geographical coordinates,
 *  including one that crosses the 180 degrees longitudinal meridian.
 * @author Максим
 *
 */
public class LatLngBounds {

	private LatLng southWest;
	private LatLng northEast;
	
	public LatLngBounds() {};
	
	public LatLngBounds (LatLng southWest, LatLng northEast){
		this.southWest = southWest;
		this.northEast = northEast;
	};
	
	public LatLng getSouthWest(){
		return southWest;
	};

	public void setSouthWest(LatLng southWest){
		this.southWest = southWest;
	};

	public LatLng getNorthEast(){
		return northEast;
	};

	public void setNorthEast(LatLng northEast){
		this.northEast = northEast;
	};

	public boolean contains(LatLng pointCoord){
		
		LatLng sw = this.southWest;
		LatLng ne = this.northEast;
		
		LatLng sw1 = pointCoord;
		LatLng ne1 = pointCoord;
		
		return (sw1.getLat() >= sw.getLat() &&
		ne1.getLat() <= ne.getLat() &&
		sw1.getLng() >= sw.getLng() &&
		ne1.getLng() <= ne.getLng());
	}
}
