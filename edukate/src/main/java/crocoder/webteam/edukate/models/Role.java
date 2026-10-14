package crocoder.webteam.edukate.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
	@Entity
	@Table(name="roles")
	public class Role {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	@Column(name="ROLE_ID")
	private Long id;
	private String name;
	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
	private List<User> users = new ArrayList<>();
	public Role() {
		
	}
	public Role(Long id, String name) {
		
		this.id = id;
		this.name = name;
	}
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void affecterUser(User user) {
		users.add(user);
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}


}
