package hr.betaware.fundfinder.article.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

import etm.core.monitor.EtmPoint;
import hr.betaware.fundfinder.article.resource.ArticleResource;
import hr.betaware.fundfinder.article.service.ArticleService;
import hr.betaware.fundfinder.etm.EtmService;
import hr.betaware.fundfinder.jpa.domain.Article;
import hr.betaware.fundfinder.jpa.envers.RevisionResource;
import hr.betaware.fundfinder.jpa.envers.RevisionService;
import hr.betaware.fundfinder.uigrid.PageableResource;
import hr.betaware.fundfinder.uigrid.UiGridResource;

@RestController
@RequestMapping(value = { "/api/v1/articles", "/e/api/v1/articles" })
public class ArticleController {

	@Autowired
	private LocaleResolver localeResolver;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private RevisionService revisionService;

	@Autowired
	private EtmService etmService;

	@RequestMapping(method = RequestMethod.POST, value = "/page")
	public PageableResource<ArticleResource> getPage(@RequestBody UiGridResource resource) {
		EtmPoint point = etmService.createPoint("ArticleController.getPage");
		try {
			return articleService.getPage(resource);
		} finally {
			etmService.collect(point);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ArticleResource find(@AuthenticationPrincipal UserDetails user, @PathVariable Integer id, HttpServletRequest request) {
		EtmPoint point = etmService.createPoint("ArticleController.find");
		try {
			return articleService.find(id, localeResolver.resolveLocale(request));
		} finally {
			etmService.collect(point);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ArticleResource save(@AuthenticationPrincipal UserDetails user, @RequestBody ArticleResource resource, HttpServletRequest request) {
		EtmPoint point = etmService.createPoint("ArticleController.save");
		try {
			return articleService.save(resource);
		} finally {
			etmService.collect(point);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value="/{id}/activate")
	public ArticleResource activate(@AuthenticationPrincipal UserDetails user, @PathVariable Integer id, HttpServletRequest request) {
		EtmPoint point = etmService.createPoint("ArticleController.activate");
		try {
			return articleService.activate(id, localeResolver.resolveLocale(request));
		} finally {
			etmService.collect(point);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value="/{id}/deactivate")
	public ArticleResource deactivate(@AuthenticationPrincipal UserDetails user, @PathVariable Integer id, HttpServletRequest request) {
		EtmPoint point = etmService.createPoint("ArticleController.deactivate");
		try {
			return articleService.deactivate(id, localeResolver.resolveLocale(request));
		} finally {
			etmService.collect(point);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value="/{id}")
	public void delete(@AuthenticationPrincipal UserDetails user, @PathVariable Integer id, HttpServletRequest request) {
		EtmPoint point = etmService.createPoint("ArticleController.delete");
		try {
			articleService.delete(id, localeResolver.resolveLocale(request));
		} finally {
			etmService.collect(point);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value="/{id}/revisions")
	public List<RevisionResource> getRevisions(@AuthenticationPrincipal @PathVariable Integer id) {
		EtmPoint point = etmService.createPoint("ArticleController.getRevisions");
		try {
			return revisionService.getRevisions(Article.class, id);
		} finally {
			etmService.collect(point);
		}
	}

}
