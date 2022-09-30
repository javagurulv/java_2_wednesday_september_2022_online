package lv.javaguru.java2.esimpleforum.console_ui;

import lv.javaguru.java2.esimpleforum.domain.Post;
import lv.javaguru.java2.esimpleforum.requests.GetAllPostsRequest;
import lv.javaguru.java2.esimpleforum.responses.GetAllPostsResponse;
import lv.javaguru.java2.esimpleforum.service.PostManagerService;

public class GetAllPostsUIAction implements UIAction{
    PostManagerService postManagerService;

    public GetAllPostsUIAction(PostManagerService postManagerService) {
        this.postManagerService = postManagerService;
    }

    public void execute() {
        System.out.println("Posts list: ");
        GetAllPostsResponse response = this.postManagerService.getAllPosts(
                new GetAllPostsRequest());

        for (Post post : response.getPosts()
        ) {
            System.out.println(post);
        }

        System.out.println("Posts list end.");
    }
}
