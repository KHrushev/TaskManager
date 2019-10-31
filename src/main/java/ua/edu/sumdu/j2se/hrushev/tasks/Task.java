package ua.edu.sumdu.j2se.hrushev.tasks;

/**
 * Task class.
 */

public class Task {

    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean isActive;

    /** Constructor for non-repeatable task.
     * @param uTitle sets the title of a task.
     * @param uTime time sets the time of task execution.
     */

    public Task(final String uTitle, final int uTime) {
        this.title = uTitle;
        this.time = uTime;
        isActive = false;
    }

    /** Constructor for repeatable task.
     *
     * @param uTitle sets the title of a task.
     * @param uStart sets starting time of task execution.
     * @param uEnd sets time for ending of task execution.
     * @param uInterval sets interval between task repeats.
     */

    public Task(final String uTitle, final int uStart,
                final int uEnd, final int uInterval) {
        this.title = uTitle;
        this.start = uStart;
        this.end = uEnd;
        this.interval = uInterval;
        isActive = false;
    }

    /**
     * Getter for task title.
     * @return title
     */

    public final String getTitle() {
        return title;
    }

    /**
     * Setter for task title.
     * @param uTitle sets new task title
     */
    public final void setTitle(final String uTitle) {
        this.title = uTitle;
    }

    /**
     * Getter for current status of task.
     * @return isActive field.
     */
    public final boolean isActive() {
        return isActive;
    }

    /**
     * Setter for isActive field.
     * @param active sets new activity status.
     */
    public final void setActive(final boolean active) {
        isActive = active;
    }

    /**
     *  Getter for task time.
     * @return time if task is non-repeatable.
     * return start if task is repeatable.
     */

    public final int getTime() {
        if (interval == 0) {
            return time;
        } else {
            return start;
        }
    }

    /**
     *  Setter for task time.
     *  Converts repeatable task into a non-repeatable one.
     * @param uTime sets new task time.
     */

    public final void setTime(final int uTime) {
        if (interval != 0) {
            this.interval = 0;
            this.start = uTime;
            this.end = uTime;
        }

        this.time = uTime;
    }

    /**
     *  Setter for task time.
     *  Converts a non-repeatable task into a repeatable one.
     * @param uStart sets new task start time
     * @param uEnd sets new task end time
     * @param uInterval sets new task repeat interval
     */

    public final void setTime(final int uStart,
                              final int uEnd, final int uInterval) {
        this.start = uStart;
        this.end = uEnd;
        this.interval = uInterval;
    }

    /**
     * Getter for start time of a repeatable task.
     * @return time of non-repeatable task execution.
     * return start time of repeatable task execution.
     */

    public final int getStartTime() {
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

    public final int getEndTime() {
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

    public final int getRepeatInterval() {
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

    public final boolean isRepeated() {
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

    public final int nextTimeAfter(final int current) {
        if (((current > this.time && interval == 0) || !this.isActive)
                || (current > this.end && this.interval != 0)) {
            return -1;
        } else {
            if (interval > 0) {
                if (current > start) {
                    int tempStart = start;

                    while (current > tempStart) {
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
}
