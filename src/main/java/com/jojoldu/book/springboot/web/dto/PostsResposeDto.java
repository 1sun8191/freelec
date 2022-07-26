package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;

@Getter
public class PostsResposeDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsResposeDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
