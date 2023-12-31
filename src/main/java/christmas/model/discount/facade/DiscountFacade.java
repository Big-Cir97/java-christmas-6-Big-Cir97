package christmas.model.discount.facade;

import static christmas.model.discount.enums.DiscountAmount.CAN_DISCOUNT_AMOUNT;
import static christmas.model.discount.enums.DiscountAmount.NON_DISCOUNT;

import christmas.model.discount.GiveawayDiscount;
import christmas.model.result.DiscountResult;
import christmas.model.calendar.Calendar;
import christmas.model.calendar.ChristmasEventCalendar;
import christmas.model.discount.ChristmasDiscount;
import christmas.model.discount.SpecialDiscount;
import christmas.model.discount.WeekdaysDiscount;
import christmas.model.discount.WeekendDiscount;
import christmas.model.order.OrderDetail;
import christmas.utils.Payment;

public class DiscountFacade {

    private final Calendar calendar;

    private final OrderDetail orderDetail;

    public DiscountFacade(Calendar calendar, OrderDetail orderDetail) {
        this.calendar = calendar;
        this.orderDetail = orderDetail;
    }

    public int calculateTotalDiscount(Payment payment, DiscountResult discountResult) {
        if (!canDiscount(payment)) {
            return NON_DISCOUNT.getDiscount();
        }

        if (includeChristmasDiscount()) {
            ChristmasDiscount christmasDiscount = new ChristmasDiscount(calendar);
            int christmasAmount = christmasDiscount.calculateDiscount();
            discountResult.updateChristmasDiscount(christmasAmount);
            return christmasAmount + basicDiscount(payment, discountResult);
        }
        return basicDiscount(payment, discountResult);
    }

    private boolean canDiscount(Payment payment) {
        return payment.beforeDiscountPayment(orderDetail) >= CAN_DISCOUNT_AMOUNT.getDiscount();
    }

    private int basicDiscount(Payment payment, DiscountResult discountResult) {
        int weekendAmount = calculateWeekendDiscount();
        int weekdaysAmount = calculateWeekdaysDiscount();
        int specialAmount = calculateSpecialDiscount();
        int giveawayAmount = calculateGiveawayDiscount(payment);

        discountResult.updateWeekendDiscount(weekendAmount);
        discountResult.updateWeeksDaysDiscount(weekdaysAmount);
        discountResult.updateSpecialDiscount(calculateSpecialDiscount());
        discountResult.updateGiveawayDiscount(giveawayAmount);

        return weekendAmount + weekdaysAmount + specialAmount + giveawayAmount;
    }

    private int calculateWeekendDiscount() {
        WeekendDiscount weekendDiscount = new WeekendDiscount(calendar, orderDetail);
        return weekendDiscount.calculateDiscount();
    }

    private int calculateWeekdaysDiscount() {
        WeekdaysDiscount weekdaysDiscount = new WeekdaysDiscount(calendar, orderDetail);
        return weekdaysDiscount.calculateDiscount();
    }

    private int calculateSpecialDiscount() {
        SpecialDiscount specialDiscount = new SpecialDiscount(calendar);
        return specialDiscount.calculateDiscount();
    }

    private int calculateGiveawayDiscount(Payment payment) {
        GiveawayDiscount giveawayDiscount = new GiveawayDiscount(payment.beforeDiscountPayment(orderDetail));
        return giveawayDiscount.calculateDiscount();
    }

    private boolean includeChristmasDiscount() {
        return calendar instanceof ChristmasEventCalendar;
    }
}
