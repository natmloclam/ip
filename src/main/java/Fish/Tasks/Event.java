package Fish.Tasks;

public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String getType() {
        return "E";
    }

    public String toString(){
        return "[E]" +  super.toString() + " (from: " + this.from + ", to: " + this.to + ")";
    }
}