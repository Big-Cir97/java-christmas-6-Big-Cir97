package christmas.model.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

        MenuName duplicatedMenu = new MenuName(name);
        assertThatThrownBy(() -> orderMenu.addMenu(duplicatedMenu, menuQuantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("메인 메뉴인지 확인한다.")
    void testCheckByMain() {
        String mainName = "티본스테이크";
        int mainQuantity = 1;
        MenuName main = new MenuName(mainName);
        MenuQuantity mQuantity = new MenuQuantity(mainQuantity);

        String subName = "양송이수프";
        int subQuantity = 1;
        MenuName sub = new MenuName(subName);
        MenuQuantity sQuantity = new MenuQuantity(subQuantity);

        orderMenu.addMenu(main, mQuantity);
        orderMenu.addMenu(sub, sQuantity);

        assertThat(orderMenu.containMainMenu(sub)).isFalse();
        assertThat(orderMenu.containMainMenu(main)).isTrue();
    }

    @Test
    @DisplayName("디저트인지 확인한다.")
    void testCheckByDesert() {
        String desertName = "아이스크림";
        int desertQuantity = 1;
        MenuName desert = new MenuName(desertName);
        MenuQuantity dQuantity = new MenuQuantity(desertQuantity);

        String subName = "양송이수프";
        int subQuantity = 1;
        MenuName sub = new MenuName(subName);
        MenuQuantity sQuantity = new MenuQuantity(subQuantity);

        orderMenu.addMenu(desert, dQuantity);
        orderMenu.addMenu(sub, sQuantity);

        assertThat(orderMenu.containDesertMenu((sub))).isFalse();
        assertThat(orderMenu.containDesertMenu((desert))).isTrue();
    }
}