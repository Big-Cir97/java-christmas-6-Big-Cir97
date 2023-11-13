package christmas.model.order;

import static christmas.utils.Constants.MAX_ORDER_QUANTITY;
import static christmas.utils.Constants.MIN_ORDER_MENU;

import christmas.enums.Category;
import christmas.enums.MenuInfo;
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
        orderMenu.getOrderMenu();
        return getOrderMenuName().entrySet().stream()
                .filter(entry -> MenuInfo.findByMenuName(entry.getKey())
                        .getCategory() == Category.MAIN_COURSE)
                .map(entry -> new MenuName(entry.getKey()))
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
                .get();

        String name = menuName.getName();
        if (MenuInfo.findByMenuName(name).getCategory() == Category.BEVERAGE) {
            throw new IllegalArgumentException("[ERROR] 음료만 주문할 수 없습니다.");
        }
    }

    private void validateMaxQuantity(int totalQuantity) {
        if (totalQuantity > MAX_ORDER_QUANTITY) {
            throw new IllegalArgumentException(String.format("[ERROR] 메뉴는 최대 %d개까지만 주문할 수 있습니다.", MAX_ORDER_QUANTITY));
        }
    }

    private int calculateTotalQuantity(OrderMenu orderMenu) {
        int totalQuantity = orderMenu.getOrderMenu().values().stream()
                .mapToInt(MenuQuantity::getQuantity)
                .sum();

        return totalQuantity;
    }
}
