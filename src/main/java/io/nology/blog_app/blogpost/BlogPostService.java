package io.nology.blog_app.blogpost;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class BlogPostService {
    @Autowired
    private BlogPostRepository repo;

    public BlogPost createBlogPost(@Valid CreateBlogPostDTO data) {
        BlogPost newPost = new BlogPost();
        newPost.setTitle(data.getTitle().trim());
        newPost.setCategory(data.getCategory().trim().toLowerCase());
        newPost.setContent(data.getContent().trim());
        Date now = new Date();
        newPost.setCreatedAt(now);
        newPost.setUpdatedAt(now);
        return this.repo.save(newPost);
    }

    public List<BlogPost> findAll() {
        return this.repo.findAll();
    }

    public Optional<BlogPost> findById(Long id) {
        return this.repo.findById(id);

    }

    public Optional<BlogPost> updatedBlogPostById(Long id, @Valid UpdateBlogPostDTO data) {
        Optional<BlogPost> result = this.findById(id);
        if (result.isEmpty()) {
            return result;
        }
        BlogPost foundPost = result.get();

        if (data.getTitle() != null) {
            foundPost.setTitle(data.getTitle().trim());
        }

        if (data.getContent() != null) {
            foundPost.setContent(data.getContent().trim());
        }
        if (data.getCategory() != null) {
            foundPost.setCategory(data.getCategory().trim().toLowerCase());
        }

        foundPost.setUpdatedAt(new Date());
        BlogPost updatedPost = this.repo.save(foundPost);
        return Optional.of(updatedPost);

    }

    public boolean deleteById(Long id) {
        Optional<BlogPost> result = this.findById(id);
        if (result.isEmpty()) {
            return false;
        }
        this.repo.delete(result.get());
        return true;
    }
}
