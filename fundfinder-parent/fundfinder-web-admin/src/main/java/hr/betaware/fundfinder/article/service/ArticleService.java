package hr.betaware.fundfinder.article.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hr.betaware.fundfinder.article.resource.ArticleResource;
import hr.betaware.fundfinder.article.resource.ArticleResourceAssembler;
import hr.betaware.fundfinder.jpa.SearchCriteria;
import hr.betaware.fundfinder.jpa.SearchOperation;
import hr.betaware.fundfinder.jpa.domain.Article;
import hr.betaware.fundfinder.jpa.domain.Article.ArticleStatus;
import hr.betaware.fundfinder.jpa.repository.ArticleRepository;
import hr.betaware.fundfinder.jpa.specification.ArticleSpecification;
import hr.betaware.fundfinder.service.BaseService;
import hr.betaware.fundfinder.uigrid.PageableResource;
import hr.betaware.fundfinder.uigrid.UiGridFilterResource;
import hr.betaware.fundfinder.uigrid.UiGridResource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ArticleService extends BaseService {

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

		entity.setStatus(ArticleStatus.ACTIVE);
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

		entity.setStatus(ArticleStatus.INACTIVE);
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

	@Transactional(readOnly = true)
	public PageableResource<ArticleResource> getPage(UiGridResource resource) {
		Page<Article> page = null;

		List<Specification<Article>> specifications = new ArrayList<>();
		if (resource.getFilter() != null && !resource.getFilter().isEmpty()) {
			for (UiGridFilterResource uiGridFilterResource : resource.getFilter()) {
				ArticleSpecification specification = createSpecification(uiGridFilterResource);
				if (specification != null) {
					specifications.add(specification);
				}
			}
		}

		if (!specifications.isEmpty()) {
			// filtering
			Specification<Article> specification = specifications.get(0);
			if (specifications.size() > 1) {
				for (int i=1; i<specifications.size(); i++) {
					specification = Specifications.where(specification).and(specifications.get(i));
				}
			}
			page = articleRepository.findAll(specification, createPageable(resource));
		} else {
			// no filtering
			page = articleRepository.findAll(createPageable(resource));
		}

		return new PageableResource<>(page.getTotalElements(), articleResourceAssembler.toResources(page.getContent()));
	}

	private ArticleSpecification createSpecification(UiGridFilterResource resource) {
		if (resource.getName().equalsIgnoreCase("id")) {
			return new ArticleSpecification(new SearchCriteria(resource.getName(), SearchOperation.EQUALITY, resource.getTerm()));
		} else if (resource.getName().equalsIgnoreCase("status")) {
			if (EnumUtils.isValidEnum(ArticleStatus.class, resource.getTerm().toUpperCase())) {
				return new ArticleSpecification(new SearchCriteria(resource.getName(), SearchOperation.EQUALITY, ArticleStatus.valueOf(resource.getTerm().toUpperCase())));
			} else {
				return null;
			}
		} else if (resource.getName().equalsIgnoreCase("creationDate") || resource.getName().equalsIgnoreCase("lastModifiedDate")) {
			return new ArticleSpecification(new SearchCriteria(resource.getName(), SearchOperation.BETWEEN, getDateRange(resource.getTerm())));
		} else {
			return new ArticleSpecification(new SearchCriteria(resource.getName(), SearchOperation.CONTAINS, resource.getTerm()));
		}
	}

}
