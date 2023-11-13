package christmas.model.discount;

import static christmas.enums.DiscountAmount.BASIC_CHRISTMAS_DISCOUNT;
import static christmas.enums.DiscountAmount.DAY_DISCOUNT;

import christmas.model.calendar.Calendar;
import christmas.model.calendar.ChristmasEventCalendar;

public class ChristmasDiscount implements Discount {

    private final ChristmasEventCalendar calendar;

    public ChristmasDiscount(Calendar calendar) {
        this.calendar = (ChristmasEventCalendar) calendar;
    }

    @Override
    public int calculateDiscount() {
        int days = calendar.calculateVisitDayFromStart();
        return days * DAY_DISCOUNT.getDiscount() + BASIC_CHRISTMAS_DISCOUNT.getDiscount();
    }
}
