package christmas.model.discount;

import static christmas.enums.DiscountAmount.NON_DISCOUNT;
import static christmas.enums.DiscountAmount.SPECIAL_DISCOUNT;

import christmas.model.calendar.Calendar;

public class SpecialDiscount implements Discount {

    private final Calendar calendar;

    public SpecialDiscount(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public int calculateDiscount() {
        if (calendar.isSpecialDay()) {
            return SPECIAL_DISCOUNT.getDiscount();
        }
        return NON_DISCOUNT.getDiscount();
    }
}
