package christmas.controller;

import christmas.model.badge.Badge;
import christmas.model.badge.enums.BadgeInfo;
import christmas.model.calendar.Calendar;
import christmas.model.calendar.CalendarFactory;
import christmas.model.discount.facade.DiscountFacade;
import christmas.model.order.MenuName;
import christmas.model.order.MenuQuantity;
import christmas.model.order.OrderDetail;
import christmas.model.order.OrderMenu;
import christmas.model.payment.Payment;
import christmas.model.result.DiscountResult;
import christmas.model.result.EventResult;
import christmas.utils.Converter;
import christmas.utils.DataInitializer;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class ChristmasController {

    private final InputView inputView;

    private final OutputView outputView;

    public ChristmasController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void visitRestaurant() {
        Calendar calendar = initCalendar();
        OrderDetail orderDetail = initOrderDetail();
        Payment payment = new Payment();

        int nonDiscountTotalOrderAmount = payment.beforeDiscountPayment(orderDetail);

        DiscountResult discountResult = new DiscountResult();
        DiscountFacade discountFacade = new DiscountFacade(calendar, orderDetail);
        int totalDiscount = discountFacade.getTotalDiscount(payment, discountResult);

        BadgeInfo badgeName = new Badge().getBadgeName(totalDiscount);
        EventResult eventResult = new EventResult(discountResult);

        outputView.outputPreview(calendar);
        outputView.outputOrderMenu(orderDetail);
        outputView.outputBeforeDiscountOrderAmount(nonDiscountTotalOrderAmount);
        outputView.outputGiveaway(eventResult);
        outputView.outputDiscountList(eventResult);
        outputView.outputTotalDiscount(totalDiscount);
        outputView.outputAfterDiscountAmount(payment, nonDiscountTotalOrderAmount, totalDiscount);
        outputView.outputBadge(badgeName);
    }

    private Calendar initCalendar() {
        DataInitializer<Calendar> initializer = new DataInitializer<>(
                () -> CalendarFactory.createCalendar(inputView.inputVisitDay()),
                outputView::outputErrorMessage
        );
        return initializer.initialize();
    }

    private OrderDetail initOrderDetail() {
        DataInitializer<OrderDetail> initializer = new DataInitializer<>(
                () -> {
                    List<String> inputOrderMenu = inputView.inputOrderMenu();
                    OrderMenu orderMenu = createOrderMenu(inputOrderMenu);
                    return new OrderDetail(orderMenu);
                },
                outputView::outputErrorMessage
        );
        return initializer.initialize();
    }

    private OrderMenu createOrderMenu(List<String> inputOrderMenu) {
        OrderMenu orderMenu = new OrderMenu();
        for (String input : inputOrderMenu) {
            String[] split = Converter.splitByMinusOperation(input);
            String name = split[0];
            int number = Converter.toInteger(split[1]);

            MenuName menuName = new MenuName(name);
            MenuQuantity menuQuantity = new MenuQuantity(number);
            orderMenu.addMenu(menuName, menuQuantity);
        }
        return orderMenu;
    }
}
