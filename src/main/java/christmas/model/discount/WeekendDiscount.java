package christmas.model.discount;

import static christmas.enums.DiscountAmount.MAIN_COURSE_DISCOUNT;
import static christmas.enums.DiscountAmount.NON_DISCOUNT;
import static christmas.utils.Constants.MIN_ORDER_MENU;

import christmas.enums.DiscountAmount;
import christmas.enums.MenuInfo;
import christmas.model.calendar.Calendar;
import christmas.model.order.MenuName;
import christmas.model.order.OrderDetail;
import christmas.utils.Constants;
import java.util.List;

public class WeekendDiscount implements Discount {

    private final Calendar calendar;
    private final OrderDetail orderDetail;

    public WeekendDiscount(Calendar calendar, OrderDetail orderDetail) {
        this.calendar = calendar;
        this.orderDetail = orderDetail;
    }

    @Override
    public int calculateDiscount() {
        if (calendar.isWeekend()) {
            return mainCourseDiscountPrice();
        }
        return NON_DISCOUNT.getDiscount();
    }

    private int mainCourseDiscountPrice() {
        List<MenuName> mainMenu = orderDetail.getMainMenu();
        int totalPrice = NON_DISCOUNT.getDiscount();
        if (mainMenu.size() < MIN_ORDER_MENU) {
            return totalPrice;
        }

        for (MenuName menuName : mainMenu) {
            int quantityByMenu = orderDetail.getQuantityByMenu(menuName);
            totalPrice += (quantityByMenu * MAIN_COURSE_DISCOUNT.getDiscount());
        }

        return totalPrice;
    }
}
