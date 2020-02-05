package ua.edu.sumdu.j2se.hrushev.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.hrushev.tasks.Main;
import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.hrushev.tasks.model.Observer;
import ua.edu.sumdu.j2se.hrushev.tasks.model.Tasks;
import ua.edu.sumdu.j2se.hrushev.tasks.view.CalendarView;
import ua.edu.sumdu.j2se.hrushev.tasks.view.Viewable;

import java.time.LocalDateTime;

public class CalendarController extends Controller implements Observer {
    private final Logger logger = Logger.getLogger(Main.class);
    private Viewable view;
    private AbstractTaskList list;
    private boolean changed = false;

    public CalendarController(Viewable view) {
        this.view = view;
    }

    @Override
    public int process(AbstractTaskList list) {
        if (changed) {
            list = this.list;
        }

        this.view.view(list);

        if (this.view instanceof CalendarView) {
            LocalDateTime start = ((CalendarView) this.view).getStartDate();
            LocalDateTime end = ((CalendarView) this.view).getEndDate(start);

            ((CalendarView) this.view).showCalendar(Tasks.calendar(list, start, end));

            logger.info("Displayed task calendar via Calendar Controller. Dates: [" + start + "; " + end + "].");
        }

        return 0;
    }

    @Override
    public void update(AbstractTaskList list) {
        this.list = list;
        changed = true;
        logger.info("List was updated. Triggered update method in Notification Controller.");
    }
}
