package tricountclone.coach.model;

import lombok.Data;

@Data
public class SignupRequest {
    private String loginId;
    private String password;
    private String name;
}
