import java.time.DayOfWeek;
import java.time.Month;

/*Хранилище календарей, всего их 14, чтобы хватило на все время на любой год*/
public class CalendarStorage {
    private static final int CALENDARS_NUMBER = 14;
    private static final int HALF_CALENDARS_NUMBER = 7;
    private static final int MONTHS_NUMBER = 12;
    /* в календаре есть как пустые ячейки в начале и конце (1 число началось во вторник,
    а значит ячейка с понедельником для этой недели пуста), так и заполненные числами,
    максимальное число ячеек соответствует случаю, когда 1 число выпадает на воскресенье и в месяце 31 день,
    последняя заполненная будет 37, но чтобы полностью заполнить неделю 6*7=42 ячейки */
    private static final int PLACES_NUMBER = 42;

    private static final int[][][] CALENDARS = new int[CALENDARS_NUMBER][MONTHS_NUMBER][PLACES_NUMBER];

    static {
        initCalendars();
    }

    /*сразу заполняем все 14 календарей, исходя из високосности и дня недели 1 января*/
    private static void initCalendars() {
        for (int calendarNumber = 0; calendarNumber < CALENDARS_NUMBER; calendarNumber++) {
            if (calendarNumber < HALF_CALENDARS_NUMBER) {
                createCalendar(
                        calendarNumber,
                        false,
                        DayOfWeek.of(calendarNumber + 1)
                );
            } else {
                createCalendar(
                        calendarNumber,
                        true,
                        DayOfWeek.of(calendarNumber - HALF_CALENDARS_NUMBER + 1));
            }
        }
    }


    private static void createCalendar(int calendarNumber, boolean isLeapYear, DayOfWeek januaryDayOfWeek) {
        int start = januaryDayOfWeek.getValue() - 1; //в какую ячейку попадет 1 января

        for (int month = 0; month < MONTHS_NUMBER; month++) {
            int day = 1;
            int daysNumberInMonth = Month.of(month + 1).length(isLeapYear);

            for (int place = 0; place < PLACES_NUMBER; place++) {
                if (place >= start && day <= daysNumberInMonth) {
                    CALENDARS[calendarNumber][month][place] = day;
                    day++;
                } else {
                    CALENDARS[calendarNumber][month][place] = 0;
                }

            }

            //в какой день недели 1 число следующего месяца
            start = (start + daysNumberInMonth) % HALF_CALENDARS_NUMBER;
        }
    }

    /*для обычных календарей номера с 1 по 7 - соответствует дню недели 1 января, для високосных
    с 8 по 14, день недели 1 января + 7*/
    public static int[][] getCalendarPages(boolean isLeapYear, DayOfWeek januaryDayOfWeek) {
        int calendarNumber;

        if (isLeapYear) {
            calendarNumber = januaryDayOfWeek.getValue() + HALF_CALENDARS_NUMBER;
        } else {
            calendarNumber = januaryDayOfWeek.getValue();
        }

        return CALENDARS[calendarNumber - 1];
    }
}
