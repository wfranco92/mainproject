package co.com.companywf.model.videogame;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ResponseStatistics {
    private Integer totalGames;
    private Map<String, Integer> genders;
    private Map<String, Integer> developers;
    private Map<String, Integer> status;
    private Map<String, Integer> location;
}
