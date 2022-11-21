package lv.javaguru.java2.cookingApp.core.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormView {
    String name;

    public FormView() {
    }

    public FormView(String name) {
        this.name = name;
    }
}
