package ua.softserve.rv_018.greentourism.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "attachment")
public class Attachment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "file_src")
	private String fileSrc;

	public Attachment() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileSrc() {
		return fileSrc;
	}

	public void setFileSrc(String fileSrc) {
		this.fileSrc = fileSrc;
	}

	@Override
	public String toString() {
		return "Attachment [id=" + id 
				+ ", fileSrc=" + fileSrc + "]";
	}
}
