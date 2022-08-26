package main.java;

public class Event extends Task {

    protected String dueTime;

    public Event(String description, String dueTime) {
        super(description);
        this.dueTime = dueTime;
    }

    public String getDueTime(){
        return this.dueTime;
    }

    @Override
    public String toString() {
        String output = String.format("[E][%s] %s (at: %s)", this.getStatusIcon(), this.description, this.dueTime);
        return output;
    }
}

