package christmas.view;

import static christmas.model.calendar.enums.CalendarDate.EVENT_MONTH;
import static christmas.model.discount.enums.DiscountAmount.NON_DISCOUNT;
import static christmas.model.order.enums.MenuInfo.CHAMPAGNE;
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

import christmas.model.badge.Badge;
import christmas.model.badge.enums.BadgeInfo;
import christmas.model.calendar.Calendar;
import christmas.model.order.OrderDetail;
import christmas.model.result.EventResult;
import christmas.utils.Constants;
import christmas.utils.Converter;
import java.util.Map;

public class OutputView {

    public void printPreview(Calendar calendar) {
        String output = String.format("%d월 %d일", EVENT_MONTH.getNumber(), calendar.getDayOfMonth())
                + OUTPUT_PREVIEW_MESSAGE.getMessage()
                + NEW_LINE;
        System.out.println(output);
    }

    public void printOrderMenu(OrderDetail orderDetail) {
        StringBuilder output = new StringBuilder();
        output.append(OUTPUT_ORDER_MENU_MESSAGE.getMessage()).append(NEW_LINE);

        Map<String, Integer> orderMenu = orderDetail.getOrderMenuName();
        for (String menuName : orderMenu.keySet()) {
            output.append(menuName).append(BLANK)
                    .append(orderMenu.get(menuName))
                    .append(Constants.QUANTITY_SUFFIX)
                    .append(NEW_LINE);
        }
        System.out.println(output);
    }

    public void printEventDiscount(EventResult eventResult, int totalDiscount) {
        printBeforeDiscountOrderAmount(eventResult);
        printGiveaway(eventResult);
        printDiscountList(eventResult);
        printTotalDiscount(totalDiscount);
        printAfterDiscountAmount(eventResult, totalDiscount);
    }

    public void printEventBadge(Badge badge) {
        String output = OUTPUT_BADGE_MESSAGE.getMessage() + NEW_LINE + badge.getBadgeName();
        System.out.print(output);
    }

    public void printErrorMessage(IllegalArgumentException ex) {
        System.out.println(ex.getMessage());
    }

    private void printBeforeDiscountOrderAmount(EventResult eventResult) {
        String output = OUTPUT_BEFORE_DISCOUNT_TOTAL_AMOUNT_MESSAGE.getMessage() + NEW_LINE
                + Converter.toThousandWonFormmat(eventResult.getNonDiscountOrderAmount()) + AMOUNT_SUFFIX + NEW_LINE;
        System.out.println(output);
    }

    private void printGiveaway(EventResult eventResult) {
        StringBuilder output = new StringBuilder();
        output.append(OUTPUT_GIVEAWAY_MENU_MESSAGE.getMessage()).append(NEW_LINE);

        int giveawayDiscount = eventResult.getGiveawayDiscount();
        if (giveawayDiscount == NON_DISCOUNT.getDiscount()) {
            output.append(OUTPUT_NON_DISCOUNT_MESSAGE.getMessage()).append(NEW_LINE);
            System.out.println(output);
            return;
        }
        output.append(CHAMPAGNE.getName()).append(BLANK)
                .append(GIVEAWAY_QUANTITY).append(QUANTITY_SUFFIX).append(NEW_LINE);
        System.out.println(output);
    }

    private void printDiscountList(EventResult eventResult) {
        StringBuilder output = new StringBuilder();
        output.append(OUTPUT_DISCOUNT_HISTORY_MESSAGE.getMessage()).append(NEW_LINE);

        if (eventResult.checkNonDiscount()) {
            output.append(OUTPUT_NON_DISCOUNT_MESSAGE.getMessage()).append(NEW_LINE);
            System.out.println(output);
            return;
        }
        printAllDiscountType(eventResult, output);
        System.out.println(output);
    }

    private void printAllDiscountType(EventResult eventResult, StringBuilder output) {
        output.append(printChristmasDiscount(eventResult));
        output.append(printWeeksDaysDiscount(eventResult));
        output.append(printWeekendDiscount(eventResult));
        output.append(printSpecialDiscount(eventResult));
        output.append(printGiveawayDiscount(eventResult));
    }

    private void printTotalDiscount(int totalDiscount) {
        StringBuilder output = new StringBuilder();
        output.append(OUTPUT_TOTAL_DISCOUNT_AMOUNT_MESSAGE.getMessage()).append(NEW_LINE);
        if (totalDiscount == NON_DISCOUNT.getDiscount()) {
            output.append(Converter.toThousandWonFormmat(totalDiscount))
                  .append(AMOUNT_SUFFIX).append(NEW_LINE);
            System.out.println(output);
            return;
        }
        output.append(MINUS_OPERATOR).append(Converter.toThousandWonFormmat(totalDiscount))
              .append(AMOUNT_SUFFIX).append(NEW_LINE);
        System.out.println(output);
    }

    private void printAfterDiscountAmount(EventResult eventResult, int totalDiscount) {
        String output = OUTPUT_AFTER_DISCOUNT_MESSAGE.getMessage() + NEW_LINE
                + Converter.toThousandWonFormmat(
                        eventResult.getPayment().afterDiscountPayment(
                                eventResult.getNonDiscountOrderAmount(), totalDiscount)
                )
                + AMOUNT_SUFFIX + NEW_LINE;
        System.out.println(output);
    }

    private String printChristmasDiscount(EventResult eventResult) {
        if (eventResult.isNonChristmasDiscount()) {
            return EMPTY;
        }
        return OUTPUT_CHRISTMAS_DISCOUNT_MESSAGE.getMessage() + MINUS_OPERATOR
                + Converter.toThousandWonFormmat(eventResult.getChristmasDiscount()) + AMOUNT_SUFFIX
                + NEW_LINE;
    }

    private String printWeeksDaysDiscount(EventResult eventResult) {
        if (eventResult.isNonWeeksDaysDiscount()) {
            return EMPTY;
        }
        return OUTPUT_WEEKSDAYS_DISCOUNT_MESSAGE.getMessage() + MINUS_OPERATOR
                + Converter.toThousandWonFormmat(eventResult.getWeeksDaysDiscount()) + AMOUNT_SUFFIX
                + NEW_LINE;
    }

    private String printWeekendDiscount(EventResult eventResult) {
        if (eventResult.isNonWeekendDiscount()) {
            return EMPTY;
        }
        return OUTPUT_WEEKEND_DISCOUNT_MESSAGE.getMessage() + MINUS_OPERATOR
                + Converter.toThousandWonFormmat(eventResult.getWeekendDiscount()) + AMOUNT_SUFFIX
                + NEW_LINE;
    }

    private String printSpecialDiscount(EventResult eventResult) {
        if (eventResult.isNonSpecialDiscount()) {
            return EMPTY;
        }
        return OUTPUT_SPECIAL_DISCOUNT_MESSAGE.getMessage() + MINUS_OPERATOR
                + Converter.toThousandWonFormmat(eventResult.getSpecialDiscount()) + AMOUNT_SUFFIX
                + NEW_LINE;
    }

    private String printGiveawayDiscount(EventResult eventResult) {
        if (eventResult.isNonGiveawayDiscount()) {
            return EMPTY;
        }
        return OUTPUT_GIVEAWAY_DISCOUNT_MESSAGE.getMessage() + MINUS_OPERATOR
                + Converter.toThousandWonFormmat(eventResult.getGiveawayDiscount()) + AMOUNT_SUFFIX
                + NEW_LINE;
    }
}
