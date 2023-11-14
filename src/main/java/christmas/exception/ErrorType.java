package christmas.exception;

import static christmas.utils.Constants.MAX_ORDER_QUANTITY;

public enum ErrorType {

    ERROR_PREFIX("[ERROR] "),

    INVALID_VISIT_DAY_NOT_NUMERIC("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_MENU_NAME("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_MENU_NOT_EXIST("존재하지 않은 메뉴입니다. 다시 입력해 주세요."),
    INVALID_MENU_QUANTITY_UNDER_ONE("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_MENU_QUANTITY("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_MENU_ONLY_DRINK("음료만 주문할 수 없습니다."),
    INVALID_MAX_MENU_QUANTITY(String.format("주문 수량은 최대 %d개 입니다. 다시 입력해 주세요.", MAX_ORDER_QUANTITY)),
    INVALID_DAY_OUT_OF_RANGE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    LIMIT_REFLECTION("리플렉션을 제한합니다.");

    private final String message;

    ErrorType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX.message + message;
    }
}
