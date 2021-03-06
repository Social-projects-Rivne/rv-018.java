package ua.softserve.rv_018.greentourism.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "point")
public class Point {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull
	@Column(name = "latitude", nullable = false)
	private float latitude;

	@NotNull
	@Column(name = "longitude", nullable = false)
	private float longitude;
	
	@Transient
	@JsonIgnore
	private boolean empty = false;

	public Point() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean isEmpty) {
		this.empty = isEmpty;
	}

	@Override
	public String toString() {
		return "Point [id=" + id + 
				", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	}
}
