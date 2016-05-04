package hr.betaware.fundfinder.jpa.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "article")
@NoArgsConstructor @Getter @Setter @ToString
public class Article {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "title", nullable = false, columnDefinition = "varchar(255)")
	private String title;

	@Column(name = "text", nullable = true, columnDefinition = "text")
	private String text;

	@Column(name = "active", nullable = false)
	private Boolean active;

	@Column(name = "creation_date", nullable = false)
	@CreatedDate
	private Date creationDate;

	@Column(name = "created_by", nullable = true, columnDefinition = "varchar(128)")
	@CreatedBy
	private String createdBy;

	@Column(name = "last_modified_date", nullable = false)
	@LastModifiedDate
	private Date lastModifiedDate;

	@Column(name = "last_modified_by", nullable = true, columnDefinition = "varchar(128)")
	@LastModifiedBy
	private String lastModifiedBy;

}
