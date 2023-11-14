package christmas.model.discount.enums;

public enum DiscountAmount {

    NON_DISCOUNT(0),
    DAY_DISCOUNT(100),
    SPECIAL_DISCOUNT(1_000),
    BASIC_CHRISTMAS_DISCOUNT(1_000),
    MAIN_COURSE_DISCOUNT(2_023),
    DESERT_DISCOUNT(2_023),
    CAN_DISCOUNT_AMOUNT(10_000),
    CAN_GIVEAWAY_DISCOUNT(120_000);

    private final int discount;

    DiscountAmount(int discount) {
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }
}
