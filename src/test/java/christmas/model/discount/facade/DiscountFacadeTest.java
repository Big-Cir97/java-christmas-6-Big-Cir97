package christmas.model.discount.facade;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.calendar.Calendar;
import christmas.model.calendar.CalendarFactory;
import christmas.model.order.MenuName;
import christmas.model.order.MenuQuantity;
import christmas.model.order.OrderDetail;
import christmas.model.order.OrderMenu;
import christmas.utils.Payment;
import christmas.model.result.DiscountResult;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DiscountFacadeTest {

    private Payment payment;

    private DiscountResult discountResult;

    @BeforeEach
    void setup() {
        payment = new Payment();
        discountResult = new DiscountResult();
    }

    @Test
    @DisplayName("만원 미만이면 할인받을 수 없다.")
    void testNonDiscountUnderTenThousand() {
        int visitDay = 2;
        Calendar calendar = CalendarFactory.createCalendar(visitDay);
        OrderMenu orderMenu = new OrderMenu();
        orderMenu.addMenu(new MenuName("아이스크림"), new MenuQuantity(1));
        OrderDetail orderDetail = new OrderDetail(orderMenu);

        DiscountFacade discountFacade = new DiscountFacade(calendar, orderDetail);
        int expected = 0;

        assertThat(discountFacade.calculateTotalDiscount(payment, discountResult)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("testArgumentDiscountGiveaway")
    @DisplayName("여러경우의 증정 할인")
    void testDiscountGiveaway(int visitDay, String menuName, int quantity, String menuName2, int quantity2,
                              int expected) {
        Calendar calendar = CalendarFactory.createCalendar(visitDay);
        OrderMenu orderMenu = new OrderMenu();
        orderMenu.addMenu(new MenuName(menuName), new MenuQuantity(quantity));
        orderMenu.addMenu(new MenuName(menuName2), new MenuQuantity(quantity2));
        OrderDetail orderDetail = new OrderDetail(orderMenu);

        DiscountFacade discountFacade = new DiscountFacade(calendar, orderDetail);

        assertThat(discountFacade.calculateTotalDiscount(payment, discountResult)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("testArgumentMainDiscount")
    @DisplayName("여러경우의 메인 메뉴 할인")
    void testDiscountMainMenu(int visitDay, String menuName, int quantity, String menuName2, int quantity2,
                              int expected) {
        Calendar calendar = CalendarFactory.createCalendar(visitDay);
        OrderMenu orderMenu = new OrderMenu();
        orderMenu.addMenu(new MenuName(menuName), new MenuQuantity(quantity));
        orderMenu.addMenu(new MenuName(menuName2), new MenuQuantity(quantity2));
        OrderDetail orderDetail = new OrderDetail(orderMenu);

        DiscountFacade discountFacade = new DiscountFacade(calendar, orderDetail);

        assertThat(discountFacade.calculateTotalDiscount(payment, discountResult)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("testArgumentDesertDiscount")
    @DisplayName("여러경우의 디저트 할인")
    void testDiscountDesert(int visitDay, String menuName, int quantity, String menuName2, int quantity2,
                            int expected) {
        Calendar calendar = CalendarFactory.createCalendar(visitDay);
        OrderMenu orderMenu = new OrderMenu();
        orderMenu.addMenu(new MenuName(menuName), new MenuQuantity(quantity));
        orderMenu.addMenu(new MenuName(menuName2), new MenuQuantity(quantity2));

        OrderDetail orderDetail = new OrderDetail(orderMenu);
        DiscountFacade discountFacade = new DiscountFacade(calendar, orderDetail);

        assertThat(discountFacade.calculateTotalDiscount(payment, discountResult)).isEqualTo(expected);
    }

    static Stream<Arguments> testArgumentDiscountGiveaway() {
        return Stream.of(
                // 크리스마스 할인 1000원 + 샴페인
                Arguments.of(1, "초코케이크", 10, "아이스크림", 1, 26_000),
                // 별 할인 + 평일 디저트 할인하지 않겠음 --> 1000 + 샴페인 + 크리스마스 할인(1200)
                Arguments.of(3, "티본스테이크", 5, "타파스", 10, 27_200),
                // 크리스마스 당일 : 별할인(1000) + 크리스마스 할인(3400) + 샴페인
                Arguments.of(25, "티본스테이크", 5, "타파스", 1, 29_400)
        );
    }

    static Stream<Arguments> testArgumentMainDiscount() {
        return Stream.of(
                // 메인 메뉴 할인 + 크리스마스 할인
                Arguments.of(1, "크리스마스파스타", 4, "아이스크림", 1, 2_023 * 4 + 1000),
                // 메인 메뉴 할인 + 크리스마스 할인 + 샴페인 증정 + 특별한 날 할인
                Arguments.of(1, "티본스테이크", 5, "아이스크림", 1, 2_023 * 5 + 1_000 + 25_000),
                // 메인 메뉴만 할인
                Arguments.of(29, "티본스테이크", 2, "아이스크림", 1, 2_023 * 2)
        );
    }

    static Stream<Arguments> testArgumentDesertDiscount() {
        return Stream.of(
                // 디저트 할인 + 크리스마스 할인 + 샴페인 증정
                Arguments.of(7, "레드와인", 2, "아이스크림", 1, 2_023 + 1_600 + 25_000),
                // 디저트 할인 + 크리스마스 할인 + 샴페인 증정 + 특별한 날 할인
                Arguments.of(25, "레드와인", 2, "아이스크림", 10, 2_023 * 10 + 3_400 + 25_000 + 1_000)
        );
    }
}