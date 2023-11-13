package christmas.model;

import static christmas.utils.Constants.MAX_ORDER_QUANTITY;
import static christmas.utils.Constants.MIN_ORDER_MENU;

import christmas.enums.Category;
import christmas.enums.MenuInfo;

public class OrderDetail {

    private final OrderMenu orderMenu;

    public OrderDetail(OrderMenu orderMenu) {
        validate(orderMenu);
        this.orderMenu = orderMenu;
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
        if (MenuInfo.getCategoryByMenuName(name) == Category.BEVERAGE) {
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
                .mapToInt(MeunQuantity::getQuantity)
                .sum();

        return totalQuantity;
    }
}
