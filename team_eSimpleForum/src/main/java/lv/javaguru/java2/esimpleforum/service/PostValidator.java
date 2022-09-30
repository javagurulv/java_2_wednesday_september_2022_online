package lv.javaguru.java2.esimpleforum.service;

import lv.javaguru.java2.esimpleforum.requests.AddPostRequest;
import lv.javaguru.java2.esimpleforum.requests.RemovePostRequest;
import lv.javaguru.java2.esimpleforum.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostValidator {
    public List<CoreError> validate(AddPostRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateTitle(request).ifPresent(errors::add);
        validateText(request).ifPresent(errors::add);
        return errors;
    }

    public List<CoreError> validate(RemovePostRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateIdToRemove(request).ifPresent(errors::add);
        return errors;
    }



    private Optional<CoreError> validateTitle(AddPostRequest request) {
        return (request.getTitle() == null || request.getTitle().isEmpty())
                ? Optional.of(new CoreError("title", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateText(AddPostRequest request) {
        return (request.getText() == null || request.getText().isEmpty())
                ? Optional.of(new CoreError("text", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateIdToRemove(RemovePostRequest request) {
        return  (request.getPostIdToRemove() == null || request.getPostIdToRemove() == 0)
            ? Optional.of(new CoreError("id", "Must be number, not empty or bigger then 0!"))
            : Optional.empty();
    }
}
