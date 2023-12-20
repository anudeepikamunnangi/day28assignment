package com.stackroute.service;

import com.stackroute.domain.Blog;

import com.stackroute.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Service indicates annotated class is a service which hold business logic in the Service layer
 */
@Service
public class BlogServiceImpl implements BlogService {
    private  BlogRepository blogRepository;

    /**
     * Constructor based Dependency injection to inject BlogRepository here
     */
    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository) {

        this.blogRepository = blogRepository;
    }

    /**
     * save a new blog
     */
    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    /**
     * retrieve all blogs
     */

    @Override
    public List<Blog> getAllBlogs() {

        return (List<Blog>) blogRepository.findAll();
    }

    /**
     * retrieve blog by id
     */

    @Override
    public Blog getBlogById(int id) {
        return blogRepository.findById(id).orElse(null);
    }

    /**
     * delete blog by id
     */
    @Override
    public Blog deleteBlog(int id) {
      Blog blog = blogRepository.findById(id).orElse(null);
       if (blog != null) {
            blogRepository.deleteById(id);
        }
        return blog;
    }



    /**
     * update blog
     */
    @Override
    public Blog updateBlog(Blog blog) {
        Optional<Blog> updatedBlog =blogRepository.findById(blog.getBlogId());
        if (updatedBlog.isPresent()) {
            Blog b = updatedBlog.get();
            b.setBlogTitle(blog.getBlogTitle());
            b.setAuthorName(blog.getAuthorName());
            b.setBlogContent(blog.getBlogContent());
            blogRepository.save(b);
            return b;
        }
        return null;
    }



}

