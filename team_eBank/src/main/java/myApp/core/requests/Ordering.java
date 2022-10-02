package myApp.core.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Ordering {

    private String orderBy;
    private String orderDirection;
}
