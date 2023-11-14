package christmas.model.result;

import static christmas.model.discount.enums.DiscountAmount.NON_DISCOUNT;

public class DiscountResult {

    private int christmasDiscount;

    private int weeksDaysDiscount;

    private int weekendDiscount;

    private int specialDiscount;

    private int giveawayDiscount;

    public DiscountResult() {
        this.christmasDiscount = NON_DISCOUNT.getDiscount();
        this.weeksDaysDiscount = NON_DISCOUNT.getDiscount();
        this.weekendDiscount = NON_DISCOUNT.getDiscount();
        this.specialDiscount = NON_DISCOUNT.getDiscount();
        this.giveawayDiscount = NON_DISCOUNT.getDiscount();
    }

    public void updateChristmasDiscount(int christmasDiscount) {
        this.christmasDiscount = christmasDiscount;
    }

    public void updateWeeksDaysDiscount(int weeksDaysDiscount) {
        this.weeksDaysDiscount = weeksDaysDiscount;
    }

    public void updateWeekendDiscount(int weekendDiscount) {
        this.weekendDiscount = weekendDiscount;
    }

    public void updateSpecialDiscount(int specialDiscount) {
        this.specialDiscount = specialDiscount;
    }

    public void updateGiveawayDiscount(int giveawayDiscount) {
        this.giveawayDiscount = giveawayDiscount;
    }

    public int getChristmasDiscount() {
        return christmasDiscount;
    }

    public int getWeeksDaysDiscount() {
        return weeksDaysDiscount;
    }

    public int getWeekendDiscount() {
        return weekendDiscount;
    }

    public int getSpecialDiscount() {
        return specialDiscount;
    }

    public int getGiveawayDiscount() {
        return giveawayDiscount;
    }
}
