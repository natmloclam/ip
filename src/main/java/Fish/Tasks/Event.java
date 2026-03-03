package Fish.Tasks;

/**
 * Represents an event - a task that has additional start and end time attributes
 */
public class Event extends Task{
    protected String from;
    protected String to;

    /**
     * Constructor of event
     *
     * @param description user input description of task
     * @param from user input start time of event
     * @param to user input end time of event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns start time
     *
     * @return start time of event
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns end time
     *
     * @return end time of event
     */
    public String getTo() {
        return to;
    }

    /**
     * Returns type of event "E"
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * toString method used for printing events
     *
     * @return String that shows the type of task (E), whether it is done (X), the task description and the start and end times
     */
    public String toString(){
        return "[E]" +  super.toString() + " (from: " + this.from + ", to: " + this.to + ")";
    }

    /**
     * Returns format which events are stored in the file as
     *
     * @return Information of event in the form of "E | [isDone] | [description] | [start] | [end]"
     */
    @Override
    public String toFileFormat() {
        return getType() + " | " + (isDone ? 1 : 0) + " | " + getDescription()
                + " | " + getFrom() + " | " + getTo();
    }
}