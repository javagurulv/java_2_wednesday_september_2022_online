package lv.javaguru.java2.esimpleforum.responses;

import lv.javaguru.java2.esimpleforum.domain.Post;

import java.util.List;

public class AddPostResponse extends CoreResponse{


    private Post newPost;

    public AddPostResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddPostResponse(Post newPost) {
        this.newPost = newPost;
    }

    public Post getNewPost() {
        return newPost;
    }

}
