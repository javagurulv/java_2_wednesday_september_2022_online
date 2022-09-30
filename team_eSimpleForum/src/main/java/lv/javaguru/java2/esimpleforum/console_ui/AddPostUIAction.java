package lv.javaguru.java2.esimpleforum.console_ui;

import lv.javaguru.java2.esimpleforum.requests.AddPostRequest;
import lv.javaguru.java2.esimpleforum.responses.AddPostResponse;
import lv.javaguru.java2.esimpleforum.service.PostManagerService;

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
        AddPostResponse response = postManagerService.addPost(new AddPostRequest(postTitle, postText));

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
        } else {
            System.out.println("New post id was: " + response.getNewPost().getPostId());
            System.out.println("Your post was added to forum.");
        }
    }
}
