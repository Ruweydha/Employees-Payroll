package com.example.payroll2.Api;

import com.example.payroll2.Dto.PostsCreate;
import com.example.payroll2.Entities.Posts;
import com.example.payroll2.Services.PostsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@Tag(name = "Posts", description = "Create, update, delete, Read")

public class PostsController {
    private final PostsService postsService;

    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @GetMapping
    public ResponseEntity<List<Posts>>  getAllPosts(){
        var posts = postsService.getAllPosts();
        return ResponseEntity.ok(posts);
    }
    @PostMapping
    public ResponseEntity<Posts> createNewPost(@RequestBody PostsCreate postsCreate){
        var newPost = postsService.createNewPost(postsCreate);
        return ResponseEntity.ok(newPost);
    }
}
