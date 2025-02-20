package co.com.companywf.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "location")
public class LocationEntity {

    @Id
    @Column(name = "location_id")
    private String locationId;
    private String name;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist(){
        System.out.println("Fecha de creacion de registro location");
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        System.out.println("Fecha de actualizacion de registro status");
        this.createdAt = LocalDateTime.now();
    }
}
