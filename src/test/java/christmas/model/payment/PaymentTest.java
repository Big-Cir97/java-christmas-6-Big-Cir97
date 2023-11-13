package christmas.model.payment;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.enums.MenuInfo;
import christmas.model.order.MenuName;
import christmas.model.order.MenuQuantity;
import christmas.model.order.OrderDetail;
import christmas.model.order.OrderMenu;
import christmas.model.payment.Payment;
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
}