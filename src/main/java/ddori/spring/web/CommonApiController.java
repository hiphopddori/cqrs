package ddori.spring.web;

import ddori.spring.domain.common.code.enumMapper.EnumMapper;
import ddori.spring.domain.common.code.enumMapper.EnumMapperGroupValue;
import ddori.spring.domain.common.code.enumMapper.EnumMapperValue;
import ddori.spring.web.common.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RequiredArgsConstructor
@RestController
public class CommonApiController {
    private EnumMapper enumMapper;

    @Autowired
    public void getEnumMapper(EnumMapper enumMapper) {
        this.enumMapper = enumMapper;
    }

    @GetMapping("api/common/codes/{name}")
    public Response findCodesById(@PathVariable String name) {
        return enumMapper.get(name) == null ? new Response(enumMapper.getGroup(name)) :
                new Response(enumMapper.get(name));
    }
    @GetMapping("api/common/codes/view/{name}")
    public Response findCodesByView(@PathVariable String name) {
        return new Response(enumMapper.getViewGroup(name));
    }
    @GetMapping("api/common/codes/{name}/{code}")
    public Response findCodesForGroup(@PathVariable String name, @PathVariable String code) {
        return new Response(enumMapper.getGroup(name, code));
    }
}
