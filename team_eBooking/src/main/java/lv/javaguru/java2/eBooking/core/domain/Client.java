package lv.javaguru.java2.eBooking.core.domain;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity(name = "Clients")
@Table(
        name = "clients",
        uniqueConstraints = {
                @UniqueConstraint(name = "client_email_unique", columnNames = "client_email")
})
public class Client {
    @Id
    @SequenceGenerator(
            name = "client_sequence",
            sequenceName = "client_sequence",
            allocationSize = 1
    )
    @GeneratedValue (
            strategy = GenerationType.SEQUENCE,
            generator = "client_sequence"
    )

    @Column (
            name = "id",
            nullable = false
    )
    private Long id;

    @Column(
            name = "client_email",
            nullable = false,
            columnDefinition = "VARCHAR(50)"
    )
    private String clientEmail;

    @Column (
            name = "client_phone_number",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String clientPhoneNumber;

    public Client(String clientEmail, String clientPhoneNumber) {
        this.clientEmail = clientEmail;
        this.clientPhoneNumber = clientPhoneNumber;
    }
}
