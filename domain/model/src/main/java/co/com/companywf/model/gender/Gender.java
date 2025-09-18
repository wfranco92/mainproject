package co.com.companywf.model.gender;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Gender {
    private String genderId;
    private String name;
    private LocalDateTime createdAt;
}
