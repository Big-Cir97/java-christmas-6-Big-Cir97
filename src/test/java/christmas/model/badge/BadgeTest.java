package christmas.model.badge;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BadgeTest {

    @Test
    @DisplayName("별 배지를 얻는다.")
    void testGetStarBadge() {
        int totalDiscountPrice = 5_500;
        Badge badge = new Badge();

        String expected = "별";

        assertThat(badge.getBadgeName(totalDiscountPrice).getName()).isEqualTo(expected);
    }

    @Test
    @DisplayName("트리 배지를 얻는다.")
    void testGetTreeBadge() {
        int totalDiscountPrice = 11_111;
        Badge badge = new Badge();

        String expected = "트리";

        assertThat(badge.getBadgeName(totalDiscountPrice).getName()).isEqualTo(expected);
    }

    @Test
    @DisplayName("산타 배지를 얻는다.")
    void testGetSantaBadge() {
        int totalDiscountPrice = 22_222;
        Badge badge = new Badge();

        String expected = "산타";

        assertThat(badge.getBadgeName(totalDiscountPrice).getName()).isEqualTo(expected);
    }

    @Test
    @DisplayName("아무 뱃지를 얻지 못한다.")
    void testNotAnyBadge() {
        int totalDiscountPrice = 4_444;
        Badge badge = new Badge();

        String expected = "없음";

        assertThat(badge.getBadgeName(totalDiscountPrice).getName()).isEqualTo(expected);
    }

}