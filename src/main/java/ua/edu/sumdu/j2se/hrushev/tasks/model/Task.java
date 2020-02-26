package ua.edu.sumdu.j2se.hrushev.tasks.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Task implements Cloneable, Serializable {
    private String title;
    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    private int interval;
    private boolean isActive;

    /** Constructor for non-repeatable task.
     * @param title sets the title of a task.
     * @param time time sets the time of task execution.
     */

    public Task(String title, LocalDateTime time) throws IllegalArgumentException{
        this.title = title;
        try {
            if (time == null) {
                throw new IllegalArgumentException();
            } else this.time = time;
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        isActive = true;
    }

    /** Constructor for repeatable task.
     *
     * @param title sets the title of a task.
     * @param start sets starting time of task execution.
     * @param end sets time for ending of task execution.
     * @param interval sets interval between task repeats.
     */

    public Task(String title, LocalDateTime start,
                LocalDateTime end, int interval) throws IllegalArgumentException{
        this.title = title;
        try {
            if (start == null || end == null || interval <= 0 || end.isBefore(start)) throw new IllegalArgumentException();
            else {
                this.start = start;
                this.end = end;
                this.interval = interval;
                this.time = start;
            }
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException("Illegal Argument in Task Assignment.");
        }
        isActive = true;
    }

    /**
     * Getter for task title.
     * @return title
     */

    public String getTitle() {
        return title;
    }

    /**
     * Setter for task title.
     * @param title sets new task title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for current status of task.
     * @return isActive field.
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Setter for isActive field.
     * @param active sets new activity status.
     */
    public void setActive(boolean active) {
        isActive = active;
    }

    /**
     *  Getter for task time.
     * @return time if task is non-repeatable.
     * return start if task is repeatable.
     */

    public LocalDateTime getTime() {
        if (interval == 0) {
            return time;
        } else {
            return start;
        }
    }

    /**
     *  Setter for task time.
     *  Converts repeatable task into a non-repeatable one.
     * @param time sets new task time.
     */

    public void setTime(LocalDateTime time) {
        if (interval != 0) {
            this.interval = 0;
            this.start = time;
            this.end = time;
        }

        this.time = time;
    }

    /**
     *  Setter for task time.
     *  Converts a non-repeatable task into a repeatable one.
     * @param start sets new task start time
     * @param end sets new task end time
     * @param interval sets new task repeat interval
     */

    public void setTime(LocalDateTime start, LocalDateTime end, int interval) {
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    /**
     * Getter for start time of a repeatable task.
     * @return time of non-repeatable task execution.
     * return start time of repeatable task execution.
     */

    public LocalDateTime getStartTime() {
        return interval == 0 ? time : start;
    }

    /**
     * Getter for end time of a repeatable task.
     * @return time of non-repeatable task execution.
     *  return end time of repeatable task execution.
     */

    public LocalDateTime getEndTime() {
        if (interval == 0) {
            return time;
        } else {
            return end;
        }
    }

    /**
     * Getter for interval of a repeatable task.
     * @return 0, because non-repeatable tasks have
     *            interval set to 0 by default.
     *  return interval of repeatable task execution.
     */

    public int getRepeatInterval() {
        if (interval == 0) {
            return 0;
        } else {
            return interval;
        }
    }

    /**
     * Method to check whether task is repeatable or not.
     * @return true if task is repeatable.
     * return false if task is non-repeatable.
     */

    public boolean isRepeated() {
        return interval > 0 && start != end;
    }

    /**
     *  Method to get next execution time relative to given current time.
     * @param current is the point from which method will determine whether
     *                task was already done, ongoing or going to be executed.
     * @return -1 if task is already done.
     *          Task is considered done if current time is greater
     *          than time of non-repeatable or end of repeatable task.
     * return tempStart if task is repeatable and ongoing.
     *          Task is considered ongoing if current time is greater than
     *          start time but less than end time.
     * return start if task is repeatable and
     *          current time is less than end time.
     * return time if current time is less than task execution time.
     */

    public LocalDateTime nextTimeAfter(LocalDateTime current) {
        if (this.isActive()) {
            if (interval > 0) {
                if (current.isAfter(end)) {
                    return null;
                } else {
                    if (current.isBefore(start)) {
                        return start;
                    } else {
                        LocalDateTime nextAfter = start;

                        while (nextAfter.isBefore(current.plusSeconds(1))) {
                            nextAfter = nextAfter.plusSeconds(interval);
                        }

                        return nextAfter.isAfter(end) ? null : nextAfter;
                    }
                }
            } else {
                return time.isAfter(current) ? time : null;
            }
        } else {
            return null;
        }
    }

    /**
     * Override method equals so it compares
     * objects values, instead of addresses.
     * @param o Object thats is being compared
     * @return true if all values are equal between
     * compared objects, false if they are not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task) || this.hashCode() != o.hashCode()) {
            return false;
        }
        Task task = (Task) o;
        return time == task.time
                && start == task.start
                && end == task.end
                && interval == task.interval
                && isActive == task.isActive
                && title.equals(task.title);
    }

    /**
     * hashCode override.
     * @return hashCode
     */
    @Override
    public int hashCode() {
        int hash = (isActive ? 1 : 0);
        hash += 31 * hash + title.hashCode();
        hash += time != null ? 31 * time.hashCode() : 0;
        hash += start != null ? 31 * start.hashCode() : 0;
        hash += end != null ? 31 * end.hashCode() : 0;
        hash += 31 * interval;
        return hash;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", time=" + time +
                ", start=" + start +
                ", end=" + end +
                ", interval=" + interval/60 +
                ", isActive=" + isActive +
                '}';
    }

    @Override
    public Task clone() throws CloneNotSupportedException {
        return (Task)super.clone();
    }
}
