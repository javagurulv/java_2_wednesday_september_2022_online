package lv.javaguru.java2.eSimpleForum.console_ui;

import lv.javaguru.java2.eSimpleForum.Post;
import lv.javaguru.java2.eSimpleForum.service.PostManagerService;

public class GetAllPostsUiAction implements UIAction{
    PostManagerService postManagerService;

    public GetAllPostsUiAction(PostManagerService postManagerService) {
        this.postManagerService = postManagerService;
    }

    public void execute() {
        System.out.println("Posts list: ");
        for (Post post : this.postManagerService.getAllPosts()) {
            System.out.println(post);
        }

        System.out.println("Posts list end.");
    }
}
