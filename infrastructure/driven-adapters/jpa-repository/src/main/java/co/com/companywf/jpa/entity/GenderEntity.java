package co.com.companywf.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "gender")
public class GenderEntity {
    @Id
    @Column(name = "gender_id")
    private String genderId;
    private String name;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist(){
        System.out.println("Fecha de creacion de registro gender");
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        System.out.println("Fecha de actualizacion de registro gender");
        this.createdAt = LocalDateTime.now();
    }
}
