package ua.edu.sumdu.j2se.hrushev.tasks.controller;

import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.hrushev.tasks.model.Tasks;
import ua.edu.sumdu.j2se.hrushev.tasks.view.CalendarView;
import ua.edu.sumdu.j2se.hrushev.tasks.view.Viewable;

import java.time.LocalDateTime;

public class CalendarController extends Controller {
    private Viewable view;

    public CalendarController(Viewable view) {
        this.view = view;
    }

    @Override
    public int process(AbstractTaskList list) {
        this.view.view(list);

        if (this.view instanceof CalendarView) {
            LocalDateTime start = ((CalendarView) this.view).getStartDate();
            LocalDateTime end = ((CalendarView) this.view).getEndDate();
            while (end.isBefore(start) || end.equals(start)) {
                System.out.println("End time has to be later than start time.");
                start = ((CalendarView) this.view).getStartDate();
                end = ((CalendarView) this.view).getEndDate();
            }

            ((CalendarView) this.view).showCalendar(Tasks.calendar(list, start, end));
        }
        return 0;
    }
}
