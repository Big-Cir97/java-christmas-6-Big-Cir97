package christmas.model.payment;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.enums.MenuInfo;
import christmas.model.calendar.Calendar;
import christmas.model.calendar.CalendarFactory;
import christmas.model.order.MenuName;
import christmas.model.order.MenuQuantity;
import christmas.model.order.OrderDetail;
import christmas.model.order.OrderMenu;
import christmas.model.payment.Payment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PaymentTest {

    @Test
    @DisplayName("할인 전 총 금액을 계산한다.")
    void testCalculateBeforeDiscountPayment() {
        OrderMenu orderMenu = new OrderMenu();

        String soupName = "양송이수프";
        int soupQuantity = 1;
        int soupPrice = MenuInfo.MUSHROOM_SOUP.getPrice();
        orderMenu.addMenu(new MenuName(soupName), new MenuQuantity(soupQuantity));

        String drinkName = "레드와인";
        int drinkQuantity = 1;
        int drinkPrice = MenuInfo.RED_WINE.getPrice();
        orderMenu.addMenu(new MenuName(drinkName), new MenuQuantity(drinkQuantity));

        OrderDetail orderDetail = new OrderDetail(orderMenu);
        Payment payment = new Payment();

        assertThat(soupQuantity * soupPrice + drinkQuantity * drinkPrice)
                .isEqualTo(payment.beforeDiscountPayment(orderDetail));
    }

    @Test
    @DisplayName("할인 후 예상 결제 금액을 계산한다.")
    void testCalculateAfterDiscountPayment() {
        String menu1 = "티본스테이크";
        int quantity1 = 1;

        String menu2 = "바비큐립";
        int quantity2 = 1;

        String menu3 = "초코케이크";
        int quantity3 = 2;

        String menu4 = "제로콜라";
        int quantity4 = 1;

        OrderMenu orderMenu = new OrderMenu();
        orderMenu.addMenu(new MenuName(menu1), new MenuQuantity(quantity1));
        orderMenu.addMenu(new MenuName(menu2), new MenuQuantity(quantity2));
        orderMenu.addMenu(new MenuName(menu3), new MenuQuantity(quantity3));
        orderMenu.addMenu(new MenuName(menu4), new MenuQuantity(quantity4));
        OrderDetail orderDetail = new OrderDetail(orderMenu);
        Payment payment = new Payment();
        int before = payment.beforeDiscountPayment(orderDetail);
        System.out.println(before);
        int discount = 31_246;

        int expected = 14_2000 - 31_246;
        assertThat(payment.afterDiscountPayment(before, discount)).isEqualTo(expected);

    }
}