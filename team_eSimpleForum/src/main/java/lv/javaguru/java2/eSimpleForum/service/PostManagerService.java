package lv.javaguru.java2.eSimpleForum.service;

import lv.javaguru.java2.eSimpleForum.Post;
import lv.javaguru.java2.eSimpleForum.database.Database;

import java.util.List;

public class PostManagerService {

    private Database database;

    public PostManagerService(Database database) {
        this.database =  database;
    }

    public void addPost(String postTitle,String postText) {
        this.database.save(new Post(postTitle, postText));
    }

    public void removePost(Long postId) {
        this.database.deleteById(postId);
    }

    public List<Post> getAllPosts() {
         return this.database.getAllPosts();
    }


}
