package model;

import model.enums.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * This class represents logic and data for a Task, containing class variables for information about the task.
 *
 * @author Pelle Berggren, pellebe@kth.se
 * @author Elias Abraham, eabraham@kth.se
 */

public class Task implements Comparable<Task>, Serializable {
    private final String description;
    private final int id;
    private String takenBy;
    private TaskState state;
    private LocalDate lastUpdate;
    private Prio prio;

    Task(String description, Prio prio, int id){
        this.description = description;
        this.prio = prio;
        this.id = id;
        this.takenBy = null;
        this.state = TaskState.TO_DO;
        this.lastUpdate = LocalDate.now();
    }
    Task(){
        this(null, Prio.Low, 0);
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getTakenBy() {
        return takenBy;
    }

    public TaskState getState() {
        return state;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public Prio getPrio() {
        return prio;
    }

    public void setTakenBy(String takenBy) throws IllegalStateException{
        if(this.takenBy != null) throw new IllegalStateException("Taken by " + this.takenBy);
        this.takenBy = takenBy;
        lastUpdate = LocalDate.now();
    }

    public void setState(TaskState state){
        this.state = state;
        lastUpdate = LocalDate.now();
    }

    public void setPrio(Prio prio){
        this.prio = prio;
        lastUpdate = LocalDate.now();
    }

    /**
     * Compares Tasks first based on enum Prio, then based on string description.
     * Description is compared reversed, so 'A' is greater than 'B' for instance.
     * Not case-sensitive.
     *
     * @param other the Task to be compared to.
     * @return an integer less than zero, zero or greater than zero,
     *          representing the relationship between the two tasks.
     */

    @Override
    public int compareTo(Task other) {
        if(prio.compareTo(other.prio) < 0) return -1;
        else if(prio.compareTo(other.prio) > 0) return 1;
        return -description.toUpperCase().compareTo(other.description.toUpperCase());
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Task){
            Task otherTask = (Task) o;
            return this.compareTo(otherTask) == 0;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Task: " +
                "description: '" + description + '\'' +
                ", id: " + id +
                ", taken by: '" + takenBy + '\'' +
                ", state: " + state +
                ", lastUpdate: " + lastUpdate +
                ", prio: " + prio;
    }
}
