package hr.betaware.fundfinder.article.resource;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import hr.betaware.fundfinder.jpa.domain.Article.ArticleStatus;
import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
public class ArticleResource {

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("title")
	private String title;

	@JsonProperty("text")
	private String text;

	@JsonProperty("status")
	private ArticleStatus status;

	@JsonProperty("creationDate")
	private Date creationDate;

	@JsonProperty("createdBy")
	private String createdBy;

	@JsonProperty("lastModifiedDate")
	private Date lastModifiedDate;

	@JsonProperty("lastModifiedBy")
	private String lastModifiedBy;

}
