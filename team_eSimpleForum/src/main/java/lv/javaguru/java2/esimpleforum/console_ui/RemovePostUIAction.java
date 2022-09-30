package lv.javaguru.java2.esimpleforum.console_ui;

import lv.javaguru.java2.esimpleforum.requests.RemovePostRequest;
import lv.javaguru.java2.esimpleforum.responses.RemovePostResponse;
import lv.javaguru.java2.esimpleforum.service.PostManagerService;

import java.util.Scanner;

public class RemovePostUIAction implements UIAction{

    PostManagerService postManagerService;

    public RemovePostUIAction(PostManagerService postManagerService) {
        this.postManagerService = postManagerService;
    }

    public void execute() {
        Scanner scanner =  new Scanner(System.in);
        System.out.println("Enter post id to remove: ");
        Long postId;
        try {
            postId = Long.parseLong(scanner.nextLine());
        }catch (NumberFormatException e){
            postId = 0L;
        }
        RemovePostResponse response = postManagerService.removePost(new RemovePostRequest(postId));

        if (response.hasErrors()) {
            response.getErrors().forEach(
                    coreError -> System.out.println("Error: " +
                            coreError.getField() +
                            " " +
                            coreError.getMessage()
                    )
            );
        } else {
            if (response.isPostRemoved()) {
                System.out.println("Your post was removed from forum.");
            } else {
                System.out.println("Your post not removed from forum.");
            }
        }

    }
}
