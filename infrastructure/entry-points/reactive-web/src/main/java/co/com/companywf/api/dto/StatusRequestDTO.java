package co.com.companywf.api.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
@ToString
public class StatusRequestDTO {
    private String description;
}
