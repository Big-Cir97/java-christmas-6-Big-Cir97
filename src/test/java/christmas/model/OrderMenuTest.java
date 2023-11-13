package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderMenuTest {

    private OrderMenu orderMenu;

    @BeforeEach
    void setup() {
        orderMenu = new OrderMenu();
    }

    @Test
    @DisplayName("주문한 메뉴를 저장한다.")
    void testOneOrderMenu() {
        String name = "양송이수프";
        int quantity = 1;
        MenuName menuName = new MenuName(name);
        MenuQuantity menuQuantity = new MenuQuantity(quantity);

        orderMenu.addMenu(menuName, menuQuantity);

        assertThat(orderMenu.getMenuQuantity(menuName)).isEqualTo(quantity);
    }

    @Test
    @DisplayName("중복된 주문은 예외를 발생시킨다.")
    void testDuplicateMenu() {
        String name = "양송이수프";
        int quantity = 1;
        MenuName menuName = new MenuName(name);
        MenuQuantity menuQuantity = new MenuQuantity(quantity);

        orderMenu.addMenu(menuName, menuQuantity);

        String duplicatedName = name;
        MenuName duplicatedMenu = new MenuName(duplicatedName);
        Assertions.assertThatThrownBy(() -> orderMenu.addMenu(duplicatedMenu, menuQuantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}