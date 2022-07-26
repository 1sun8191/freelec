package com.jojoldu.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity     //테이블과 링크될 클래스임을 명시
@Getter
@NoArgsConstructor      //기본 생성자
public class Posts extends BaseTimeEntity{

    @Id                 //해당 테이블의 PK
    @GeneratedValue(strategy= GenerationType.IDENTITY)          //PK생성규칙, 1씩 증가
    private Long id;

    @Column(length = 500, nullable = false)             //테이블의 컬럼, 없어도 되나, 설정 수정하고 싶을때 기본은 VARCHAR(255)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;


    /**
     *
     * @param title
     * @param content
     * @param author
     */
    @Builder                                            //생성자에 나중에 객체 생성시 해당 필드만 포함.어느필드에 어떤값을 채워야 하는지 명확하게 인지
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
