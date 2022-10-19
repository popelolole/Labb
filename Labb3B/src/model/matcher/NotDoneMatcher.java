package model.matcher;

import model.Task;
import model.enums.TaskState;

/**
 * This class represents a task matcher that identifies tasks that are not done
 *
 * @author Pelle Berggren, pellebe@kth.se
 * @author Elias Abraham, eabraham@kth.se
 */
public class NotDoneMatcher implements ITaskMatcher {

    @Override
    public boolean match(Task task) {
        if(task.getState() != TaskState.DONE) return true;
        return false;
    }
}
