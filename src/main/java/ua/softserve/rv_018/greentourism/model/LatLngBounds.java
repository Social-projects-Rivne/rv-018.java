package ua.softserve.rv_018.greentourism.model;

public class LatLngBounds {

	public LatLng southWest;
	public LatLng northEast;
	
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
		
		return (sw1.getLatitude() >= sw.getLatitude() &&
		ne1.getLatitude() <= ne.getLatitude() &&
		sw1.getLongitude() >= sw.getLongitude() &&
		ne1.getLongitude() <= ne.getLongitude());
	}
}
