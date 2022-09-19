package lv.javaguru.java2.eSimpleForum.database;

import lv.javaguru.java2.eSimpleForum.Post;
import java.util.ArrayList;
import java.util.List;

public class InMemoryDatabaseImpl implements Database{


    private Long nextId = 1L;
    private List<Post> posts = new ArrayList<>();

    @Override
    public void save(Post post) {
        post.setPostId(nextId);
        nextId++;
        posts.add(post);
    }

    @Override
    public void deleteById(Long id) {
        posts.stream()
                .filter(post -> post.getPostId().equals(id))
                .findFirst()
                .ifPresent(post -> posts.remove(post));
    }

    @Override
    public List<Post> getAllPosts() {
        return posts;
    }
}
