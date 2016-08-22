package ua.softserve.rv_018.greentourism.model;

/**
 * A LatLngBounds instance represents a rectangle in geographical coordinates,
 *  including one that crosses the 180 degrees longitudinal meridian.
 * @author Maks
 *
 */
public class MapBound {

	private Point southWest;
	private Point northEast;
	
	public MapBound() {};
	
	public Point getSouthWest(){
		return southWest;
	};

	public void setSouthWest(Point southWest){
		this.southWest = southWest;
	};

	public Point getNorthEast(){
		return northEast;
	};

	public void setNorthEast(Point northEast){
		this.northEast = northEast;
	};

	public boolean contains(Point pointCoord){
		
		float pointLatCoord = pointCoord.getLatitude();
		float pointLngCoord = pointCoord.getLongitude();
		
		return (pointLatCoord >= this.southWest.getLatitude() && 
				pointLatCoord <= this.northEast.getLatitude() && 
				pointLngCoord >= this.southWest.getLongitude() && 
				pointLngCoord <= this.northEast.getLongitude());
	}
}
