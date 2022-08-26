package main.java;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription(){
        return this.description;
    }

    public boolean getIsDone(){
        return this.isDone;
    }

    public void mark(){
        this.isDone = true;
    }
    public void unmark(){
        this.isDone = false;
    }

    public String toString() {
        String output = String.format("[%s] %s", this.getStatusIcon(), this.description);
        return output;
    }
}