package Project;

import Project.Task;
import model.Prio;

public class Main{
    public static void main(String[] args){
        Task a = new Task("bana", Prio.Medium, 21);
        Task b = new Task("banan", Prio.Medium, 22);

        //a.findTasks(new PrioMatcher(Prio.Low));

        System.out.println(a.compareTo(b));
    }
}