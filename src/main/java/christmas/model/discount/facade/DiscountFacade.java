package christmas.model.discount.facade;

import static christmas.enums.DiscountAmount.CAN_DISCOUNT_AMOUNT;
import static christmas.enums.DiscountAmount.CAN_GIVEAWAY_DISCOUNT;
import static christmas.enums.DiscountAmount.NON_DISCOUNT;

import christmas.enums.DiscountAmount;
import christmas.enums.MenuInfo;
import christmas.model.DiscountResult;
import christmas.model.calendar.Calendar;
import christmas.model.calendar.ChristmasEventCalendar;
import christmas.model.discount.ChristmasDiscount;
import christmas.model.discount.SpecialDiscount;
import christmas.model.discount.WeekdaysDiscount;
import christmas.model.discount.WeekendDiscount;
import christmas.model.order.OrderDetail;
import christmas.model.payment.Payment;

public class DiscountFacade {

    private final Calendar calendar;
    private final OrderDetail orderDetail;

    public DiscountFacade(Calendar calendar, OrderDetail orderDetail) {
        this.calendar = calendar;
        this.orderDetail = orderDetail;
    }

    public int getTotalDiscount(Payment payment, DiscountResult discountResult) {
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
        int weekendAmount = weekendDiscount();
        int weekdaysAmount = weekdaysDiscount();
        int specialAmount = specialDiscount();
        int giveawayAmount = giveawayDiscount(payment);

        discountResult.updateWeekendDiscount(weekendAmount);
        discountResult.updateWeeksDaysDiscount(weekdaysAmount);
        discountResult.updateSpecialDiscount(specialDiscount());
        discountResult.updateGiveawayDiscount(giveawayAmount);

        return weekendAmount + weekdaysAmount + specialAmount + giveawayAmount;
    }

    private int weekendDiscount() {
        WeekendDiscount weekendDiscount = new WeekendDiscount(calendar, orderDetail);
        return weekendDiscount.calculateDiscount();
    }

    private int weekdaysDiscount() {
        WeekdaysDiscount weekdaysDiscount = new WeekdaysDiscount(calendar, orderDetail);
        return weekdaysDiscount.calculateDiscount();
    }

    private int specialDiscount() {
        SpecialDiscount specialDiscount = new SpecialDiscount(calendar);
        return specialDiscount.calculateDiscount();
    }

    private int giveawayDiscount(Payment payment) {
        int beforeDiscountPayment = payment.beforeDiscountPayment(orderDetail);
        if (beforeDiscountPayment < CAN_GIVEAWAY_DISCOUNT.getDiscount()) {
            return NON_DISCOUNT.getDiscount();
        }
        return MenuInfo.CHAMPAGNE.getPrice();
    }


    private boolean includeChristmasDiscount() {
        if (calendar instanceof ChristmasEventCalendar) {
            return true;
        }
        return false;
    }
}
