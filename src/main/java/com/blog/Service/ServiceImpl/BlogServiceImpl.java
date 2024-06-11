package com.blog.Service.ServiceImpl;

import com.blog.Repo.BlogRepo;
import com.blog.Service.BlogService;
import com.blog.DTO.BlogDto;
import com.blog.Entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepo blogRepo;

    @Override
    public BlogDto createBlog(BlogDto BlogDto) {
        Blog blog = new Blog();
        blog.setTitle(BlogDto.getTitle());
        blog.setContent(BlogDto.getContent());
        blog.setUserId(BlogDto.getUserId());
        Blog saved = blogRepo.save(blog);
        return convertBlogToDto(saved);
    }

    @Override
    public BlogDto updateBlog(BlogDto BlogDto, int blogId) {
        Optional<Blog> optionalBlog = blogRepo.findById(blogId);
        if (!optionalBlog.isPresent()) {
            return null;
        }

        Blog blog = optionalBlog.get();
        blog.setTitle(BlogDto.getTitle());
        blog.setContent(BlogDto.getContent());
        blog.setUserId(BlogDto.getUserId());
        Blog updated = blogRepo.save(blog);
        return convertBlogToDto(updated);
    }

    @Override
    public BlogDto getBlogById(int blogId) {
        Optional<Blog> optionalPost = blogRepo.findById(blogId);
        if (!optionalPost.isPresent()) {
            return null;
        }

        return convertBlogToDto(optionalPost.get());
    }

    @Override
    public List<BlogDto> getBlogByUserId(int userId) {
        return blogRepo.findByUserId(userId).stream()
                .map(this::convertBlogToDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteBlog(int postId) {
        Optional<Blog> optionalBlog = blogRepo.findById(postId);
        if (optionalBlog.isPresent()) {
            blogRepo.deleteById(postId);
            return true;
        } else {
            return false;
        }
    }
    @Override
    public List<BlogDto> getAllBlogs() {
        return blogRepo.findAll().stream()
                .map(this::convertBlogToDto)
                .collect(Collectors.toList());
    }
    private BlogDto convertBlogToDto(Blog blog) {
        BlogDto dto = new BlogDto();
        dto.setId(blog.getId());
        dto.setTitle(blog.getTitle());
        dto.setContent(blog.getContent());
        dto.setUserId(blog.getUserId());
        return dto;
    }
}
