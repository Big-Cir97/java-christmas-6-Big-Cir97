package christmas.model.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.calendar.Calendar;
import christmas.model.calendar.CalendarFactory;
import christmas.model.order.MenuName;
import christmas.model.order.MenuQuantity;
import christmas.model.order.OrderDetail;
import christmas.model.order.OrderMenu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekendDiscountTest {

    @Test
    @DisplayName("주말에는 메인 요리를 할인받는다.")
    void testDiscountMainCourse() {
        int visitDay = 1;
        Calendar calendar = CalendarFactory.createCalendar(visitDay);

        String mainCourse = "티본스테이크";
        int quantity = 1;
        OrderMenu orderMenu = new OrderMenu();
        orderMenu.addMenu(new MenuName(mainCourse), new MenuQuantity(quantity));
        OrderDetail orderDetail = new OrderDetail(orderMenu);

        WeekendDiscount weekendDiscount = new WeekendDiscount(calendar, orderDetail);
        int expected = 2_023;

        assertThat(weekendDiscount.calculateDiscount()).isEqualTo(expected);
    }

    @Test
    @DisplayName("주말 주문에 메인 요리가 없다면 할인 금액은 0원 이다.")
    void testDiscountZero() {
        int visitDay = 3;
        Calendar calendar = CalendarFactory.createCalendar(visitDay);

        String mainCourse = "시저샐러드";
        int quantity = 1;
        OrderMenu orderMenu = new OrderMenu();
        orderMenu.addMenu(new MenuName(mainCourse), new MenuQuantity(quantity));
        OrderDetail orderDetail = new OrderDetail(orderMenu);

        WeekendDiscount weekendDiscount = new WeekendDiscount(calendar, orderDetail);
        int expected = 0;

        assertThat(weekendDiscount.calculateDiscount()).isEqualTo(expected);
    }
}