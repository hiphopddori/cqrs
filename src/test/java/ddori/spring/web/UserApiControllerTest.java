package ddori.spring.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import ddori.spring.command.UserRepository;
import ddori.spring.domain.user.User;
import ddori.spring.query.UserMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ddori.spring.web.dto.UserRoleRequestDto;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserApiControllerTest {
    @LocalServerPort
    private int port;
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                // .apply(springSecurity())
                .build();
    }
    @Test
    public void user_권한수정() throws Exception {
        UserRoleRequestDto requestDto = UserRoleRequestDto.builder()
                .role("TEST")
                .build();

        String url = "http://localhost:" + port + "/api/user/role/1";
        //when
        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        User user = userRepository.findById(Long.parseLong("1"))
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));
        assertThat(user.getRole()).isEqualTo("TEST");
    }
    @Test
    public void user_마이바티스조회() throws Exception {

        User user = userMapper.findById(Long.parseLong("1"))
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));
        assertThat(user.getRole()).isEqualTo("TEST");

    }
}
