package christmas.model.payment;

import christmas.enums.MenuInfo;
import christmas.model.order.OrderDetail;

public class Payment {

    // 할인 전 총 주문 금액
    public int beforeDiscountPayment(OrderDetail orderDetail) {
        return orderDetail.getOrderMenuName().entrySet().stream()
                .filter(entry -> MenuInfo.isExistMenu(entry.getKey()))
                .mapToInt(entry -> {
                    MenuInfo menuInfo = MenuInfo.findByMenuName(entry.getKey());
                    int quantity = entry.getValue();
                    return menuInfo.getPrice() * quantity;
                })
                .sum();
    }

    // 할인 후 총 주문 금액
    public int afterDiscountPayment() {
        return 0;
    }
}