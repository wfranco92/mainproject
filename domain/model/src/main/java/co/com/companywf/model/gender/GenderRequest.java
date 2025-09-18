package co.com.companywf.model.gender;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Setter
@ToString
@NoArgsConstructor
@Builder(toBuilder = true)
public class GenderRequest {
    private String genderId;
    private String name;
    private LocalDateTime createdAt;
}
