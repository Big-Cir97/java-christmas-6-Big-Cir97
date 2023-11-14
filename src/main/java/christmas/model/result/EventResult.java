package christmas.model.result;

import christmas.model.badge.enums.BadgeInfo;
import christmas.model.badge.Badge;

public class EventResult {

    private final DiscountResult discountResult;

    public EventResult(DiscountResult discountResult) {
        this.discountResult = discountResult;
    }

    public BadgeInfo getBadgeResult(Badge badge, int totalDiscountPrice) {
        return badge.getBadgeName(totalDiscountPrice);
    }

    public int getChristmasDiscount() {
        return discountResult.getChristmasDiscount();
    }

    public int getWeeksDaysDiscount() {
        return discountResult.getWeeksDaysDiscount();
    }

    public int getWeekendDiscount() {
        return discountResult.getWeekendDiscount();
    }

    public int getSpecialDiscount() {
        return discountResult.getSpecialDiscount();
    }

    public int getGiveawayDiscount() {
        return discountResult.getGiveawayDiscount();
    }

//    public int getChristmasDiscount() {
//        return CHRISTMAS_DISCOUNT_RESULT + MINUS_OPERATOR + formmatComma() + RESULT_SUFFIX;
//    }
//
//
//
//    private String formmatComma(int value) {
//        DecimalFormat decimalFormat = new DecimalFormat(Constants.AMOUNT_FORMAT);
//        return decimalFormat.format(value);
//    }
}
