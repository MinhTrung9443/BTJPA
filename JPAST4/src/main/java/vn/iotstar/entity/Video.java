package vn.iotstar.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Videos")
@NamedQuery(name="Video.findAll", query="SELECT v FROM Video v")
public class Video implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VideoId")
	private int videoid;
	
	@Column(name = "Active")
	private boolean active;
	
	@Column(name = "Description", columnDefinition = "NVARCHAR(MAX)")
	private String description;
	
	@Column(name="Poster")
	private String poster;
	
	@Column(name="Title", columnDefinition = "NVARCHAR(MAX)")
	private String title;
	
	@Column(name="Views")
	private int views;
	 //bi-directional many-to-one association to Category
	 @ManyToOne
	 @JoinColumn(name="CategoryId")	 
	 @ToString.Exclude
	 private Category category;



}
