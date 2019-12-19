package ua.edu.sumdu.j2se.hrushev.tasks;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            if (task.getRepeatInterval() > 0) {
                Stream<LocalDateTime> stream = Tasks.getRepeatDates(task).stream();
                dateList.addAll(stream.filter(date -> date.isBefore(end) && date.isAfter(start)).collect(Collectors.toList()));
            } else {
                dateList.add(task.getTime());
            }
        }

        for (LocalDateTime date : dateList) {
            Set<Task> set = new HashSet<>();
            for (Task task : taskList) {
                if (task.getStartTime().equals(date) || (task.nextTimeAfter(start) != null && task.nextTimeAfter(start).isBefore(end))) {
                    set.add(task);
                }
            }

            calendar.put(date, set);
        }

        return calendar;
    }

    private static List<LocalDateTime> getRepeatDates(Task task) {
        ArrayList<LocalDateTime> dates = new ArrayList<>();
        LocalDateTime date = task.getStartTime();
        while (date.isBefore(task.getEndTime())) {
            dates.add(date);
            date = date.plusSeconds(task.getRepeatInterval());
        }

        return dates;
    }
}
