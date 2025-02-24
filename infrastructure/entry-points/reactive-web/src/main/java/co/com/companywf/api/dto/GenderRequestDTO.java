package co.com.companywf.api.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class GenderRequestDTO {
    private String name;
}
