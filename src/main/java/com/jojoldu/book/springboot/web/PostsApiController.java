package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.service.posts.PostsService;
import com.jojoldu.book.springboot.web.dto.PostsResposeDto;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@RequiredArgsConstructor
@RestController
public class PostsApiController {


    private final PostsService postsService;

    @GetMapping("/all")
    public List<PostsResposeDto> findAll() {
        return postsService.findAll();
    }

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id,requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResposeDto findById (@PathVariable Long id) {
        return postsService.findById(id);
    }

//    @DeleteMapping("/api/vi/posts/{id}")
    @RequestMapping(method = RequestMethod.DELETE, produces = APPLICATION_JSON)
//    public void delete(@PathVariable Long id) {
    public void delete(@RequestParam Long id) {
        System.out.println("id <<<<<>>>>>>>>>>>>>= " + id);
        postsService.delete(id);
    }

}
