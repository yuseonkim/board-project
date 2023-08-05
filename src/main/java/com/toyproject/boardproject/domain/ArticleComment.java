package com.toyproject.boardproject.domain;

import java.time.LocalDateTime;

public class ArticleComment {
    long id;

    Article article;
    private String content;

    private LocalDateTime createdAt;
    private String createdBy;

    private LocalDateTime modifiedAt;
    private String modifiedBy;
}
