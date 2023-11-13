package christmas.model.calendar;

import christmas.utils.Constants;
import java.time.LocalDate;
import java.util.List;

public class EventCalendar {
    // 월요일이 1, 일요일이 7입니다.
    private LocalDate eventDate;

    public EventCalendar(int day) {
        this.eventDate = LocalDate.of(Constants.EVENT_YEAR, Constants.EVENT_MONTH, day);
    }

    public boolean isWeekDays() {
         int day = eventDate.getDayOfWeek().getValue();
         if (day == Constants.FRIDAY || day == Constants.SATURDAY) {
             return false;
         }
        return true;
    }

    private int getDayOfMonth() {
        return eventDate.getDayOfMonth();
    }
}
