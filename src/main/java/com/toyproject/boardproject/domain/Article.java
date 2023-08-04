package com.toyproject.boardproject.domain;


import jakarta.persistence.*;

import java.time.LocalDateTime;

public class Article {
    private Long id;
    private String title;
    private String hashtag;

    private LocalDateTime createdAt;
    private String createdBy;

    private LocalDateTime modifiedAt;
    private String modifiedBy;

}
