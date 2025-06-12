package co.com.companywf.model.user;


import co.com.companywf.model.role.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserRequest {
    private String username;
    private String password;
    private List<Role> roles;
    private boolean admin;
}
