package co.com.companywf.model.videogame;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
public class VideoGameRequest {
    private String name;
    private String gender;
    private String status;
    private String developer;
    private String location;
}
