package model.matcher;

import model.Task;

/**
 * @author Pelle Berggren, pellebe@kth.se
 * @author Elias Abraham, eabraham@kth.se
 */
public interface ITaskMatcher {
    abstract public boolean match(Task task);
}
