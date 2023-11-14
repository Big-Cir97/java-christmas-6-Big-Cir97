package christmas.model.order;

import static christmas.exception.ErrorType.INVALID_MENU_NAME;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OrderMenu {

    private Map<MenuName, MenuQuantity> orderMenu;

    public OrderMenu() {
        this.orderMenu = new HashMap<>();
    }

    public Map<MenuName, MenuQuantity> getOrderMenu() {
        return Collections.unmodifiableMap(orderMenu);
    }

    public int getMenuQuantity(MenuName menuName) {
        return orderMenu.get(menuName).getQuantity();
    }

    public void addMenu(MenuName menuName, MenuQuantity menuQuantity) {
        validate(menuName);
        orderMenu.put(menuName, menuQuantity);
    }

    private void validate(MenuName menuName) {
        validateDuplicateMenuName(menuName);
    }

    private void validateDuplicateMenuName(MenuName menuName) {
        if (orderMenu.containsKey(menuName)) {
            throw new IllegalArgumentException(INVALID_MENU_NAME.getMessage());
        }
    }
}
