package model.matcher;

import model.enums.Prio;
import model.Task;

/**
 * This class represents a task matcher that identifies tasks by priority.
 *
 * @author Pelle Berggren, pellebe@kth.se
 * @author Elias Abraham, eabraham@kth.se
 */
public class PrioMatcher implements ITaskMatcher {
    private Prio prio;

    public PrioMatcher(Prio prio){
        this.prio = prio;
    }

    @Override
    public boolean match(Task task) {
        if(prio == task.getPrio()) return true;
        return false;
    }
}
