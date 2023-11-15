package christmas.model.result;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.calendar.Calendar;
import christmas.model.calendar.CalendarFactory;
import christmas.model.discount.facade.DiscountFacade;
import christmas.model.order.MenuName;
import christmas.model.order.MenuQuantity;
import christmas.model.order.OrderDetail;
import christmas.model.order.OrderMenu;
import christmas.model.payment.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventResultTest {

    @Test
    @DisplayName("모든 할인이 0원이면 참을 반환한다.")
    void testCheckAllDiscountIsZero() {
        int visitDay = 2;
        Calendar calendar = CalendarFactory.createCalendar(visitDay);

        OrderMenu orderMenu = new OrderMenu();
        String pastaName = "해산물파스타";
        int pastaQuantity = 3;
        orderMenu.addMenu(new MenuName(pastaName), new MenuQuantity(pastaQuantity));

        String drinkName = "제로콜라";
        int drinkQuantity = 3;
        orderMenu.addMenu(new MenuName(drinkName), new MenuQuantity(drinkQuantity));

        OrderDetail orderDetail = new OrderDetail(orderMenu);

        DiscountFacade discountFacade = new DiscountFacade(calendar, orderDetail);
        Payment payment = new Payment();
        int nonDiscountOrderAmount = payment.beforeDiscountPayment(orderDetail);
        DiscountResult discountResult = new DiscountResult();
        discountFacade.calculateTotalDiscount(payment, discountResult);
        EventResult eventResult = new EventResult(discountResult, payment, nonDiscountOrderAmount);

        assertThat(eventResult.checkNonDiscount()).isFalse();
    }

    @Test
    @DisplayName("혜택 결과를 저장한 값을 확인한다.")
    void testCheckResultDiscount() {
        int visitDay = 2;
        Calendar calendar = CalendarFactory.createCalendar(visitDay);

        OrderMenu orderMenu = new OrderMenu();
        String pastaName = "해산물파스타";
        int pastaQuantity = 3;
        orderMenu.addMenu(new MenuName(pastaName), new MenuQuantity(pastaQuantity));

        String drinkName = "제로콜라";
        int drinkQuantity = 3;
        orderMenu.addMenu(new MenuName(drinkName), new MenuQuantity(drinkQuantity));

        OrderDetail orderDetail = new OrderDetail(orderMenu);

        DiscountFacade discountFacade = new DiscountFacade(calendar, orderDetail);
        Payment payment = new Payment();
        int nonDiscountOrderAmount = payment.beforeDiscountPayment(orderDetail);
        DiscountResult discountResult = new DiscountResult();
        discountFacade.calculateTotalDiscount(payment, discountResult);
        EventResult eventResult = new EventResult(discountResult, payment, nonDiscountOrderAmount);

        assertThat(eventResult.getChristmasDiscount()).isEqualTo(discountResult.getChristmasDiscount());
        assertThat(eventResult.getGiveawayDiscount()).isEqualTo(discountResult.getGiveawayDiscount());
        assertThat(eventResult.getSpecialDiscount()).isEqualTo(discountResult.getSpecialDiscount());
        assertThat(eventResult.getWeekendDiscount()).isEqualTo(discountResult.getWeekendDiscount());
        assertThat(eventResult.getWeeksDaysDiscount()).isEqualTo(discountResult.getWeeksDaysDiscount());
    }
}