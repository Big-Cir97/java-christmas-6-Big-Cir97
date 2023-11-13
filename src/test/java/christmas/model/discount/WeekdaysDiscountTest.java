package christmas.model.discount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import christmas.model.calendar.Calendar;
import christmas.model.calendar.CalendarFactory;
import christmas.model.order.MenuName;
import christmas.model.order.MenuQuantity;
import christmas.model.order.OrderDetail;
import christmas.model.order.OrderMenu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekdaysDiscountTest {

    @Test
    @DisplayName("평일에는 디저트를 할인받는다.")
    void testDiscountDesert() {
        int visitDay = 3;
        Calendar calendar = CalendarFactory.createCalendar(visitDay);

        String desertMenu = "아이스크림";
        int quantity = 10;
        OrderMenu orderMenu = new OrderMenu();
        orderMenu.addMenu(new MenuName(desertMenu), new MenuQuantity(quantity));
        OrderDetail orderDetail = new OrderDetail(orderMenu);

        WeekdaysDiscount weekdaysDiscount = new WeekdaysDiscount(calendar, orderDetail);
        int expected = 2_023 * quantity;

        assertThat(weekdaysDiscount.calculateDiscount()).isEqualTo(expected);
    }

    @Test
    @DisplayName("평일 주문에 디저트가 없다면 할인 금액은 0원 이다.")
    void testDiscountZero() {
        int visitDay = 3;
        Calendar calendar = CalendarFactory.createCalendar(visitDay);

        String desertMenu = "시저샐러드";
        int quantity = 1;
        OrderMenu orderMenu = new OrderMenu();
        orderMenu.addMenu(new MenuName(desertMenu), new MenuQuantity(quantity));
        OrderDetail orderDetail = new OrderDetail(orderMenu);

        WeekdaysDiscount weekdaysDiscount = new WeekdaysDiscount(calendar, orderDetail);
        int expected = 0;

        assertThat(weekdaysDiscount.calculateDiscount()).isEqualTo(expected);
    }


}