package io.nology.blog_app.blogpost;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.nology.blog_app.common.exceptions.NotFoundException;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("posts")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    @PostMapping
    public ResponseEntity<BlogPost> createBlogPost(@Valid @RequestBody CreateBlogPostDTO data) {
        BlogPost createdPost = this.blogPostService.createBlogPost(data);
        System.out.println(createdPost);
        return new ResponseEntity<BlogPost>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BlogPost>> findAllBlogPosts() {
        List<BlogPost> blogPosts = this.blogPostService.findAll();
        return new ResponseEntity<List<BlogPost>>(blogPosts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> findBlogPostById(@PathVariable long id) throws NotFoundException {
        Optional<BlogPost> result = this.blogPostService.findById(id);
        BlogPost foundPost = result.orElseThrow(() -> new NotFoundException("Could not find blog post with id" + id));
        return new ResponseEntity<>(foundPost, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BlogPost> updateBlogPostById(@PathVariable long id,
            @Valid @RequestBody UpdateBlogPostDTO data)
            throws NotFoundException {
        Optional<BlogPost> result = this.blogPostService.updatedBlogPostById(id, data);
        BlogPost foundPost = result.orElseThrow(() -> new NotFoundException("Could not find blog post with id" + id));
        return new ResponseEntity<>(foundPost, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BlogPost> deleteBlogPostById(@PathVariable long id) throws NotFoundException {
        boolean deleteSuccessful = this.blogPostService.deleteById(id);
        if (deleteSuccessful == false) {
            throw new NotFoundException("Could not find blog post with id" + id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
