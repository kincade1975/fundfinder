package hr.betaware.fundfinder.article.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hr.betaware.fundfinder.article.resource.ArticleResource;
import hr.betaware.fundfinder.article.resource.ArticleResourceAssembler;
import hr.betaware.fundfinder.jpa.domain.Article;
import hr.betaware.fundfinder.jpa.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ArticleService {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private ArticleResourceAssembler articleResourceAssembler;

	@Transactional(readOnly = true)
	public ArticleResource find(Integer id, Locale locale) {
		log.debug("Finding article [{}]...", id);

		if (id == 0) {
			return new ArticleResource();
		}

		Article entity = articleRepository.findOne(id);
		if (entity == null) {
			throw new ResourceNotFoundException(messageSource.getMessage("exception.resourceNotFound",
					new Object[] { messageSource.getMessage("resource.article", null, locale), id }, locale));
		}

		return articleResourceAssembler.toResource(entity);
	}

	@Transactional
	public ArticleResource save(ArticleResource resource) {
		log.debug("Saving article [{}]...", resource);

		Article entity = null;
		if (resource.getId() == null) {
			entity = articleResourceAssembler.createEntity(resource);
		} else {
			entity = articleRepository.findOne(resource.getId());
			entity = articleResourceAssembler.updateEntity(entity, resource);
		}

		articleRepository.save(entity);

		return articleResourceAssembler.toResource(entity);
	}

	@Transactional
	public ArticleResource activate(Integer id, Locale locale) {
		log.debug("Activating article [{}]...", id);

		Article entity = articleRepository.findOne(id);
		if (entity == null) {
			throw new ResourceNotFoundException(messageSource.getMessage("exception.resourceNotFound",
					new Object[] { messageSource.getMessage("resource.article", null, locale), id }, locale));
		}

		entity.setActive(Boolean.TRUE);
		articleRepository.save(entity);

		return articleResourceAssembler.toResource(entity);
	}

	@Transactional
	public ArticleResource deactivate(Integer id, Locale locale) {
		log.debug("Deactivating article [{}]...", id);

		Article entity = articleRepository.findOne(id);
		if (entity == null) {
			throw new ResourceNotFoundException(messageSource.getMessage("exception.resourceNotFound",
					new Object[] { messageSource.getMessage("resource.article", null, locale), id }, locale));
		}

		entity.setActive(Boolean.FALSE);
		articleRepository.save(entity);

		return articleResourceAssembler.toResource(entity);
	}

	@Transactional
	public void delete(Integer id, Locale locale) {
		log.debug("Deleting article [{}]...", id);

		Article entity = articleRepository.findOne(id);
		if (entity == null) {
			throw new ResourceNotFoundException(messageSource.getMessage("exception.resourceNotFound",
					new Object[] { messageSource.getMessage("resource.article", null, locale), id }, locale));
		}

		articleRepository.delete(entity);
	}

}
