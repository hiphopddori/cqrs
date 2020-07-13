package ddori.spring.web;


import ddori.spring.service.UserService;
import ddori.spring.web.dto.UserRoleRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PutMapping("api/user/role/{id}")
    public Long setRole(@PathVariable Long id, @RequestBody UserRoleRequestDto requestDto){
        return userService.role(id, requestDto);
    }
}
