package co.com.companywf.model.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class Role {
    private Long roleId;
    private String rolName;
}
