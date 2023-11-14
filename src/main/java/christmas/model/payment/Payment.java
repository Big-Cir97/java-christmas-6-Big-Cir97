package christmas.model.payment;

import christmas.model.order.enums.MenuInfo;
import christmas.model.order.OrderDetail;

public class Payment {

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
    public int afterDiscountPayment(int beforeDiscountPayment, int totalDiscountPayment) {
        return beforeDiscountPayment - totalDiscountPayment;
    }
}