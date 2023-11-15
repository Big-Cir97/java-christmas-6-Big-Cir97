package christmas.model.discount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.model.order.MenuName;
import christmas.model.order.MenuQuantity;
import christmas.model.order.OrderDetail;
import christmas.model.order.OrderMenu;
import christmas.utils.Payment;
import org.assertj.core.api.Assertions;
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
    @DisplayName("증정은 할인 혜택에 포함된다.")
    void testDiscountGiveaway() {
        orderMenu.addMenu(new MenuName("티본스테이크"), new MenuQuantity(10));
        orderDetail = new OrderDetail(orderMenu);
        int orderAmount = payment.beforeDiscountPayment(orderDetail);

        GiveawayDiscount giveawayDiscount = new GiveawayDiscount(orderAmount);
        int expected = 25_000;

        assertThat(giveawayDiscount.calculateDiscount()).isEqualTo(expected);
    }

    @Test
    @DisplayName("주문 금액을 넘지 않으면 증정 받지 않는다.")
    void testNonDiscountGiveaway() {
        orderMenu.addMenu(new MenuName("아이스크림"), new MenuQuantity(1));
        orderDetail = new OrderDetail(orderMenu);
        int orderAmount = payment.beforeDiscountPayment(orderDetail);

        GiveawayDiscount giveawayDiscount = new GiveawayDiscount(orderAmount);
        int expected = 0;

        assertThat(giveawayDiscount.calculateDiscount()).isEqualTo(expected);
    }

    @Test
    @DisplayName("주문 금액은 0원 미만일 수 없다.")
    void testUnderAmountCase() {
        int orderAmount = -1;

        assertThatThrownBy(() -> new GiveawayDiscount(orderAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 주문 금액은 0원 이상입니다.");
    }
}