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
@Table(name = "comment_item")
public class CommentItem {
	
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
	@JoinColumn(name = "comment_id")
	private Comment comment;
	
	@Column(name = "table_type")
	private String tableType;

	public CommentItem() {}

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

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
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
		return "CommentItem [id=" + id
				+ ", place=" + place
				+ ", event=" + event
				+ ", comment=" + comment
				+ ", tableType=" + tableType + "]";
	}
}
