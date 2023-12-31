package christmas.view.enums;

import static christmas.model.calendar.enums.CalendarDate.EVENT_MONTH;

public enum OutputMessage {

    OUTPUT_CHRISTMAS_DISCOUNT_MESSAGE("크리스마스 디데이 할인: "),
    OUTPUT_WEEKEND_DISCOUNT_MESSAGE("주말 할인: "),
    OUTPUT_WEEKSDAYS_DISCOUNT_MESSAGE("평일 할인: "),
    OUTPUT_SPECIAL_DISCOUNT_MESSAGE("특별 할인: "),
    OUTPUT_GIVEAWAY_DISCOUNT_MESSAGE("증정 이벤트: "),
    OUTPUT_NON_DISCOUNT_MESSAGE("없음"),
    OUTPUT_PREVIEW_MESSAGE("에 우테코 식당에서 받을 이벤트 혜택 미리보기!"),
    OUTPUT_ORDER_MENU_MESSAGE("<주문 메뉴>"),
    OUTPUT_BEFORE_DISCOUNT_TOTAL_AMOUNT_MESSAGE("<할인 전 총주문 금액>"),
    OUTPUT_GIVEAWAY_MENU_MESSAGE("<증정 메뉴>"),
    OUTPUT_DISCOUNT_HISTORY_MESSAGE("<혜택 내역>"),
    OUTPUT_TOTAL_DISCOUNT_AMOUNT_MESSAGE("<총혜택 금액>"),
    OUTPUT_AFTER_DISCOUNT_MESSAGE("<할인 후 예상 결제 금액>"),
    OUTPUT_BADGE_MESSAGE(String.format("<%d월 이벤트 배지>", EVENT_MONTH.getNumber()));

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
