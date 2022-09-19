package lv.javaguru.java2.eSimpleForum.console_ui;

import lv.javaguru.java2.eSimpleForum.Post;
import lv.javaguru.java2.eSimpleForum.database.Database;
import lv.javaguru.java2.eSimpleForum.service.PostManagerService;

import java.util.Scanner;

public class AddPostUIAction implements UIAction{


    PostManagerService postManagerService;

    public AddPostUIAction(PostManagerService postManagerService) {
        this.postManagerService = postManagerService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter post title: ");
        String postTitle = scanner.nextLine();
        System.out.println("Enter post text: ");
        String postText = scanner.nextLine();
        postManagerService.addPost(postTitle, postText);
        System.out.println("Your post was added to forum.");
    }
}
