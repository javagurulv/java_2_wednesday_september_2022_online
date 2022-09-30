package lv.javaguru.java2.esimpleforum.responses;

import lv.javaguru.java2.esimpleforum.domain.Post;

import java.util.List;

public class GetAllPostsResponse {

    private List<Post> posts;

    public GetAllPostsResponse(List<Post> posts) {
        this.posts = posts;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
