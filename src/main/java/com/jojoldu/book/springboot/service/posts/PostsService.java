package com.jojoldu.book.springboot.service.posts;

import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.web.dto.PostsListResposeDto;
import com.jojoldu.book.springboot.web.dto.PostsResposeDto;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.apache.el.stream.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor        //final이 선언된 모든 필드를 인자값으로 하는 생성자 생성
public class PostsService {

    private final PostsRepository postsRepository;

    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    public List<PostsResposeDto> findAll() {
        return postsRepository.findAll().stream().map(PostsResposeDto :: new).collect(Collectors.toList());
    }


    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다. id = "+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResposeDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다. id = "+id));
        return new PostsResposeDto(entity);
    }

    @Transactional(readOnly = true)         //조회 기능만 남겨두어 조회 속도가 개선
    public List<PostsListResposeDto> findAllDesc()
    {
        return postsRepository.findAllDesc().stream().map((Posts posts) -> new PostsListResposeDto(posts)).collect(Collectors.toList());
//        return postsRepository.findAllDesc().stream().map(PostsListResposeDto :: new).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" +id));
        postsRepository.delete(posts);
    }
}
