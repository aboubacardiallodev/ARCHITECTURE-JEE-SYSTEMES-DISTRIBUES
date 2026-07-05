package net.aboubacar.entities;

import jakarta.persistence.*;
import lombok.*;
import net.aboubacar.enumeration.AppointmentStatus;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Medecin medecin;

    @OneToOne(mappedBy = "appointment")
    private Consultation consultation;

    @Override
    public String toString() {
        return "RendezVous{id=" + id + ", status=" + status + "}";
    }
}
