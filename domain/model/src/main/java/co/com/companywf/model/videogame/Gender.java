package co.com.companywf.model.videogame;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Gender {
    private String genderId;
    private String name;
    private LocalDateTime createdAt;
}
