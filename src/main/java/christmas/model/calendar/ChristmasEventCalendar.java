package christmas.model.calendar;

import static christmas.enums.CalendarDate.CHRISTMAS;
import static christmas.enums.CalendarDate.EVENT_MONTH;
import static christmas.enums.CalendarDate.EVENT_YEAR;
import static christmas.enums.CalendarDate.FRIDAY;
import static christmas.enums.CalendarDate.SATURDAY;
import static christmas.enums.CalendarDate.START_DAY;
import static christmas.enums.CalendarDate.SUNDAY;

import java.time.LocalDate;
import java.time.YearMonth;

public class ChristmasEventCalendar implements Calendar {

    private final LocalDate eventDate;

    public ChristmasEventCalendar(int day) {
        validate(day);
        this.eventDate = LocalDate.of(EVENT_YEAR.getNumber(), EVENT_MONTH.getNumber(), day);
    }

    public boolean isWeekDays() {
        int day = getDayOfWeek();
        if (day == FRIDAY.getNumber() || day == SATURDAY.getNumber()) {
            return false;
        }
        return true;
    }

    public boolean isSpecialDay() {
        if (isChristmas() || isSunday()) {
            return true;
        }

        return false;
    }

    public int calculateVisitDayFromStart() {
        int visitDay = getDayOfMonth();
        return visitDay - START_DAY.getNumber();
    }

    private void validate(int day) {
        validateDayRange(day);
    }

    private void validateDayRange(int day) {
        YearMonth yearMonth = YearMonth.of(EVENT_YEAR.getNumber(), EVENT_MONTH.getNumber());
        if (day < START_DAY.getNumber() || day > yearMonth.atEndOfMonth().getDayOfMonth()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private boolean isChristmas() {
        return getDayOfMonth() == CHRISTMAS.getNumber();
    }

    private boolean isSunday() {
        return getDayOfWeek() == SUNDAY.getNumber();
    }

    private int getDayOfWeek() {
        return eventDate.getDayOfWeek().getValue();
    }

    private int getDayOfMonth() {
        return eventDate.getDayOfMonth();
    }
}
