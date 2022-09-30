package lv.javaguru.java2.esimpleforum.requests;

public class AddPostRequest {
    private String title;
    private String text;

    public AddPostRequest(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

}
