package com.blog.Repo;

import com.blog.Entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepo extends JpaRepository<Blog, Integer> {
    List<Blog> findByUserId(int userId);
}
