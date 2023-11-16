package christmas.model.order.enums;

public enum Category {

    APPETIZER("에피타이저"),
    MAIN_COURSE("메인"),
    DESSERT("디저트"),
    BEVERAGE("음료");

    private final String category;

    Category(String category) {
        this.category = category;
    }
}
