package christmas.enums;

public enum CalendarNumber {

    START_DAY(1),

    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7),

    EVENT_YEAR(23),
    EVENT_MONTH(12),

    CHRISTMAS(25);

    private final int number;

    CalendarNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
