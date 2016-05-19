package hr.betaware.fundfinder.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "article")
@NoArgsConstructor @Getter @Setter @ToString
@Audited
@JsonInclude(Include.NON_NULL)
public class Article extends AbstractEntity {

	public enum ArticleStatus { ACTIVE, INACTIVE };

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "title", nullable = false, columnDefinition = "varchar(255)")
	private String title;

	@Column(name = "text", nullable = true, columnDefinition = "text")
	private String text;

	@Column(name = "status", nullable = false, columnDefinition="varchar(32)")
	@Enumerated(EnumType.STRING)
	private ArticleStatus status;

}
