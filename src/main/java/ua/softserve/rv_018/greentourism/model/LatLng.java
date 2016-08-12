package ua.softserve.rv_018.greentourism.model;

public class LatLng {

	private float lat;
	private float lng;
	
	public LatLng() {};
	
	public LatLng (float latitude, float longitude){
		this.lat = latitude;
		this.lng = longitude;
	};
	
	public float getLatitude(){
		return lat;
	};

	public void setLatitude(float latitude){
		this.lat = latitude;
	};

	public float getLongitude(){
		return lng;
	};

	public void setLongitude(float longitude){
		this.lng = longitude;
	};
}
