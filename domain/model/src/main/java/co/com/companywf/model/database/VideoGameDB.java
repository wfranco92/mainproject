package co.com.companywf.model.database;

import co.com.companywf.model.developer.Developer;
import co.com.companywf.model.gender.Gender;
import co.com.companywf.model.location.Location;
import co.com.companywf.model.status.Status;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class VideoGameDB {
    private String id;
    private String name;
    private Gender gender;
    private Status status;
    private Developer developer;
    private Location location;
    private LocalDateTime createdAt;
}
