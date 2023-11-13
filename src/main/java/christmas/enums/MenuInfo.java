package christmas.enums;

import java.util.Arrays;

public enum MenuInfo {

    MUSHROOM_SOUP("양송이수프", 6_000, Category.APPETIZER),
    TAPAS("타파스", 5_500, Category.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_000, Category.APPETIZER),

    T_BONE_STEAK("티본스테이크", 55_000, Category.MAIN_COURSE),
    BARBECUE_RIBS("바비큐립", 54_000, Category.MAIN_COURSE),
    SEAFOOD_PASTA("해산물파스타", 35_000, Category.MAIN_COURSE),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, Category.MAIN_COURSE),

    CHOCOLATE_CAKE("초코케이크", 15_000, Category.DESSERT),
    ICE_CREAM("아이스크림", 5_000, Category.DESSERT),

    ZERO_COLA("제로콜라", 3_000, Category.BEVERAGE),
    RED_WINE("레드와인", 60_000, Category.BEVERAGE),
    CHAMPAGNE("샴페인", 25_000, Category.BEVERAGE);

    private final String name;
    private final int price;
    private final Category category;

    MenuInfo(String name, int price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public static boolean isExistMenu(String name) {
        return Arrays.stream(MenuInfo.values())
                .map(MenuInfo::getName)
                .anyMatch(existMenu -> existMenu.equals(name));
    }

    public static Category getCategoryByMenuName(String name) {
        return Arrays.stream(MenuInfo.values())
                .filter(menuInfo -> menuInfo.getName().equals(name))
                .map(MenuInfo::getCategory)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 메뉴입니다."));
    }

    // todo : 계산 메소드 추가 고려
}
