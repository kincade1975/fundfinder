package hr.betaware.fundfinder.article.resource;

import java.util.ArrayList;
import java.util.List;

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
		resource.setStatus(entity.getStatus());
		resource.setCreationDate(entity.getCreationDate().toDate());
		resource.setCreatedBy(entity.getCreatedBy());
		resource.setLastModifiedDate(entity.getLastModifiedDate().toDate());
		resource.setLastModifiedBy(entity.getLastModifiedBy());
		return resource;
	}

	public List<ArticleResource> toResources(List<Article> entities) {
		List<ArticleResource> resources = new ArrayList<>();
		for (Article entity : entities) {
			resources.add(toResource(entity));
		}
		return resources;
	}

	public Article createEntity(ArticleResource resource) {
		Article entity = new Article();
		entity.setTitle(resource.getTitle());
		entity.setText(StringEscapeUtils.escapeHtml4(resource.getText()));
		entity.setStatus(resource.getStatus());
		return entity;
	}

	public Article updateEntity(Article entity, ArticleResource resource) {
		entity.setTitle(resource.getTitle());
		entity.setText(StringEscapeUtils.escapeHtml4(resource.getText()));
		entity.setStatus(resource.getStatus());
		return entity;
	}

}
