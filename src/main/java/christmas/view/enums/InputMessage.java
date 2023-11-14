package christmas.view.enums;

import static christmas.model.calendar.enums.CalendarDate.EVENT_MONTH;

import christmas.model.calendar.enums.CalendarDate;

public enum InputMessage {

    INPUT_START_MESSAGE(String.format("안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.", EVENT_MONTH.getNumber())),
    INPUT_VISIT_DAY_MESSAGE(String.format("%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해주세요!)", EVENT_MONTH.getNumber())),
    INPUT_ORDER_MENU_MESSAGE("주문하실 메뉴를 메뉴와 개수로 알려 주세요. (e.g. 해산물파스타-2, 레드와인-1, 초코케이크-1");

    private final String message;

    InputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
