package christmas.model.discount;


import static christmas.model.discount.enums.DiscountAmount.CAN_GIVEAWAY_DISCOUNT;
import static christmas.model.discount.enums.DiscountAmount.NON_DISCOUNT;

import christmas.model.order.enums.MenuInfo;

public class GiveawayDiscount implements Discount {

    private final int totalOrderAmount;

    public GiveawayDiscount(int totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    @Override
    public int calculateDiscount() {
        if (totalOrderAmount >= CAN_GIVEAWAY_DISCOUNT.getDiscount()) {
            return MenuInfo.CHRISTMAS_PASTA.getPrice();
        }
        return NON_DISCOUNT.getDiscount();
    }
}
