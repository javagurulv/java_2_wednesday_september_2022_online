package lv.javaguru.java2.esimpleforum.requests;

public class RemovePostRequest {
    private Long postIdToRemove;

    public RemovePostRequest(Long postIdToRemove) {
        this.postIdToRemove = postIdToRemove;
    }

    public Long getPostIdToRemove() {
        return postIdToRemove;
    }
}
