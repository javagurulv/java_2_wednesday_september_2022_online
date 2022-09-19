package lv.javaguru.java2.eSimpleForum;

import java.util.Date;
import java.util.Objects;

public class Post {
    private Long postId;
    private User author;
    private Date createdAt;
    private String title;
    private String text;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Post(String title, String text) {
        this.title = title;
        this.text = text;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Post post = (Post) obj;
        return Objects.equals(title, post.title) &&
                Objects.equals(text, post.text);

    }

    @Override
    public String toString() {
        return " Post id: " + this.postId +
                " Post title: " + this.title +
                " Post text:  " + this.text;
    }
}

