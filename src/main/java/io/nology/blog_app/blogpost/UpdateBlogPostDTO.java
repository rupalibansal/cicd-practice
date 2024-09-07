package io.nology.blog_app.blogpost;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Pattern;

public class UpdateBlogPostDTO {

    // This pattern checks that the string contains at least one non white-space
    // character
    // Provides the errror message that will be shown if the string does not match
    // the regular expression

    @Pattern(regexp = ".*\\S.*", message = "Title cannot be empty")
    @Length(min = 5)
    private String title;

    @Pattern(regexp = ".*\\S.*", message = "Content cannot be empty")
    private String content;

    @Pattern(regexp = ".*\\S.*", message = "Category cannot be empty")
    private String category;

    public String getTitle() { 
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "CreateBlogPostDTO [title=" + title + ", content=" + content + ",category=" + category + "]";
    }

}
