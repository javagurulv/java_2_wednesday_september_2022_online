package myApp.core.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Paging {

    private Integer pageNumber;
    private Integer pageSize;
}
