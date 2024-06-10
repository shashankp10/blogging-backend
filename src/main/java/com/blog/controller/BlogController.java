package com.blog.controller;

import com.blog.Service.BlogService;
import com.blog.dto.BlogDto;
import com.blog.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/post")
    public BlogDto createBlog(@RequestBody BlogDto blogDto) {
        return blogService.createBlog(blogDto);
    }

    @PutMapping("/{blogId}")
    public BlogDto updateBlog(@RequestBody BlogDto BlogDto, @PathVariable int blogId) {
        return blogService.updateBlog(BlogDto, blogId);
    }

    @GetMapping("/{blogId}")
    public BlogDto getBlogById(@PathVariable int blogId) {
        return blogService.getBlogById(blogId);
    }

    @GetMapping("/user/{blogId}")
    public List<BlogDto> getBlogByUserId(@PathVariable int blogId) {
        return blogService.getBlogByUserId(blogId);
    }
    @GetMapping("/")
    public ResponseEntity<List<BlogDto>> getAllPosts() {
        List<BlogDto> posts = blogService.getAllBlogs();
        return ResponseEntity.ok(posts);
    }
    @DeleteMapping("/{blogId}")
    public void deletePost(@PathVariable int blogId) {
        blogService.deleteBlog(blogId);
    }
}
