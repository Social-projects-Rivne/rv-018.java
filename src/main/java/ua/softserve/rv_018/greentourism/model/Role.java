package ua.softserve.rv_018.greentourism.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "user_role")
public class Role implements GrantedAuthority {
	
	private static final long serialVersionUID = 5894686183915569602L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(name = "name", nullable = false, columnDefinition = "varchar(255) default 'ROLE_USER'")
    private String name;
	
    public Role() {}

	public Role(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return null;
	}
}