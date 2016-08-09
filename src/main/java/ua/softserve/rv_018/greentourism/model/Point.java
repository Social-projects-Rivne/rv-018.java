package ua.softserve.rv_018.greentourism.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "points")
public class Point {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "langtitude")
	private String langtitude;
	@Column(name = "longtitude")
	private String longtitude;

	public Point() {
	}

	public Point(Long id, String langtitude, String longtitude) {
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

	public String getLangtitude() {
		return langtitude;
	}

	public void setLangtitude(String langtitude) {
		this.langtitude = langtitude;
	}

	public String getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}

	@Override
	public String toString() {
		return "Point [id=" + id + ", langtitude=" + langtitude
				+ ", longtitude=" + longtitude + "]";
	}

}
