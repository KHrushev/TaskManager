package ua.edu.sumdu.j2se.hrushev.tasks.view;

import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.hrushev.tasks.model.Task;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class CalendarView implements Viewable, DateGetter {

    @Override
    public int view(AbstractTaskList list) {
        System.out.println("\nCalendar is a list of Tasks, consisting of Tasks, that are going to be" +
                " executed in a period of time entered by you.\n");
        return 0;
    }

    public LocalDateTime getStartDate() {
        System.out.println("Enter calendar start date: (yyyy-MM-dd HH:mm)");
        return getDate();
    }

    public LocalDateTime getEndDate(LocalDateTime start) {
        System.out.println("Enter calendar end date: (yyyy-MM-dd HH:mm)");
        LocalDateTime end = getDate();
        while (end.isBefore(start) || end.equals(start)) {
            System.out.println("End time has to be later than start time.");
            end = getDate();
        }
        return end;
    }

    public void showCalendar(SortedMap<LocalDateTime, Set<Task>> calendar) {
        if (calendar.isEmpty()) {
            System.out.println("\nNo active tasks in entered range.\n");
        } else {
            System.out.println("\n" + this.prettyMap(calendar) + "\n");
        }
    }

    private String prettyMap(SortedMap<LocalDateTime, Set<Task>> map) {
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<LocalDateTime, Set<Task>>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<LocalDateTime, Set<Task>> entry = iter.next();
            sb.append(entry.getKey());
            sb.append('=').append('"');
            sb.append(entry.getValue());
            sb.append('"');
            if (iter.hasNext()) {
                sb.append(',').append('\n');
            }
        }

        return sb.toString();
    }
}
