package wots.blog.server.domain.dto;

import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class LoginRequest {
    public String userId;

    public String userPw;
}
