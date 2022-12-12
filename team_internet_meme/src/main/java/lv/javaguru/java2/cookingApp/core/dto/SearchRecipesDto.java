package lv.javaguru.java2.cookingApp.core.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SearchRecipesDto {
    List<FormView> list;

    public SearchRecipesDto(List<FormView> list) {
        this.list = list;
    }

    public void add(FormView formView) {
        list.add(formView);
    }
}
