package christmas.enums;

public enum CalendarDate {

    START_DAY(1),
    CHRISTMAS(25),

    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7),

    EVENT_YEAR(23),
    EVENT_MONTH(12);

    private final int number;

    CalendarDate(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
