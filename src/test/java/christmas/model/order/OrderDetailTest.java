package christmas.model.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.entry;

import java.util.List;
import java.util.Map;
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

        orderMenu.addMenu(new MenuName(drinkName), new MenuQuantity(drinkQuantity));

        assertThatThrownBy(() -> new OrderDetail(orderMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 음료만 주문할 수 없습니다.");
    }

    @Test
    @DisplayName("주문한 메뉴가 20개를 초과하면 예외를 발생시킨다.")
    void testOverQuantity() {
        String soupName = "양송이수프";
        int soupQuantity = 15;
        orderMenu.addMenu(new MenuName(soupName), new MenuQuantity(soupQuantity));

        String drinkName = "레드와인";
        int drinkQuantity = 6;
        orderMenu.addMenu(new MenuName(drinkName), new MenuQuantity(drinkQuantity));

        assertThatThrownBy(() -> new OrderDetail(orderMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("주문 수량은 최대 20개 입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("주문 목록을 확인한다.")
    void testDisplayOrderDetail() {
        String soupName = "양송이수프";
        int soupQuantity = 5;
        orderMenu.addMenu(new MenuName(soupName), new MenuQuantity(soupQuantity));

        String drinkName = "레드와인";
        int drinkQuantity = 6;
        orderMenu.addMenu(new MenuName(drinkName), new MenuQuantity(drinkQuantity));

        OrderDetail orderDetail = new OrderDetail(orderMenu);
        Map<String, Integer> orderMenus = orderDetail.getOrderMenuName();

        assertThat(orderMenus)
                .isNotEmpty()
                .contains(entry("양송이수프", 5), entry("레드와인", 6));
    }

    @Test
    @DisplayName("주문 목록에서 메인 메뉴를 확인한다.")
    void testCheckMainCourse() {
        String steakName = "티본스테이크";
        int steakQuantity = 5;
        orderMenu.addMenu(new MenuName(steakName), new MenuQuantity(steakQuantity));

        String drinkName = "레드와인";
        int drinkQuantity = 6;
        orderMenu.addMenu(new MenuName(drinkName), new MenuQuantity(drinkQuantity));

        OrderDetail orderDetail = new OrderDetail(orderMenu);
        List<MenuName> mainCourse = List.of(new MenuName(steakName));

        assertThat(orderDetail.getMainMenu()).isEqualTo(mainCourse);
    }

    @Test
    @DisplayName("주문 목록에서 디저트 메뉴를 확인한다.")
    void testCheckDesert() {
        String desertName = "초코케이크";
        int desertQuantity = 1;
        orderMenu.addMenu(new MenuName(desertName), new MenuQuantity(desertQuantity));

        String drinkName = "레드와인";
        int drinkQuantity = 6;
        orderMenu.addMenu(new MenuName(drinkName), new MenuQuantity(drinkQuantity));

        OrderDetail orderDetail = new OrderDetail(orderMenu);
        List<MenuName> desert = List.of(new MenuName(desertName));

        assertThat(orderDetail.getDesertMenu()).isEqualTo(desert);
    }

    @Test
    @DisplayName("메뉴 이름으로 주문 수량을 확인한다.")
    void testCheckQuantityByMenuName() {
        String soupName = "양송이수프";
        int soupQuantity = 5;
        orderMenu.addMenu(new MenuName(soupName), new MenuQuantity(soupQuantity));

        String steakName = "티본스테이크";
        int steakQuantity = 5;
        orderMenu.addMenu(new MenuName(steakName), new MenuQuantity(steakQuantity));
        OrderDetail orderDetail = new OrderDetail(orderMenu);

        assertThat(orderDetail.getQuantityByMenu(new MenuName(soupName))).isEqualTo(5);
        assertThat(orderDetail.getQuantityByMenu(new MenuName(steakName))).isEqualTo(5);
    }
}