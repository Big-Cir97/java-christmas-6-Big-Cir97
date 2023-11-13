package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.entry;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderDetailTest {

    private OrderMenu orderMenu;

    @BeforeEach
    void setup() {
        orderMenu = new OrderMenu();
    }

    @Test
    @DisplayName("음료만 주문하면 예외를 발생시킨다.")
    void testOnlyDrinkOrder() {
        String drinkName = "레드와인";
        int drinkQuantity = 3;

        orderMenu.addMenu(new MenuName(drinkName), new MeunQuantity(drinkQuantity));

        assertThatThrownBy(() -> new OrderDetail(orderMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 음료만 주문할 수 없습니다.");
    }

    @Test
    @DisplayName("주문한 메뉴가 20개를 초과하면 예외를 발생시킨다.")
    void testOverQuantity() {
        String soupName = "양송이수프";
        int soupQuantity = 15;
        orderMenu.addMenu(new MenuName(soupName), new MeunQuantity(soupQuantity));

        String drinkName = "레드와인";
        int drinkQuantity = 6;
        orderMenu.addMenu(new MenuName(drinkName), new MeunQuantity(drinkQuantity));

        assertThatThrownBy(() -> new OrderDetail(orderMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 메뉴는 최대 20개까지만 주문할 수 있습니다.");
    }

    @Test
    @DisplayName("주문 목록을 확인한다.")
    void testDisplayOrderDetail() {
        String soupName = "양송이수프";
        int soupQuantity = 5;
        orderMenu.addMenu(new MenuName(soupName), new MeunQuantity(soupQuantity));

        String drinkName = "레드와인";
        int drinkQuantity = 6;
        orderMenu.addMenu(new MenuName(drinkName), new MeunQuantity(drinkQuantity));

        OrderDetail orderDetail = new OrderDetail(orderMenu);
        Map<String, Integer> orderMenus = orderDetail.getOrderMenu();

        assertThat(orderMenus)
                .isNotEmpty()
                .contains(entry("양송이수프", 5), entry("레드와인", 6));
    }
}