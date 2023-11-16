package christmas.utils;

import static christmas.model.discount.enums.DiscountAmount.CAN_GIVEAWAY_DISCOUNT;
import static christmas.model.order.enums.MenuInfo.CHAMPAGNE;

import christmas.model.order.OrderDetail;
import christmas.model.order.enums.MenuInfo;

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

    public int afterDiscountPayment(int beforeDiscountPayment, int totalDiscountPayment) {
        if (beforeDiscountPayment < CAN_GIVEAWAY_DISCOUNT.getDiscount()) {
            return beforeDiscountPayment - totalDiscountPayment;
        }
        return beforeDiscountPayment - totalDiscountPayment + CHAMPAGNE.getPrice();
    }
}