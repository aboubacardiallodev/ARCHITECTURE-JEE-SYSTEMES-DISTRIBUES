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
public class AppRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;
    private String description;

    @ManyToMany(mappedBy = "roles")
    private List<AppUser> users;

    @Override
    public String toString() {
        return "AppRole{id=" + id + ", roleName='" + roleName + "'}";
    }
}
