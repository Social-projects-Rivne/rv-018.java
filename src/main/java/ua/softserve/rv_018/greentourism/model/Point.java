package ua.softserve.rv_018.greentourism.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//<<<<<<< HEAD
//@Table(name = "points")
//public class Point {
//    @Id
//    @GeneratedValue(strategy=GenerationType.AUTO)
//    private Long id;
//    @Column(name = "lon")
//    private float lon;
//    @Column(name = "lat")
//    private float lat;
//	
//    public Point() {}
//
//	public Point(Long id, float lon, float lat) {
//		super();
//		this.id = id;
//		this.lon = lon;
//		this.lat = lat;
//	}
//	
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public float getLon() {
//		return lon;
//	}
//
//	public void setLon(float lon) {
//		this.lon = lon;
//	}
//
//	public float getLat() {
//		return lat;
//	}
//
//	public void setLat(float lat) {
//		this.lat = lat;
//=======
@Table(name = "point")
public class Point {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "langtitude", nullable = false)
	private float langtitude;
	
	@Column(name = "longtitude", nullable = false)
	private float longtitude;
	
	public Point() { }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getLangtitude() {
		return langtitude;
	}

	public void setLangtitude(float langtitude) {
		this.langtitude = langtitude;
	}

	public float getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(float longtitude) {
		this.longtitude = longtitude;
//>>>>>>> 66bb7fbb0b5a95fb306a3cbf66520e134e886147
	}

	@Override
	public String toString() {
//<<<<<<< HEAD
//		return "Point [id=" + id + ", lon=" + lon + ", lat=" + lat + "]";
//	}
//=======
		return "Point [id=" + id + 
				", langtitude=" + langtitude
				+ ", longtitude=" + longtitude + "]";
	}

//>>>>>>> 66bb7fbb0b5a95fb306a3cbf66520e134e886147
}
