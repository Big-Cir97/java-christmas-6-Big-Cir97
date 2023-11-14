package christmas.utils;

import static christmas.exception.ErrorType.ERROR_PREFIX;

public abstract class Constants {

    public static final int MIN_ORDER_MENU = 1;
    public static final int MIN_ORDER_QUANTITY = 1;
    public static final int MAX_ORDER_QUANTITY = 20;
    public static final int GIVEAWAY_QUANTITY = 1;

    public static final String EMPTY = "";
    public static final String COMMA = ",";
    public static final String MINUS_OPERATOR = "-";
    public static final String BLANK = " ";
    public static final String NEW_LINE = "\n";
    public static final String AMOUNT_FORMAT = "#,###";
    public static final String AMOUNT_SUFFIX = "원";
    public static final String QUANTITY_SUFFIX = "개";


    private Constants() {
        throw new NullPointerException(ERROR_PREFIX.getMessage());
    }
}
