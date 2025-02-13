package co.com.companywf.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
}
