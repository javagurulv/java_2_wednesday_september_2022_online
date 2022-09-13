package fitness_club.users.visitors;

import fitness_club.users.client.Client;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

//визитор или клиент который зашёл в зал или собирается посетить(встал в очередь или занял время посещения)
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Visitor {
    private boolean inGym;
    private String reservation; //тут должно быть время резервации
    private Client client;

}
