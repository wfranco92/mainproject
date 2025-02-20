package co.com.companywf.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "state")
public class StatusEntity {
    @Id
    @Column(name = "state_id")
    private String statusId;
    private String description;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist(){
        System.out.println("Fecha de creacion de registro status");
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        System.out.println("Fecha de actualizacion de registro status");
        this.createdAt = LocalDateTime.now();
    }
}
