package com.stackroute.controller;

import com.stackroute.domain.Blog;
import com.stackroute.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
/**
 * RestController annotation is used to create Restful web services using Spring MVC
 */

/**
 * RequestMapping annotation maps HTTP requests to handler methods
 */
@RestController
@RequestMapping("/api/v1")
public class BlogController {

    private  BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {

        this.blogService = blogService;
    }

    /**
     * save a new blog
     */


    @PostMapping("/blog")
    public ResponseEntity<Blog> saveBlog(@RequestBody Blog blog) {

            Blog savingBlog = blogService.saveBlog(blog);
            ResponseEntity<Blog> blog1 = new ResponseEntity<Blog>(savingBlog,HttpStatus.CREATED);
            return blog1;

    }

    /**
     * retrieve all blogs
     */
    @GetMapping("/blogs")
    public ResponseEntity<List<Blog>> getAllBlogs() {
        List<Blog> blog = blogService.getAllBlogs();
        return new ResponseEntity<List<Blog>>(blog,HttpStatus.OK);
//
    }

    /**
     * retrieve blog by id
     */
    @GetMapping("blog/{blogId}")
    public ResponseEntity<Blog> getBlogById(@PathVariable("blogId") int blogId) {
        try{
        Blog blog = blogService.getBlogById(blogId);

           return new ResponseEntity<>(blog,HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
//        else{
//
//
//           return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//        }
    }

    /**
     * delete blog by id
     */
    @DeleteMapping("blog/{blogId}")
    public ResponseEntity<Blog> deleteBlog(@PathVariable("blogId") int blogId) {
        Blog deletedBlog = blogService.deleteBlog(blogId);
        if (deletedBlog != null) {
            return new ResponseEntity<>(deletedBlog, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * update blog
     */
    @PutMapping("blog")
    public ResponseEntity<Blog> updateBlog(@RequestBody Blog blog) {
        Blog updatedBlog = blogService.updateBlog(blog);
        ResponseEntity<Blog> blog2=new ResponseEntity<Blog>(updatedBlog,HttpStatus.OK);
        return blog2;
    }

}