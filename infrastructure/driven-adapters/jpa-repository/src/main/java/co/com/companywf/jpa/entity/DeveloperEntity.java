package co.com.companywf.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "developer")
public class DeveloperEntity {
    @Id
    @Column(name = "developer_id")
    private String developerId;
    private String name;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist(){
        System.out.println("Fecha de creacion de registro developer");
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        System.out.println("Fecha de actualizacion de registro developer");
        this.createdAt = LocalDateTime.now();
    }
}
