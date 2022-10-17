package Project;

import model.Prio;
import model.ProjectState;
import model.TaskState;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Project implements Comparable<Project>, Serializable{
    private String title;
    private int id;
    private String description;
    private LocalDate created;
    private int nextTaskId;
    private ArrayList<Task> tasks;

    Project(String title, String description, int id){
        this.title = title;
        this.description = description;
        this.id = id;
        nextTaskId = 0;
        tasks = new ArrayList<>();
        created = LocalDate.now();
    }

    public Task getTaskById(int id){
        for(Task t : tasks){
            if(t.getId() == id) return t;
        }
        return null;
    }

    public Task addTask(String description, Prio prio){
        Task task = new Task(description, prio, ++nextTaskId);
        //task.setTakenBy("hej");
        tasks.add(task);
        return task;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getCreated() {
        return created;
    }

    public int getNextTaskId() {
        return nextTaskId;
    }

    public ArrayList<Task> getTasks() {
        ArrayList<Task> copy = (ArrayList<Task>) tasks.clone();
        return copy;
    }

    /**
     * Creates a list of tasks that matches a certain criteria.
     *
     * @param matcher the criteria to match after.
     * @return a list of tasks that match the criteria.
     */

    public List<Task> findTasks(ITaskMatcher matcher){
        List<Task> list = new ArrayList<>();
        for(int i = 0;i < tasks.size();i++){
            Task task = tasks.get(i);
            if(matcher.match(task)){
                list.add(task);
            }
        }

        Task tmp;
        for(int i = 0;i < list.size();i++){
            for(int j = 1;j < list.size() - 1;j++){
                if(list.get(j - 1).getId() > list.get(j).getId()) {
                    tmp = list.get(j - 1);
                    list.set(j-1, list.get(j));
                    list.set(j, tmp);
                }
            }
        }
        return list;
    }

    public ProjectState getState(){
        if(tasks.isEmpty()) return ProjectState.EMPTY;
        for(int i = 0;i < tasks.size();i++){
            if(!tasks.get(i).equals(TaskState.DONE)) return ProjectState.ONGOING;
        }
        return ProjectState.COMPLETED;
    }

    public LocalDate getLastUpdated(){
        if(tasks.isEmpty()) return created;

        LocalDate latestdate = tasks.get(0).getLastUpdate();
        for(int i = 1;i < tasks.size();i++){
            LocalDate date = tasks.get(i).getLastUpdate();
            if(!latestdate.isAfter(date)) latestdate = date;
        }
        return latestdate;
    }

    @Override
    public int compareTo(Project other) {
        return title.compareTo(other.title);
    }

    @Override
    public String toString() {
        String info = "Project:" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", description='" + description + '\'' +
                ", created=" + created +
                ", nextTaskId=" + nextTaskId +
                ", tasks: \n[";

        for(int i = 0;i < tasks.size();i++){
            info += tasks.get(i).toString() + "\n";
        }
        return info + "]";
    }
}
