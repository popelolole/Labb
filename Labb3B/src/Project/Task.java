package Project;

import model.Prio;
import model.TaskState;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Comparable<Task>, Serializable {
    private String description;
    private int id;
    private String takenBy;
    private TaskState state;
    private LocalDate lastUpdate;
    private Prio prio;

    Task(String description, Prio prio, int id){
        this.description = description;
        this.prio = prio;
        this.id = id;
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
        if(takenBy != null) throw new IllegalStateException("Taken by " + takenBy);
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
     * Compares Tasks first based on prio, then based on description.
     *
     * @param other the Task to be compared.
     * @return -1 if other prio or description is greater than this Task's.
     * @return 0 if prio and description is equal for both objects.
     * @return 1 if other prio or description is less than this Task's.
     */

    @Override
    public int compareTo(Task other) {
        if(prio.compareTo(other.prio) < 0) return -1;
        else if(prio.compareTo(other.prio) > 0) return 1;
        return description.compareTo(other.description);
    }
}
