package vn.iotstar.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Categories")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CategoryId")
	private int categoryid;
	
	@Column(name="CategoryName", columnDefinition = "nvarchar(255) not null")
	private String categoryname;
	
	@Column(name="Images", columnDefinition="nvarchar(500) null")
	private String images;
	
	//de nguyen bien trung vs ten truong trong database
	private int status;
	
	// bi-directional many-to-one association to Video


	 @OneToMany(mappedBy = "category")
	 @ToString.Exclude
	 private List<Video> videos;
	 public Video addVideo(Video video) {
		 getVideos().add(video);
		 video.setCategory(this);
		 return video;
	 }
	 public Video removeVideo(Video video) {
		 getVideos().remove(video);
		 video.setCategory(null);
		 return video;
	 }

}
