package lv.javaguru.java2.esimpleforum.responses;

import java.util.List;

public class RemovePostResponse extends CoreResponse{


    private boolean postRemoved;

    public RemovePostResponse(List<CoreError> errors) {
        super(errors);
    }

    public RemovePostResponse(boolean bookRemoved) {
        this.postRemoved = bookRemoved;
    }

    public boolean isPostRemoved() {
        return postRemoved;
    }

}
