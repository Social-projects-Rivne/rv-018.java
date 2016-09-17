package ua.softserve.rv_018.greentourism.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "gallery")
public class Gallery {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "item_id", insertable = false, updatable = false)
	private Place place;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "item_id", insertable = false, updatable = false)
	private Event event;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "attach_id")
	private Attachment attachment;
	
	@Column(name = "table_type")
	private String tableType;

	public Gallery() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Override
	public String toString() {
		return "Gallery [id=" + id
				+ ", place=" + place
				+ ", event=" + event
				+ ", attachment=" + attachment
				+ ", tableType=" + tableType + "]";
	}
}
