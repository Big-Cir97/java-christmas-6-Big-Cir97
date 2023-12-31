package christmas.model.discount;

import static christmas.model.discount.enums.DiscountAmount.DESERT_DISCOUNT;
import static christmas.model.discount.enums.DiscountAmount.NON_DISCOUNT;
import static christmas.utils.Constants.MIN_ORDER_MENU;

import christmas.model.calendar.Calendar;
import christmas.model.order.MenuName;
import christmas.model.order.OrderDetail;
import java.util.List;

public class WeekdaysDiscount implements Discount {

    private final Calendar calendar;

    private final OrderDetail orderDetail;

    public WeekdaysDiscount(Calendar calendar, OrderDetail orderDetail) {
        this.calendar = calendar;
        this.orderDetail = orderDetail;
    }

    @Override
    public int calculateDiscount() {
        if (!calendar.isWeekend()) {
            return desertDiscountPrice();
        }
        return NON_DISCOUNT.getDiscount();
    }

    private int desertDiscountPrice() {
        List<MenuName> desertMenu = orderDetail.getDesertMenu();
        int totalPrice = NON_DISCOUNT.getDiscount();
        if (desertMenu.size() < MIN_ORDER_MENU) {
            return totalPrice;
        }

        for (MenuName menuName : desertMenu) {
            int quantityByMenu = orderDetail.getQuantityByMenu(menuName);
            totalPrice += (quantityByMenu * DESERT_DISCOUNT.getDiscount());
        }

        return totalPrice;
    }
}
