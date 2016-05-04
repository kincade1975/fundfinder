package hr.betaware.fundfinder.article.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping(value = { "/api/v1/article" })
public class ArticleController {

	@Autowired
	private LocaleResolver localeResolver;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private EtmService etmService;

	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ArticleResource find(@PathVariable Integer id, HttpServletRequest request) {
		EtmPoint point = etmService.createPoint("ArticleController.find");
		try {
			return articleService.find(id, localeResolver.resolveLocale(request));
		} finally {
			etmService.collect(point);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ArticleResource save(@RequestBody ArticleResource resource, HttpServletRequest request) {
		EtmPoint point = etmService.createPoint("ArticleController.save");
		try {
			return articleService.save(resource);
		} finally {
			etmService.collect(point);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value="/activate/{id}")
	public ArticleResource activate(@PathVariable Integer id, HttpServletRequest request) {
		EtmPoint point = etmService.createPoint("ArticleController.activate");
		try {
			return articleService.activate(id, localeResolver.resolveLocale(request));
		} finally {
			etmService.collect(point);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value="/deactivate/{id}")
	public ArticleResource deactivate(@PathVariable Integer id, HttpServletRequest request) {
		EtmPoint point = etmService.createPoint("ArticleController.deactivate");
		try {
			return articleService.deactivate(id, localeResolver.resolveLocale(request));
		} finally {
			etmService.collect(point);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value="/{id}")
	public void delete(@PathVariable Integer id, HttpServletRequest request) {
		EtmPoint point = etmService.createPoint("ArticleController.delete");
		try {
			articleService.delete(id, localeResolver.resolveLocale(request));
		} finally {
			etmService.collect(point);
		}
	}

}
