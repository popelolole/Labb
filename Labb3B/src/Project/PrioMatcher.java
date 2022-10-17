package Project;

import model.Prio;

public class PrioMatcher implements ITaskMatcher{
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
