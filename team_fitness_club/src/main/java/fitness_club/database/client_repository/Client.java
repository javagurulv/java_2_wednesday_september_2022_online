package fitness_club.database.client_repository;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
class Client {
    private String name;
    private String surname;
    private int age;
    private String phoneNumber;
    private String email;
    private String gender;
}
