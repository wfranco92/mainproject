package co.com.companywf.model.videogame;

import co.com.companywf.model.database.VideoGameDB;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class VideoGameListResponse {
    private Long totalItems;
    private Integer totalPages;
    private Integer currentPage;
    private List<Videogame> videoGames;
}
