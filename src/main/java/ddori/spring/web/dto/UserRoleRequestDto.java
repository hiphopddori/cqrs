package ddori.spring.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRoleRequestDto {
    private String role;
    @Builder
    public UserRoleRequestDto(String role) {
        this.role = role;
    }
}
