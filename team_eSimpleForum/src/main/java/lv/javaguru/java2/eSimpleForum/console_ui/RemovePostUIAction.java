package lv.javaguru.java2.eSimpleForum.console_ui;

import lv.javaguru.java2.eSimpleForum.Post;
import lv.javaguru.java2.eSimpleForum.service.PostManagerService;

import java.util.Scanner;

public class RemovePostUIAction implements UIAction{

    PostManagerService postManagerService;

    public RemovePostUIAction(PostManagerService postManagerService) {
        this.postManagerService = postManagerService;
    }

    public void execute() {
        Scanner scanner =  new Scanner(System.in);
        System.out.println("Enter post id to remove: ");
        Long postId = Long.parseLong(scanner.nextLine());
        postManagerService.removePost(postId);
        System.out.println("Your post was removed from forum.");
    }
}
