package christmas.model.badge;

import christmas.model.badge.enums.BadgeInfo;

public class Badge {

    private final BadgeInfo badgeInfo;

    public Badge(int totalDiscountAmount) {
        this.badgeInfo = selectBadge(totalDiscountAmount);
    }

    public String getBadgeName() {
        return badgeInfo.getName();
    }

    private BadgeInfo selectBadge(int totalDiscountAmount) {
        return BadgeInfo.findBadgeByPrice(totalDiscountAmount);
    }
}
