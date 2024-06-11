package com.blog.Controller;

import com.blog.Service.BlogService;
import com.blog.DTO.BlogDto;
import jakarta.validation.Valid;
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
    public BlogDto createBlog(@Valid @RequestBody BlogDto blogDto) {
        return blogService.createBlog(blogDto);
    }

    @PutMapping("/{blogId}")
    public BlogDto updateBlog(@Valid @RequestBody BlogDto BlogDto, @PathVariable int blogId) {
        return blogService.updateBlog(BlogDto, blogId);
    }

    @GetMapping("/{blogId}")
    public BlogDto getBlogById(@Valid @PathVariable int blogId) {
        return blogService.getBlogById(blogId);
    }

    @GetMapping("/user/{blogId}")
    public List<BlogDto> getBlogByUserId(@Valid @PathVariable int blogId) {
        return blogService.getBlogByUserId(blogId);
    }
    @GetMapping("/")
    public ResponseEntity<List<BlogDto>> getAllPosts() {
        List<BlogDto> posts = blogService.getAllBlogs();
        return ResponseEntity.ok(posts);
    }
    @DeleteMapping("/{blogId}")
    public ResponseEntity<String> deletePost(@Valid @PathVariable int blogId) {
        boolean isDeleted = blogService.deleteBlog(blogId);
        if (isDeleted) {
            return ResponseEntity.ok("Blog post deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("Blog post not found.");
        }
    }
}
