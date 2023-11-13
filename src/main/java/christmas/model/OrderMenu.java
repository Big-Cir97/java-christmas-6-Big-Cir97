package christmas.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OrderMenu {

    private Map<MenuName, MeunQuantity> orderMenu;

    public OrderMenu() {
        this.orderMenu = new HashMap<>();
    }

    public Map<MenuName, MeunQuantity> getOrderMenu() {
        return Collections.unmodifiableMap(orderMenu);
    }

    public int getMenuQuantity(MenuName menuName) {
        return orderMenu.get(menuName).getQuantity();
    }

    public void addMenu(MenuName menuName, MeunQuantity meunQuantity) {
        validate(menuName);
        orderMenu.put(menuName, meunQuantity);
    }

    private void validate(MenuName menuName) {
        validateDuplicateMenuName(menuName);
    }

    private void validateDuplicateMenuName(MenuName menuName) {
        if (orderMenu.containsKey(menuName)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
