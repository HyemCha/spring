package tricountclone.coach.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private Long id;
    private String loginId;
    private String name;
    private String password;
}
