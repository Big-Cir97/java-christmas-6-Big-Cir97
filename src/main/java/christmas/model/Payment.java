package christmas.model;

import christmas.enums.MenuInfo;

public class Payment {

    public int beforeDiscountPayment(OrderDetail orderDetail) {
        return orderDetail.getOrderMenu().entrySet().stream()
                .filter(entry -> MenuInfo.isExistMenu(entry.getKey()))
                .mapToInt(entry -> {
                    MenuInfo menuInfo = MenuInfo.findByMenuName(entry.getKey());
                    int quantity = entry.getValue();
                    return menuInfo.getPrice() * quantity;
                })
                .sum();
    }

    public int afterDiscountPayment() {
        return 0;
    }
}