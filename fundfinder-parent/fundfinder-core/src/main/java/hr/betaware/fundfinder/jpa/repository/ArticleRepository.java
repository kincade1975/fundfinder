package hr.betaware.fundfinder.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import hr.betaware.fundfinder.jpa.domain.Article;

public interface ArticleRepository extends CrudRepository<Article, Integer>, JpaSpecificationExecutor<Article> {

	Page<Article> findAll(Pageable pageable);

}
