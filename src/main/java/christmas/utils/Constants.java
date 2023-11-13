package christmas.utils;

public abstract class Constants {

    public static final int MIN_ORDER_MENU = 1;
    public static final int MIN_ORDER_QUANTITY = 1;
    public static final int MAX_ORDER_QUANTITY = 20;

    private Constants() {
        throw new NullPointerException("[ERROR] 리플렉션을 제한합니다.");
    }
}
