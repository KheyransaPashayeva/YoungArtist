package az.atl.youngartist.model.reguest;


import az.atl.youngartist.model.enums.Role;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String email;
    private String username;
    private String password;
    private Role userRole;

}
