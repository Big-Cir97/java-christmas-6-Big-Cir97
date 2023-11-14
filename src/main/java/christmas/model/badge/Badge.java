package christmas.model.badge;

import christmas.enums.BadgeInfo;

public class Badge {

    public BadgeInfo getBadgeName(int totalDiscountPrice) {
        return BadgeInfo.findBadgeByPrice(totalDiscountPrice);
    }
}
