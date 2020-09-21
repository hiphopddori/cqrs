package ddori.spring.domain.common.code.enumMapper;

import java.util.List;

public interface EnumMapperGroupType<T> {
    String getCode();
    String getTitle();
    List<T> getGroup();
}
