package model.matcher;

import model.Task;
import model.matcher.ITaskMatcher;

/**
 * This class represents a task matcher that identifies tasks by taken by.
 *
 * @author Pelle Berggren, pellebe@kth.se
 * @author Elias Abraham, eabraham@kth.se
 */
public class TakenByMatcher implements ITaskMatcher {
    private String takenBy;

    public TakenByMatcher(String takenBy){
        this.takenBy = takenBy;
    }

    @Override
    public boolean match(Task task) {
        if(takenBy.equals(task.getTakenBy())) return true;
        return false;
    }
}
