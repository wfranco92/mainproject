package co.com.companywf.api.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
public class VideoGameRequestDTO {
    private String name;
    private String gender;
    private String status;
    private String developer;
    private String location;
}
