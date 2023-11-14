package christmas.model.result;

import static christmas.model.discount.enums.DiscountAmount.NON_DISCOUNT;

public class EventResult {

    private final DiscountResult discountResult;

    public EventResult(DiscountResult discountResult) {
        this.discountResult = discountResult;
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

    public boolean checkNonDiscount() {
        return isAllDiscountZero();
    }

    private boolean isAllDiscountZero() {
        return getChristmasDiscount() == NON_DISCOUNT.getDiscount() &&
                getChristmasDiscount() == getWeekendDiscount() &&
                getChristmasDiscount() == getWeekendDiscount() &&
                getChristmasDiscount() == getSpecialDiscount() &&
                getChristmasDiscount() == getGiveawayDiscount();
    }

}
