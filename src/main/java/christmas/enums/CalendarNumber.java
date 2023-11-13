package christmas.enums;

public enum CalendarNumber {

    EVENT_YEAR(23),
    EVENT_MONTH(12),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7),
    CHRISTMAS(25);

    private final int number;

    CalendarNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
