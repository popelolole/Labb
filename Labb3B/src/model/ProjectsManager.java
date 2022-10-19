package model;

import model.exceptions.TitleNotUniqueException;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents logic and data for a Project Manager, used to handle Projects.
 * A Project Manager contains a list of projects and an integer used to hand out Ids to projects.
 *
 * @author Pelle Berggren, pellebe@kth.se
 * @author Elias Abraham, eabraham@kth.se
 */
public class ProjectsManager {
    private int nextProjectId;
    private ArrayList<Project> projects;

    public ProjectsManager(){
        this.nextProjectId = 1;
        projects = new ArrayList<>();
    }

    public ArrayList<Project> getProjects(){
        ArrayList copy = (ArrayList<Project>) projects.clone();
        return copy;
    }

    public void setProjects(List<Project> incomingProjects) {
        projects.clear();
        projects.addAll(incomingProjects);
        nextProjectId = projects.size() + 1;
    }

    public boolean isTitleUnique(String title){
        for(int i = 0;i < projects.size();i++){
            if(projects.get(i).getTitle().equals(title)) return false;
        }
        return true;
    }

    public Project addProject(String title, String description) throws TitleNotUniqueException {
        if(!isTitleUnique(title)) throw new TitleNotUniqueException("Title already exists.");
        Project p = new Project(title, description, nextProjectId++);
        projects.add(p);
        return p;
    }

    public void removeProject(Project project){
        projects.remove(project);
        nextProjectId--;
    }

    public Project getProjectById(int id){
        for(Project p : projects){
            if(p.getId() == id) return p;
        }
        return null;
    }

    public List<Project> findProjects(String titleStr){
        List<Project> list = new ArrayList<>();
        for(Project p : projects){
            if(p.getTitle().equals(titleStr)) list.add(p);
        }
        return list;
    }

    private int getHighestId(){
        return nextProjectId - 1;
    }

    @Override
    public String toString() {
        String info = "";
        for(int i = 0;i < projects.size();i++){
            info += projects.get(i).toString() + "\n";
        }
        return info;
    }
}
