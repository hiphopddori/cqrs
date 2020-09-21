package ddori.spring.domain.common.code.lesson;


import ddori.spring.domain.common.code.enumMapper.EnumMapperType;

/**
 * 카테고리
 */
public enum Category implements EnumMapperType {
    BACKSWING("백스윙"),
    FULLSWING("풀스윙"),
    IRONSHOT("아이언샷"),
    SHORTGAME("숏게임");

    private String title;

    Category(String title) {
        this.title = title;
    }

    @Override
    public String getCode() { return name();}
    @Override
    public String getTitle() {
        return title;
    }
}
