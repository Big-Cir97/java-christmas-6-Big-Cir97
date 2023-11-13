package christmas.utils;

public abstract class Constants {

    public static final int MIN_ORDER_MENU = 1;
    public static final int MIN_ORDER_QUANTITY = 1;
    public static final int MAX_ORDER_QUANTITY = 20;
    public static final int GIVEAWAY_AMOUNT = 120_000;
    public static final int NON_DISCOUNT = 0;

    public static final int EVENT_YEAR = 23;
    public static final int EVENT_MONTH = 12;
    public static final int FRIDAY = 5;
    public static final int SATURDAY = 6;
    public static final int SPECIAL_DISCOUNT = 1_000;

    private Constants() {
        throw new NullPointerException("[ERROR] 리플렉션을 제한합니다.");
    }
}
