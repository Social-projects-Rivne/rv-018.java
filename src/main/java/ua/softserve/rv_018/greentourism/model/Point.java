package ua.softserve.rv_018.greentourism.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "point")
public class Point {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "langtitude")
	private float langtitude;
	@Column(name = "longtitude")
	private float longtitude;

	public Point(Long id, float langtitude, float longtitude) {
		this.id = id;
		this.langtitude = langtitude;
		this.longtitude = longtitude;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	}

	@Override
	public String toString() {
		return "Point [id=" + id + ", langtitude=" + langtitude
				+ ", longtitude=" + longtitude + "]";
	}

}
