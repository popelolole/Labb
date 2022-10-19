package model;

import model.enums.*;
import model.matcher.ITaskMatcher;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

/**
 * This class represents logic and data for a Project, used to handle objects of type Task.
 * A Project contains information about the project, and a list of tasks.
 *
 * @author Pelle Berggren, pellebe@kth.se
 * @author Elias Abraham, eabraham@kth.se
 */

public class Project implements Comparable<Project>, Serializable{
    private final String title;
    private final int id;
    private final String description;
    private final LocalDate created;
    private int nextTaskId;
    private final ArrayList<Task> tasks;

    Project(String title, String description, int id){
        this.title = title;
        this.description = description;
        this.id = id;
        nextTaskId = 1;
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
        Task task = new Task(description, prio, nextTaskId++);
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
     * Also sorts the list.
     *
     * @param matcher the criteria to match after.
     * @return a list of tasks that match the criteria.
     */

    public List<Task> findTasks(ITaskMatcher matcher){
        List<Task> list = new ArrayList<>();
        for(Task task : tasks){
            if(matcher.match(task)){
                list.add(task);
            }
        }
        sortTasks(list);
        return list;
    }

    public void sortTasks(List<Task> list){
        Collections.sort(list, Collections.reverseOrder());
    }
    public void sortTasks(){
        sortTasks(tasks);
    }

    /**
     * Calculates and returns current state of the project.
     * The project is considered ongoing if all tasks are not done.
     *
     * @return a value of enum ProjectState, depending on if the project is empty, ongoing or completed.
     */

    public ProjectState getState(){
        if(tasks.isEmpty()) return ProjectState.EMPTY;
        for(int i = 0;i < tasks.size();i++){
            if(!tasks.get(i).equals(TaskState.DONE)) return ProjectState.ONGOING;
        }
        return ProjectState.COMPLETED;
    }

    /**
     * Gets the date at which any task in the project was last updated.
     * If the project has no tasks, the creation date of the project counts as latest update.
     *
     * @return a LocalDate value of the latest update.
     */
    public LocalDate getLastUpdated(){
        if(tasks.isEmpty()) return created;

        LocalDate latestdate = tasks.get(0).getLastUpdate();
        LocalDate date;
        for(int i = 1;i < tasks.size();i++){
            date = tasks.get(i).getLastUpdate();
            if(!latestdate.isAfter(date)) latestdate = date;
        }
        return latestdate;
    }

    public boolean removeTask(Task task) {
        for(int i=0;i<tasks.size();i++) {
            if(tasks.get(i).getId() == task.getId()) {
                tasks.remove(task);
                nextTaskId--;
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Project other) {
        return title.toUpperCase().compareTo(other.title.toUpperCase());
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Project){
            Project otherProject = (Project) o;
            return this.compareTo(otherProject) == 0;
        }
        return false;
    }

    @Override
    public String toString() {
        String info = "Project: " +
                "title: '" + title + '\'' +
                ", id: " + id +
                ", description: '" + description + '\'' +
                ", created: " + created +
                ", \nTasks: \n[";

        for(int i = 0;i < tasks.size();i++){
            info += tasks.get(i).toString();
            if(i != tasks.size() - 1) info += "\n";
        }
        return info + "]";
    }
}
