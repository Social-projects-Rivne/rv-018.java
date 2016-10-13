package ua.softserve.rv_018.greentourism.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "event")
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id")
	private Category category;

	@NotNull
	@Column(name = "date_start")
	private Date dateStart;

	@NotNull
	@Column(name = "date_end")
	private Date dateEnd;

	@NotNull
	@Size(min=10)
	@Column(name = "description")
	private String description;
	
	@NotNull
	@Size(min=5, max=70)
	@Column(name = "name")
	private String name;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "point_id", nullable = false)
	private Point point;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "owner_id")
	private User user;

	@Transient
	private List<Attachment> attachments = new ArrayList<>();
	
	public Event() {};

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date date) {
		this.dateStart = date;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date date) {
		this.dateEnd = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	@Override
	public String toString() {
		return "Event [id=" + id
				+ ", category=" + category
				+ ", dateStart=" + dateStart
				+ ", dateEnd=" + dateEnd
				+ ", description=" + description
				+ ", name=" + name
				+ ", point=" + point
				+ ", user=" + user + "]";
	}
}
