package christmas.utils;

public abstract class Constants {

    public static final int MIN_ORDER_MENU = 1;
    public static final int MIN_ORDER_QUANTITY = 1;
    public static final int MAX_ORDER_QUANTITY = 20;

    public static final String CHRISTMAS_DISCOUNT_RESULT = "크리스마스 디데이 할인: ";
    public static final String WEEKEND_DISCOUNT_RESULT = "주말 할인: ";
    public static final String WEEKSDAYS_DISCOUNT_RESULT = "평일 할인: ";
    public static final String SPECIAL_DISCOUNT_RESULT = "특별 할인: ";
    public static final String GIVEAWAY_DISCOUNT_RESULT = "증정 이벤트: ";
    public static final String MINUS_OPERATOR = "-";
    public static final String AMOUNT_FORMAT = "#,###";
    public static final String RESULT_SUFFIX = "원";

    public static final String COMMA = ",";

    private Constants() {
        throw new NullPointerException("[ERROR] 리플렉션을 제한합니다.");
    }
}
