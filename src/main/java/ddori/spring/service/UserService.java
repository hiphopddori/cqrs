package ddori.spring.service;

import ddori.spring.command.UserRepository;
import ddori.spring.domain.user.User;
import ddori.spring.query.UserMapper;
import ddori.spring.web.dto.UserRoleRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public Long role(Long id, UserRoleRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        user.changeRole(requestDto.getRole());
        return id;
    }
}
