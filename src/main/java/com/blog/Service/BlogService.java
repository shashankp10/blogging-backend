package com.blog.Service;

import com.blog.DTO.BlogDto;

import java.util.List;

public interface BlogService {
    BlogDto createBlog(BlogDto blogDto);
    BlogDto updateBlog(BlogDto blogDto, int postId);
    BlogDto getBlogById(int postId);
    List<BlogDto> getBlogByUserId(int userId);
    boolean deleteBlog(int postId);
    List<BlogDto> getAllBlogs();
}
