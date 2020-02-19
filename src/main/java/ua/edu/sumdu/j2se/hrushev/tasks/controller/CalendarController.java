package ua.edu.sumdu.j2se.hrushev.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.hrushev.tasks.Main;
import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.hrushev.tasks.model.Observer;
import ua.edu.sumdu.j2se.hrushev.tasks.model.Tasks;
import ua.edu.sumdu.j2se.hrushev.tasks.view.CalendarView;

import java.time.LocalDateTime;

public class CalendarController extends Controller implements Observer {
    private final Logger logger = Logger.getLogger(Main.class);
    private AbstractTaskList list;
    private boolean changed = false;

    @Override
    public int process(AbstractTaskList list) {
        CalendarView view = new CalendarView();

        if (changed) {
            list = this.list;
        }

        view.view(list);

        LocalDateTime start = view.getStartDate();
        LocalDateTime end = view.getEndDate(start);

        view.showCalendar(Tasks.calendar(list, start, end));

        logger.info("Displayed task calendar via Calendar Controller. Dates: [" + start + "; " + end + "].");

        return 0;
    }

    @Override
    public void update(AbstractTaskList list) {
        this.list = list;
        changed = true;
        logger.info("List was updated. Triggered update method in Notification Controller.");
    }
}
