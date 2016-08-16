package ua.softserve.rv_018.greentourism.model;

/**
 * A LatLng is a point in geographical coordinates: latitude and longitude.
 * @author Maks
 *
 */
public class LatLng {

	private float lat;
	private float lng;
	
	public LatLng() {};
	
	public LatLng (float lat, float lng){
		this.lat = lat;
		this.lng = lng;
	};
	
	public float getLat(){
		return lat;
	};

	public void setLat(float lat){
		this.lat = lat;
	};

	public float getLng(){
		return lng;
	};

	public void setLng(float lng){
		this.lng = lng;
	};
}
