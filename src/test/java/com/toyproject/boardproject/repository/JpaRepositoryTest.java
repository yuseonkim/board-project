package com.toyproject.boardproject.repository;

import com.toyproject.boardproject.config.JpaConfig;
import com.toyproject.boardproject.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Jpa 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;
    public JpaRepositoryTest(
        @Autowired ArticleRepository articleRepository,
        @Autowired ArticleCommentRepository articleCommentRepository
    ){
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorksFine(){
        // Given
        List<Article> articles = articleRepository.findAll();

        // When
        assertThat(articles)
                .isNotNull()
                .hasSize(30);

        articleRepository.findAll().forEach(System.out::println);
        // Then
    }

    @DisplayName("insert 테스트")
    @Test
    void givenTestData_whenInserting_thenWorksFine(){
        // Given
        Long count = articleRepository.count();
        Article article = Article.of("new article","abc","#spring");
        articleRepository.save(article);

        // When
        assertThat(articleRepository.count()).isEqualTo(count+1);
        articleRepository.findAll().forEach(System.out::println);
        // Then
    }

    @DisplayName("update 테스트")
    @Test
    void givenTestData_whenUpdating_thenWorksFine(){
        // Given
        Article article = articleRepository.findById(1L).orElseThrow();
        String updateHashtag =  "#springBoot";
        article.setHashtag(updateHashtag);


        // When
        Article savedArticle = articleRepository.saveAndFlush(article);

        assertThat(article.getHashtag()).isEqualTo("#springBoot");
        articleRepository.findAll().forEach(System.out::println);

        // Then
    }

    @DisplayName("delete 테스트")
    @Test
    void givenTestData_WhenDeleting_thenWorksFine(){
        // Given
        Long count = articleRepository.count();
        articleRepository.deleteById(1L);

        // When
        assertThat(articleRepository.count()+1).isEqualTo(count);

        // Then
    }
}
