package co.com.companywf.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder(toBuilder = true)
@Table(name = "videogame")
public class VideoGameEntity {
    @Id
    @Column(name = "videogame_id")
    private String videogameId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "gender_id", nullable = false)
    private GenderEntity gender;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private StatusEntity status;

    @ManyToOne
    @JoinColumn(name = "developer_id", nullable = false)
    private DeveloperEntity developer;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private LocationEntity location;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist(){
        System.out.println("Fecha de creacion de registro videoGame");
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        System.out.println("Fecha de actualizacion de registro videoGame");
        this.updatedAt = LocalDateTime.now();
    }
}
