package lv.javaguru.java2.esimpleforum.database;

import lv.javaguru.java2.esimpleforum.domain.Post;

import java.util.List;

public interface Database {

    void save(Post post);

    boolean deleteById(Long id);

    List<Post> getAllPosts();
}
