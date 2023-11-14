package christmas.model.order;

import static christmas.exception.ErrorType.INVALID_MAX_MENU_QUANTITY;
import static christmas.exception.ErrorType.INVALID_MENU_ONLY_DRINK;
import static christmas.utils.Constants.MAX_ORDER_QUANTITY;
import static christmas.utils.Constants.MIN_ORDER_MENU;

import christmas.model.order.enums.Category;
import christmas.model.order.enums.MenuInfo;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDetail {

    private final OrderMenu orderMenu;

    public OrderDetail(OrderMenu orderMenu) {
        validate(orderMenu);
        this.orderMenu = orderMenu;
    }

    public Map<String, Integer> getOrderMenuName() {
        Map<String, Integer> details = new HashMap<>();
        for (Map.Entry<MenuName, MenuQuantity> entry : orderMenu.getOrderMenu().entrySet()) {
            String menuName = entry.getKey().getName();
            int quantity = entry.getValue().getQuantity();
            details.put(menuName, quantity);
        }

        return Collections.unmodifiableMap(details);
    }

    public int getQuantityByMenu(MenuName menuName) {
        return orderMenu.getMenuQuantity(menuName);
    }

    public List<MenuName> getMainMenu() {
        return getOrderMenuName().keySet().stream()
                .filter(value -> MenuInfo.findByMenuName(value)
                        .getCategory() == Category.MAIN_COURSE)
                .map(MenuName::new)
                .toList();
    }

    public List<MenuName> getDesertMenu() {
        return getOrderMenuName().keySet().stream()
                .filter(value -> MenuInfo.findByMenuName(value)
                        .getCategory() == Category.DESSERT)
                .map(MenuName::new)
                .toList();
    }

    private void validate(OrderMenu orderMenu) {
        if (orderMenu.getOrderMenu().size() == MIN_ORDER_MENU) {
            validateOnlyBeverage(orderMenu);
        }

        validateMaxQuantity(calculateTotalQuantity(orderMenu));
    }

    private void validateOnlyBeverage(OrderMenu orderMenu) {
        MenuName menuName = orderMenu.getOrderMenu().keySet().stream()
                .findFirst()
                .orElseThrow();

        String name = menuName.getName();
        if (MenuInfo.findByMenuName(name).getCategory() == Category.BEVERAGE) {
            throw new IllegalArgumentException(INVALID_MENU_ONLY_DRINK.getMessage());
        }
    }

    private void validateMaxQuantity(int totalQuantity) {
        if (totalQuantity > MAX_ORDER_QUANTITY) {
            throw new IllegalArgumentException(INVALID_MAX_MENU_QUANTITY.getMessage());
        }
    }

    private int calculateTotalQuantity(OrderMenu orderMenu) {
        return orderMenu.getOrderMenu().values().stream()
                .mapToInt(MenuQuantity::getQuantity)
                .sum();
    }
}
