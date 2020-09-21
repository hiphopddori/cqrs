package ddori.spring.domain.common.code.lesson;


import ddori.spring.domain.common.code.enumMapper.EnumMapperType;

/**
 * 계정 타입
 */
public enum AccountType implements EnumMapperType {
    GENERAL("일반"),
    LESSON("레슨프로"),
    MASTER("마스터");

    private String title;

    AccountType(String title) {
        this.title = title;
    }

    @Override
    public String getCode() { return name();}
    @Override
    public String getTitle() {
        return title;
    }
}
