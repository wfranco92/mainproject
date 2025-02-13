package co.com.companywf.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
}
