package net.aboubacar.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateConsultation;
    private String rapport;

    @OneToOne
    private Appointment appointment;

    @Override
    public String toString() {
        return "Consultation{id=" + id + ", rapport='" + rapport + "'}";
    }

}
