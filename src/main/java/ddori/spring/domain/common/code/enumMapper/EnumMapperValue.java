package ddori.spring.domain.common.code.enumMapper;

public class EnumMapperValue {
    private String title;
    private String code;

    public EnumMapperValue(EnumMapperType enumMapperType) {
        title = enumMapperType.getTitle();
        code = enumMapperType.getCode();
    }

    public String getCode() {return code;}
    public String getTitle() {return title;}

    @Override
    public String toString() { return String.format("{code=%s, title=%s}", code, title); }
}
