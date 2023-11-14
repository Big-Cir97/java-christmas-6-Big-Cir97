package christmas.model.result;

import static christmas.model.discount.enums.DiscountAmount.NON_DISCOUNT;

import christmas.model.payment.Payment;

public class EventResult {

    private final DiscountResult discountResult;

    private final Payment payment;

    private final int nonDiscountOrderAmount;

    public Payment getPayment() {
        return payment;
    }

    public int getNonDiscountOrderAmount() {
        return nonDiscountOrderAmount;
    }

    public EventResult(DiscountResult discountResult, Payment payment, int nonDiscountOrderAmount) {
        this.discountResult = discountResult;
        this.payment = payment;
        this.nonDiscountOrderAmount = nonDiscountOrderAmount;
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

    public boolean isNonChristmasDiscount() {
        return discountResult.getChristmasDiscount() == NON_DISCOUNT.getDiscount();
    }

    public boolean isNonWeeksDaysDiscount() {
        return discountResult.getWeeksDaysDiscount() == NON_DISCOUNT.getDiscount();
    }

    public boolean isNonWeekendDiscount() {
        return discountResult.getWeekendDiscount() == NON_DISCOUNT.getDiscount();
    }

    public boolean isNonSpecialDiscount() {
        return discountResult.getSpecialDiscount() == NON_DISCOUNT.getDiscount();
    }

    public boolean isNonGiveawayDiscount() {
        return discountResult.getGiveawayDiscount() == NON_DISCOUNT.getDiscount();
    }

    public boolean checkNonDiscount() {
        return isNonAnyDiscount();
    }

    private boolean isNonAnyDiscount() {
        return isNonChristmasDiscount() && isNonWeeksDaysDiscount() && isNonWeekendDiscount() &&
                isNonGiveawayDiscount() && isNonGiveawayDiscount();
    }
}
