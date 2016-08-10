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
	private int id;
	
	@Column(name = "langtitude", nullable = false)
	private String langtitude;
	
	@Column(name = "longtitude", nullable = false)
	private String longtitude;

	public Point() { }

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
		return "Point [id=" + id + 
				", langtitude=" + langtitude
				+ ", longtitude=" + longtitude + "]";
	}

}
