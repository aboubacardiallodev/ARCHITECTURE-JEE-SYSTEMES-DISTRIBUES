package net.aboubacar.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Medecin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String email;
    private String specialite;

    @OneToMany(mappedBy = "medecin", fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    @Override
    public String toString() {
        return "Medecin{id=" + id + ", nom='" + nom + "', specialite='" + specialite + "'}";
    }

}
