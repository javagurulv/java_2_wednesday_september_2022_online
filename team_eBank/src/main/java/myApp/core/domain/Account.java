package myApp.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {

    private Long id;
    private int balance;
}
