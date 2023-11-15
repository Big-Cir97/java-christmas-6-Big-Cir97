package christmas.model.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.order.MenuName;
import christmas.model.order.MenuQuantity;
import christmas.model.order.OrderDetail;
import christmas.model.order.OrderMenu;
import christmas.utils.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GiveawayDiscountTest {

    private Payment payment;

    private OrderDetail orderDetail;

    private OrderMenu orderMenu;

    @BeforeEach
    void setup() {
        payment = new Payment();
        orderMenu = new OrderMenu();
    }

    @Test
    @DisplayName("증정은 할인에 포함한다.")
    void testDiscountGiveaway() {
        orderMenu.addMenu(new MenuName("티본스테이크"), new MenuQuantity(10));
        orderDetail = new OrderDetail(orderMenu);
        int notDiscount = payment.beforeDiscountPayment(orderDetail);

        GiveawayDiscount giveawayDiscount = new GiveawayDiscount(notDiscount);
        int expected = 25_000;

        assertThat(giveawayDiscount.calculateDiscount()).isEqualTo(expected);
    }

    @Test
    @DisplayName("증정이 없으면 할인되지 않는다.")
    void testNonDiscountGiveaway() {
        orderMenu.addMenu(new MenuName("아이스크림"), new MenuQuantity(1));
        orderDetail = new OrderDetail(orderMenu);
        int notDiscount = payment.beforeDiscountPayment(orderDetail);

        GiveawayDiscount giveawayDiscount = new GiveawayDiscount(notDiscount);
        int expected = 0;

        assertThat(giveawayDiscount.calculateDiscount()).isEqualTo(expected);
    }
}