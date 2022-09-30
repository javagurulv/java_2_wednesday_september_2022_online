package lv.javaguru.java2.esimpleforum.service;

import lv.javaguru.java2.esimpleforum.database.Database;
import lv.javaguru.java2.esimpleforum.domain.Post;
import lv.javaguru.java2.esimpleforum.requests.AddPostRequest;
import lv.javaguru.java2.esimpleforum.requests.GetAllPostsRequest;
import lv.javaguru.java2.esimpleforum.requests.RemovePostRequest;
import lv.javaguru.java2.esimpleforum.responses.AddPostResponse;
import lv.javaguru.java2.esimpleforum.responses.CoreError;
import lv.javaguru.java2.esimpleforum.responses.GetAllPostsResponse;
import lv.javaguru.java2.esimpleforum.responses.RemovePostResponse;

import java.util.List;

public class PostManagerService {

    private Database database;
    private PostValidator validator;

    public PostManagerService(Database database, PostValidator validator) {
        this.database =  database;
        this.validator = validator;
    }

    public AddPostResponse addPost(AddPostRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddPostResponse(errors);
        }

        Post post = new Post(request.getTitle(), request.getText());
        database.save(post);
        return new AddPostResponse(post);
    }

    public RemovePostResponse removePost(RemovePostRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new RemovePostResponse(errors);
        }
        boolean isPostRemoved = this.database.deleteById(request.getPostIdToRemove());

        return new RemovePostResponse(isPostRemoved);
    }

    public GetAllPostsResponse getAllPosts(GetAllPostsRequest request) {
         return new GetAllPostsResponse(this.database.getAllPosts());
    }


}
