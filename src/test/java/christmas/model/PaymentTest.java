package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.enums.MenuInfo;
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
        orderMenu.addMenu(new MenuName(soupName), new MeunQuantity(soupQuantity));

        String drinkName = "레드와인";
        int drinkQuantity = 1;
        int drinkPrice = MenuInfo.RED_WINE.getPrice();
        orderMenu.addMenu(new MenuName(drinkName), new MeunQuantity(drinkQuantity));

        OrderDetail orderDetail = new OrderDetail(orderMenu);
        Payment payment = new Payment();

        assertThat(soupQuantity * soupPrice + drinkQuantity * drinkPrice)
                .isEqualTo(payment.beforeDiscountPayment(orderDetail));
    }
}