package hr.betaware.fundfinder.article.resource;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Component;

import hr.betaware.fundfinder.jpa.domain.Article;

@Component
public class ArticleResourceAssembler {

	public ArticleResource toResource(Article entity) {
		ArticleResource resource = new ArticleResource();
		resource.setId(entity.getId());
		resource.setTitle(entity.getTitle());
		resource.setText(StringEscapeUtils.unescapeHtml4(entity.getText()));
		resource.setActive(entity.getActive());
		resource.setCreationDate(entity.getCreationDate());
		resource.setCreatedBy(entity.getCreatedBy());
		resource.setLastModifiedDate(entity.getLastModifiedDate());
		resource.setLastModifiedBy(entity.getLastModifiedBy());
		return resource;
	}

	public Article createEntity(ArticleResource resource) {
		Article entity = new Article();
		entity.setTitle(resource.getTitle());
		entity.setText(StringEscapeUtils.escapeHtml4(resource.getText()));
		entity.setActive(resource.getActive());
		return entity;
	}

	public Article updateEntity(Article entity, ArticleResource resource) {
		entity.setTitle(resource.getTitle());
		entity.setText(StringEscapeUtils.escapeHtml4(resource.getText()));
		entity.setActive(resource.getActive());
		return entity;
	}

}
