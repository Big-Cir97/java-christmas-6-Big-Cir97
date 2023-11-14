package christmas.view;

import static christmas.model.calendar.enums.CalendarDate.EVENT_MONTH;
import static christmas.model.discount.enums.DiscountAmount.NON_DISCOUNT;
import static christmas.model.order.enums.MenuInfo.CHAMPAGNE;
import static christmas.utils.Constants.AMOUNT_FORMAT;
import static christmas.utils.Constants.AMOUNT_SUFFIX;
import static christmas.utils.Constants.BLANK;
import static christmas.utils.Constants.EMPTY;
import static christmas.utils.Constants.GIVEAWAY_QUANTITY;
import static christmas.utils.Constants.MINUS_OPERATOR;
import static christmas.utils.Constants.NEW_LINE;
import static christmas.utils.Constants.QUANTITY_SUFFIX;
import static christmas.view.enums.OutputMessage.OUTPUT_AFTER_DISCOUNT_MESSAGE;
import static christmas.view.enums.OutputMessage.OUTPUT_BADGE_MESSAGE;
import static christmas.view.enums.OutputMessage.OUTPUT_BEFORE_DISCOUNT_TOTAL_AMOUNT_MESSAGE;
import static christmas.view.enums.OutputMessage.OUTPUT_CHRISTMAS_DISCOUNT_MESSAGE;
import static christmas.view.enums.OutputMessage.OUTPUT_DISCOUNT_HISTORY_MESSAGE;
import static christmas.view.enums.OutputMessage.OUTPUT_GIVEAWAY_DISCOUNT_MESSAGE;
import static christmas.view.enums.OutputMessage.OUTPUT_GIVEAWAY_MENU_MESSAGE;
import static christmas.view.enums.OutputMessage.OUTPUT_NON_DISCOUNT_MESSAGE;
import static christmas.view.enums.OutputMessage.OUTPUT_ORDER_MENU_MESSAGE;
import static christmas.view.enums.OutputMessage.OUTPUT_PREVIEW_MESSAGE;
import static christmas.view.enums.OutputMessage.OUTPUT_SPECIAL_DISCOUNT_MESSAGE;
import static christmas.view.enums.OutputMessage.OUTPUT_TOTAL_DISCOUNT_AMOUNT_MESSAGE;
import static christmas.view.enums.OutputMessage.OUTPUT_WEEKEND_DISCOUNT_MESSAGE;
import static christmas.view.enums.OutputMessage.OUTPUT_WEEKSDAYS_DISCOUNT_MESSAGE;

import christmas.model.badge.enums.BadgeInfo;
import christmas.model.calendar.Calendar;
import christmas.model.order.OrderDetail;
import christmas.model.payment.Payment;
import christmas.model.result.EventResult;
import christmas.utils.Constants;
import christmas.view.enums.OutputMessage;
import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {


    public void outputPreview(Calendar calendar) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d월 %d일", EVENT_MONTH.getNumber(), calendar.getDayOfMonth()))
                .append(OUTPUT_PREVIEW_MESSAGE.getMessage())
                .append(NEW_LINE);
        System.out.println(sb);
    }

    public void outputOrderMenu(OrderDetail orderDetail) {
        StringBuilder sb = new StringBuilder();
        sb.append(OUTPUT_ORDER_MENU_MESSAGE.getMessage()).append(NEW_LINE);

        Map<String, Integer> orderMenu = orderDetail.getOrderMenuName();
        for (String menuName : orderMenu.keySet()) {
            sb.append(menuName).append(BLANK)
                    .append(orderMenu.get(menuName))
                    .append(Constants.QUANTITY_SUFFIX)
                    .append(NEW_LINE);
        }
        System.out.println(sb);
    }

    public void outputBeforeDiscountOrderAmount(int totalOrderAmount) {
        StringBuilder sb = new StringBuilder();
        sb.append(OUTPUT_BEFORE_DISCOUNT_TOTAL_AMOUNT_MESSAGE.getMessage()).append(NEW_LINE);
        sb.append(formatComma(totalOrderAmount)).append(AMOUNT_SUFFIX).append(NEW_LINE);
        System.out.println(sb);
    }

    public void outputGiveaway(EventResult eventResult) {
        StringBuilder sb = new StringBuilder();
        sb.append(OUTPUT_GIVEAWAY_MENU_MESSAGE.getMessage()).append(NEW_LINE);

        int giveawayDiscount = eventResult.getGiveawayDiscount();
        if (giveawayDiscount == NON_DISCOUNT.getDiscount()) {
            sb.append(OUTPUT_NON_DISCOUNT_MESSAGE.getMessage()).append(NEW_LINE);
            System.out.println(sb);
            return;
        }

        sb.append(CHAMPAGNE.getName()).append(BLANK)
                .append(GIVEAWAY_QUANTITY).append(QUANTITY_SUFFIX).append(NEW_LINE);
        System.out.println(sb);
    }

    public void outputDiscountList(EventResult eventResult) {
        StringBuilder sb = new StringBuilder();
        sb.append(OUTPUT_DISCOUNT_HISTORY_MESSAGE.getMessage()).append(NEW_LINE);

        if (eventResult.checkNonDiscount()) {
            sb.append(OUTPUT_NON_DISCOUNT_MESSAGE.getMessage()).append(NEW_LINE);
            System.out.println(sb);
            return;
        }
        sb.append(printChristmasDiscount(eventResult));
        sb.append(printWeeksDaysDiscount(eventResult));
        sb.append(printWeekendDiscount(eventResult));
        sb.append(printSpecialDiscount(eventResult));
        sb.append(printGiveawayDiscount(eventResult));
        System.out.println(sb);
    }

    public void outputTotalDiscount(int totalDiscount) {
        StringBuilder sb = new StringBuilder();
        sb.append(OUTPUT_TOTAL_DISCOUNT_AMOUNT_MESSAGE.getMessage()).append(NEW_LINE);
        if (totalDiscount == NON_DISCOUNT.getDiscount()) {
            sb.append(formatComma(totalDiscount)).append(AMOUNT_SUFFIX).append(NEW_LINE);
            System.out.println(sb);
            return;
        }
        sb.append(MINUS_OPERATOR).append(formatComma(totalDiscount)).append(AMOUNT_SUFFIX).append(NEW_LINE);
        System.out.println(sb);
    }

    public void outputAfterDiscountAmount(Payment payment, int beforeDiscountAmount, int totalDiscount) {
        StringBuilder sb = new StringBuilder();
        sb.append(OUTPUT_AFTER_DISCOUNT_MESSAGE.getMessage()).append(NEW_LINE);;
        sb.append(formatComma(payment.afterDiscountPayment(beforeDiscountAmount, totalDiscount)))
                .append(NEW_LINE);
        System.out.println(sb);
    }

    public void outputBadge(BadgeInfo badgeInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append(OUTPUT_BADGE_MESSAGE.getMessage()).append(NEW_LINE)
                .append(badgeInfo.getName());
        System.out.print(sb);
    }

    public void outputErrorMessage(IllegalArgumentException ex) {
        System.out.println(ex.getMessage());
    }

    private String printChristmasDiscount(EventResult eventResult) {
        int christmasDiscount = eventResult.getChristmasDiscount();
        if (christmasDiscount == NON_DISCOUNT.getDiscount()) {
            return EMPTY;
        }
        return OUTPUT_CHRISTMAS_DISCOUNT_MESSAGE.getMessage() + MINUS_OPERATOR
                + formatComma(christmasDiscount) + AMOUNT_SUFFIX
                + NEW_LINE;
    }

    private String printWeeksDaysDiscount(EventResult eventResult) {
        int weeksDaysDiscount = eventResult.getWeeksDaysDiscount();
        if (weeksDaysDiscount == NON_DISCOUNT.getDiscount()) {
            return EMPTY;
        }
        return OUTPUT_WEEKSDAYS_DISCOUNT_MESSAGE.getMessage() + MINUS_OPERATOR
                + formatComma(weeksDaysDiscount) + AMOUNT_SUFFIX
                + NEW_LINE;
    }

    private String printWeekendDiscount(EventResult eventResult) {
        int weekendDiscount = eventResult.getWeekendDiscount();
        if (weekendDiscount == NON_DISCOUNT.getDiscount()) {
            return EMPTY;
        }
        return OUTPUT_WEEKEND_DISCOUNT_MESSAGE.getMessage() + MINUS_OPERATOR
                + formatComma(weekendDiscount) + AMOUNT_SUFFIX
                + NEW_LINE;
    }

    private String printSpecialDiscount(EventResult eventResult) {
        int specialDiscount = eventResult.getSpecialDiscount();
        if (specialDiscount == NON_DISCOUNT.getDiscount()) {
            return EMPTY;
        }
        return OUTPUT_SPECIAL_DISCOUNT_MESSAGE.getMessage() + MINUS_OPERATOR
                + formatComma(specialDiscount) + AMOUNT_SUFFIX
                + NEW_LINE;
    }

    private String printGiveawayDiscount(EventResult eventResult) {
        int giveawayDiscount = eventResult.getGiveawayDiscount();
        if (giveawayDiscount == NON_DISCOUNT.getDiscount()) {
            return EMPTY;
        }
        return OUTPUT_GIVEAWAY_DISCOUNT_MESSAGE.getMessage() + MINUS_OPERATOR
                + formatComma(giveawayDiscount) + AMOUNT_SUFFIX
                + NEW_LINE;
    }

    private String formatComma(int value) {
        DecimalFormat decimalFormat = new DecimalFormat(Constants.AMOUNT_FORMAT);
        return decimalFormat.format(value);
    }
}