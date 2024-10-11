package vn.iotstar.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Users")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="UserId")
	private int userid;
	
	@Column(name="Name", columnDefinition = "nvarchar(255) not null")
	private String name;
	
	@Column(name="UserName", columnDefinition = "nvarchar(255) not null")
	private String username;
	
	@Column(name="Password", columnDefinition = "nvarchar(255) not null")
	private String password;
	
	@Column(name="Images", columnDefinition="nvarchar(500) null")
	private String images;
	
	//de nguyen bien trung vs ten truong trong database
	private int status;
}
