package christmas.model.result;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountResultTest {

    @Test
    @DisplayName("할인 결과 객체 생성 시 0으로 초기화된다.")
    void testInitValueIsZero() {
        DiscountResult discountResult = new DiscountResult();

        assertThat(discountResult.getWeeksDaysDiscount()).isEqualTo(0);
        assertThat(discountResult.getGiveawayDiscount()).isEqualTo(0);
        assertThat(discountResult.getChristmasDiscount()).isEqualTo(0);
        assertThat(discountResult.getSpecialDiscount()).isEqualTo(0);
        assertThat(discountResult.getWeekendDiscount()).isEqualTo(0);
    }

    @Test
    @DisplayName("할인 금액을 추가할 수 있다.")
    void testAddDiscountAmount() {
        DiscountResult discountResult = new DiscountResult();
        int christmasDiscount = 1_000;
        int weeksDaysDiscount = 2_000;
        int weekendDiscount = 3_000;
        int specialDiscount = 2_222;
        int giveawayDiscount = 1_001;

        discountResult.updateChristmasDiscount(christmasDiscount);
        discountResult.updateSpecialDiscount(specialDiscount);
        discountResult.updateGiveawayDiscount(giveawayDiscount);
        discountResult.updateWeekendDiscount(weekendDiscount);
        discountResult.updateWeeksDaysDiscount(weeksDaysDiscount);

        assertThat(discountResult.getChristmasDiscount()).isEqualTo(christmasDiscount);
        assertThat(discountResult.getGiveawayDiscount()).isEqualTo(giveawayDiscount);
        assertThat(discountResult.getWeekendDiscount()).isEqualTo(weekendDiscount);
        assertThat(discountResult.getSpecialDiscount()).isEqualTo(specialDiscount);
        assertThat(discountResult.getWeeksDaysDiscount()).isEqualTo(weeksDaysDiscount);

    }
}