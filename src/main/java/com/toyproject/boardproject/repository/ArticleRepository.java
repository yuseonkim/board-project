package com.toyproject.boardproject.repository;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import com.toyproject.boardproject.domain.Article;
import com.toyproject.boardproject.domain.QArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource

public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>,
        QuerydslBinderCustomizer<QArticle>{
    @Override
    default void customize(QuerydslBindings bindings, QArticle root){
     bindings.excludeUnlistedProperties(true);
     //TODO : id 추가
     bindings.including(root.title,root.content,root.hashtag,root.createdBy,root.createdAt);
     bindings.bind(root.title).first(StringExpression::containsIgnoreCase);
     bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
     bindings.bind(root.createdAt).first(DateTimeExpression::eq);
     bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
    }
}
