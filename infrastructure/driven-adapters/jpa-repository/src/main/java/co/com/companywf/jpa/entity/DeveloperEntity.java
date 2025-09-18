package co.com.companywf.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "developer")
public class DeveloperEntity {
    @Id
    @Column(name = "developer_id")
    private String developerId;
    private String name;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist(){
        System.out.println("Fecha de creacion de registro developer");
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        System.out.println("Fecha de actualizacion de registro developer");
        this.updatedAt = LocalDateTime.now();
    }
}
