package lv.javaguru.java2.esimpleforum.database;

import lv.javaguru.java2.esimpleforum.domain.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public boolean deleteById(Long id) {

        boolean PostDeleted = false;
        Optional<Post> postToDeleteOpt = posts.stream()
                .filter(post -> post.getPostId().equals(id))
                .findFirst();
        if (postToDeleteOpt.isPresent()) {
            Post bookToRemove = postToDeleteOpt.get();
            PostDeleted = posts.remove(bookToRemove);
        }
        return PostDeleted;
    }

    @Override
    public List<Post> getAllPosts() {
        return posts;
    }
}
