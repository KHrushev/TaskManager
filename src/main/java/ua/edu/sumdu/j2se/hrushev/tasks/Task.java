package ua.edu.sumdu.j2se.hrushev.tasks;

public class Task implements Cloneable{
    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean isActive;

    /** Constructor for non-repeatable task.
     * @param title sets the title of a task.
     * @param time time sets the time of task execution.
     */

    public Task(String title, int time) throws IllegalArgumentException {
        if (time < 0) {
            throw new IllegalArgumentException("Time cannot be of negative value.");
        }
        this.title = title;
        this.time = time;
        isActive = false;
    }

    /** Constructor for repeatable task.
     *
     * @param title sets the title of a task.
     * @param start sets starting time of task execution.
     * @param end sets time for ending of task execution.
     * @param interval sets interval between task repeats.
     */

    public Task(String title, int start,
                int end, int interval) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        isActive = false;
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

    public int getTime() {
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

    public void setTime(int time) {
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

    public void setTime(int start, int end, int interval) {
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    /**
     * Getter for start time of a repeatable task.
     * @return time of non-repeatable task execution.
     * return start time of repeatable task execution.
     */

    public int getStartTime() {
        if (interval == 0) {
            return time;
        } else {
            return start;
        }
    }

    /**
     * Getter for end time of a repeatable task.
     * @return time of non-repeatable task execution.
     *  return end time of repeatable task execution.
     */

    public int getEndTime() {
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

    public int nextTimeAfter(int current) {
        if (((current >= this.time && interval == 0) || !this.isActive)
                || (current >= this.end && this.interval != 0)) {
            return -1;
        } else {
            if (interval > 0) {
                if (current + interval > end) {
                    return -1;
                } else if (current >= start) {
                    int tempStart = start;

                    while (current >= tempStart) {
                        tempStart += interval;
                    }

                    return tempStart;
                } else {
                    return start;
                }
            } else {
                return time;
            }
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
     * @return
     */
    @Override
    public int hashCode() {
        int hash = (isActive ? 1: 0);
        hash = 31 * hash + title.hashCode();
        hash = 31 * hash + time;
        hash = 31 * hash + start;
        hash = 31 * hash + end;
        hash = 31 * hash + interval;
        return hash;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", time=" + time +
                ", start=" + start +
                ", end=" + end +
                ", interval=" + interval +
                ", isActive=" + isActive +
                '}';
    }

    @Override
    public Task clone() throws CloneNotSupportedException {
        return (Task)super.clone();
    }
}
