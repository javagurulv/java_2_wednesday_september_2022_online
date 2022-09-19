package lv.javaguru.java2.eSimpleForum.database;

import lv.javaguru.java2.eSimpleForum.Post;

import java.util.List;

public interface Database {

    void save(Post post);

    void deleteById(Long id);

    List<Post> getAllPosts();
}
