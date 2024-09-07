package io.nology.blog_app.blogpost;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "blog_posts")
public class BlogPost {

    public BlogPost(String title, String content, Date createdAt, String category) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.category = category;
    }

    public BlogPost() {
    }

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private Long id;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    // @Column
    // @Temporal(TemporalType.TIMESTAMP)
    // private Date createdAt;

    // @Column
    // @Temporal(TemporalType.TIMESTAMP)
    // private Date updatedAt;

    @Column
    private String category;

    // public Long getId() {
    return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    // public Date getCreatedAt() {
    return createdAt;

    }

    public String getCategory() {
        return category;
    }

    // public Date getUpdatedAt() {
    return updatedAt;

    }

    // public void setId(Long id) {
    this.id=id;}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // public void setCreatedAt(Date createdAt) {
    // this.createdAt = createdAt;
    // }
 
    public void setCategory(String category) {
        this.category = category;
    }

    // public void setUpdatedAt(Date updatedAt) {
    this.updatedAt=updatedAt;

    }

    @Override
    public String toString() {
        return "BlogPost [id=" + id + ", title=" + title + ", content=" + content + ", createdAt=" + createdAt
                + ", category=" + category + "]";
    }

}
