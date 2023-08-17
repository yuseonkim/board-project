package com.toyproject.boardproject.repository;

import com.toyproject.boardproject.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource

public interface ArticleRepository extends JpaRepository<Article,Long> {

}
