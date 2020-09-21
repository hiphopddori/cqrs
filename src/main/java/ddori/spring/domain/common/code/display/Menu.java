package ddori.spring.domain.common.code.display;


import ddori.spring.domain.common.code.enumMapper.EnumMapperType;

/**
 * 노출
 */
public enum Menu implements EnumMapperType {
    HOTDEAL("핫딜"),
    RECOMMEND("추천"),
    TIMESALE("타임세일");

    private String title;

    Menu(String title) {
        this.title = title;
    }

    @Override
    public String getCode() { return name();}
    @Override
    public String getTitle() {
        return title;
    }
}
