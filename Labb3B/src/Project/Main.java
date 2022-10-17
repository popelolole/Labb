package Project;

import Project.Task;
import model.Prio;
import model.TaskState;

public class Main{
    public static void main(String[] args){
        Project a = new Project("bana", "hej", 21);
        Task b = new Task("banan", Prio.Medium, 22);
        a.addTask("hejhej", Prio.Low);
        a.addTask("blabla", Prio. Medium);
        a.addTask("dada", Prio.Low);

        a.getTasks().get(0).setTakenBy("hej");
        a.getTasks().get(0).setState(TaskState.DONE);
        a.getTasks().get(1).setState(TaskState.IN_PROGRESS);
        a.getTasks().get(2).setState(TaskState.TO_DO);

        System.out.println(a.toString());

        System.out.println(a.findTasks(new PrioMatcher(Prio.Low)).toString());
        System.out.println(a.findTasks(new TakenByMatcher("hej")).toString());
        System.out.println(a.findTasks(new NotDoneMatcher()).toString());

    }
}