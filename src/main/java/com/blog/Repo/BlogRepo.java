package com.blog.Repo;

import com.blog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepo extends JpaRepository<Blog, Integer> {
    List<Blog> findByUserId(int userId);
}
