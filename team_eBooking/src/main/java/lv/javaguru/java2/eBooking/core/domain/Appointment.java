package lv.javaguru.java2.eBooking.core.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity(name = "appointments")

public class Appointment {
    @Id

    @SequenceGenerator(
            name = "appointment_sequence",
            sequenceName = "appointment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "appointment_sequence"
    )

    @Column (
            name = "id",
            nullable = false
    )
    private Long id;
    @Column (
            name = "master_name",
            nullable = false
    )
    private String masterName;
    @Column (
            name = "service_type",
            nullable = false
    )
    private String typeOfService;

    public Appointment(String masterName, String typeOfService) {
        this.masterName=masterName;
        this.typeOfService = typeOfService;
    }
}
