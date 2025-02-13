package co.com.companywf.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
}
