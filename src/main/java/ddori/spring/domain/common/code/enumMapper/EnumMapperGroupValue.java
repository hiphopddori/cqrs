package ddori.spring.domain.common.code.enumMapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnumMapperGroupValue<T extends EnumMapperType> {
    private String title;
    private String code;
    private List<T> group;

    public EnumMapperGroupValue(EnumMapperGroupType enumMapperGroupType) {
        title = enumMapperGroupType.getTitle();
        code = enumMapperGroupType.getCode();
        group = enumMapperGroupType.getGroup();
    }

    public String getCode() {return code;}
    public String getTitle() {return title;}
    public List<T> getGroup() {return group;}

    @Override
    public String toString() {
//        StringBuffer sb = new StringBuffer("");
//
//        for (int i = 0; i < group.size(); i++) {
//            if (i == 0)
//                sb.append("{"+code + ": [");
//            sb.append(group.get(i).toString());
//        }
//
//        if (group.size() > 0)
//            sb.append("}");
//
//        return sb.toString();
        return group.toString();
    }
}
