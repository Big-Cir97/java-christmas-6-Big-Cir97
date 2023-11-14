package christmas.enums;

import java.util.Arrays;

public enum BadgeInfo {

    SANTA_BADGE("산타", 20_000),
    TREE_BADGE("트리", 10_000),
    START_BADGE("별", 5_000),
    NON_BADGE("없음", 0);

    private final String name;
    private final int price;

    BadgeInfo(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static BadgeInfo findBadgeByPrice(int totalPrice) {
        return Arrays.stream(values())
                .filter(badge -> totalPrice >= badge.price)
                .findFirst()
                .orElse(NON_BADGE);
    }
}
