package Project;

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
