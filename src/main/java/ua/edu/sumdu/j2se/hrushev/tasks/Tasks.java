package ua.edu.sumdu.j2se.hrushev.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Tasks {
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime from, LocalDateTime to) {
        ArrayTaskList selected = new ArrayTaskList();

        for (Task task : tasks) {
            if (task != null) {
                if (task.isActive() && task.nextTimeAfter(from) != null && task.nextTimeAfter(from).isBefore(to.plusSeconds(1))) {
                    selected.add(task);
                }
            }
        }

        return selected;
    }

    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        SortedMap<LocalDateTime, Set<Task>> calendar = new TreeMap<>();
        Iterable<Task> taskList = Tasks.incoming(tasks, start, end);
        Set<LocalDateTime> dateList = new HashSet<>();

        for (Task task : taskList) {
            dateList.add(task.getStartTime());
        }

        for (LocalDateTime date : dateList) {
            Set<Task> set = new HashSet<>();
            for (Task task : taskList) {
                if (task.getStartTime().equals(date)) {
                    set.add(task);
                }
            }

            calendar.put(date, set);
        }

        return calendar;
    }
}
