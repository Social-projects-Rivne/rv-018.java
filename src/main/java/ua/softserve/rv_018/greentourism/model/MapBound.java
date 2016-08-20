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
	
	public MapBound(Point southWest, Point northEast){
		this.southWest = southWest;
		this.northEast = northEast;
	};
	
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
		
		Point sw = this.southWest;
		Point ne = this.northEast;
		
		Point sw1 = pointCoord;
		Point ne1 = pointCoord;
		
		return (sw1.getLangtitude() >= sw.getLangtitude() &&
		ne1.getLangtitude() <= ne.getLangtitude() &&
		sw1.getLongtitude() >= sw.getLongtitude() &&
		ne1.getLongtitude() <= ne.getLongtitude());
	}
}
