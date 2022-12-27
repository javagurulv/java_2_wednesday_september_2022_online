package myApp.core.responses;

import lombok.Getter;

import java.util.List;

@Getter
public class DeleteUserResponses extends CoreResponse {

    private boolean isDeleted;

    public DeleteUserResponses(List<CoreError> errors) {
        super(errors);
    }

    public DeleteUserResponses(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
