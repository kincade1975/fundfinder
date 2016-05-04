package hr.betaware.fundfinder.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import hr.betaware.fundfinder.jpa.domain.Article;

public interface ArticleRepository extends CrudRepository<Article, Integer> {

}
