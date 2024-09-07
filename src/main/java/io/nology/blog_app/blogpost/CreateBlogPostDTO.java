package io.nology.blog_app.blogpost;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public class CreateBlogPostDTO {
    @NotBlank
    @Length(min = 5)
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String category;

    @Override
    public String toString() {
        return "CreateBlogPostDTO [title=" + title + ", content=" + content + ",category=" + category + "]";
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCategory() {
        return category;
    }

}
