package UI;

import Project.*;
import model.*;

import java.util.List;
import java.util.Scanner;

/**
 * User interactions for a specific project, current project.
 * The user selects actions on current project in the projectLoop method.
 */
class CurrentProjectUI {
    private Project currentProject;
    private final Scanner scan;

    // package private visibility - only visible to other classes in
    // package ui - intended for MainUI.
    CurrentProjectUI(Scanner scan) {
        this.scan = scan;
        this.currentProject = null; // TODO: Ugly!
    }

    void setCurrentProject(Project project) {
        this.currentProject = project;
        projectLoop();
    }

    Project getCurrentProject() {
        return currentProject;
    }

    void projectLoop() {
        char choice;
        do {
            printCurrentProjectMenu();
            choice = InputUtils.scanAndReturnFirstChar(scan);

            switch (choice) {
                case 'T':
                    System.out.print("Name? ");
                    String takenBy = scan.nextLine();
                    viewTasks(new TakenByMatcher(takenBy));
                    break;
                case 'N':
                    viewTasks(new NotDoneMatcher());
                    break;
                case 'H':
                    Prio prio = searchPrio();
                    if(prio != null) viewTasks(new PrioMatcher(prio));
                    break;
                case 'A':
                    addTask();
                    break;
                case 'U':
                    updateTask();
                    break;
                case 'S':
                    currentProject.setTasks(currentProject.sortTasks(currentProject.getTasks()));
                    break;
                case 'R':
                    System.out.println("Enter task id: ");
                    int TaskId = scan.nextInt();
                    scan.nextLine();
                    Task theTask = currentProject.getTaskById(TaskId);
                    if(theTask != null && currentProject.removeTask(theTask)) {
                        System.out.println("Task successfully removed");
                    }
                    else System.out.println("Task doesn't exist");
                    break;
                case 'X':
                    break;
                default:
                    System.out.println("Unknown command");
            }

        } while (choice != 'X');
    }

    private void viewTasks(ITaskMatcher matcher) {
        System.out.println(currentProject.toString() + "\n\nResults:");
        List<Task> tasks = currentProject.findTasks(matcher);
        printTasks(tasks);
    }

    private void addTask() {
        System.out.print("Description? ");
        String descr = scan.nextLine();
        System.out.print("Priority (L)ow, (M)edium, (H)igh? ");
        char prioChar = InputUtils.scanAndReturnFirstChar(scan);
        Prio prio = prioChar == 'H' ? Prio.High : prioChar == 'L' ? Prio.Low : Prio.Medium;
        currentProject.addTask(descr, prio);
    }

    private void updateTask() {
        System.out.print("Task id? ");
        int id = scan.nextInt();
        scan.nextLine(); //remove "new line" from scanner buffer
        Task task = currentProject.getTaskById(id);
        if (task != null) {
            boolean close = false;
            while(!close) {
                printUpdateTask(task);
                char choice = InputUtils.scanAndReturnFirstChar(scan);
                switch (choice) {
                    case 'S':
                        System.out.print("New state (T)odo (D)one? ");
                        char stateChar = InputUtils.scanAndReturnFirstChar(scan);
                        if (stateChar == 'T') task.setState(TaskState.TO_DO);
                        else if (stateChar == 'D') task.setState(TaskState.DONE);
                        break;
                    case 'P':
                        System.out.println("New priority (L)ow (M)edium or (H)igh");
                        char prioChar = InputUtils.scanAndReturnFirstChar(scan);
                        if (prioChar == 'L') task.setPrio(Prio.Low);
                        else if (prioChar == 'M') task.setPrio(Prio.Medium);
                        else if (prioChar == 'H') task.setPrio(Prio.High);
                        break;
                    case 'T':
                        if (task.getTakenBy() != null) {System.out.println("Task already taken. "); break;}
                        System.out.println("Taken by (name or email address)? ");
                        String emailStr = scan.nextLine();
                        task.setTakenBy(emailStr);
                        break;
                    default:
                        close = true;
                }
            }
        } else {
            System.out.println("Id not found.");
        }
    }

    private void printCurrentProjectMenu() {
        System.out.println("--- Manage " + currentProject.getTitle() + " ---");
        System.out.println("T - list tasks taken by ...");
        System.out.println("N - list tasks not done");
        System.out.println("H - list tasks after priority");
        System.out.println("A - add task");
        System.out.println("U - update task");
        System.out.println("S - sort tasks (after priority)");
        System.out.println("R - remove task");
        System.out.println("X - exit project menu");
        System.out.println("----------");
    }

    private void printTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks added");
        } else {
            for (Task task : tasks) {
                System.out.println(task.toString());
            }
        }
    }

    private void printUpdateTask(Task task){
        System.out.println(task);
        System.out.println("--- Update task " + task.getId() + " ---");
        System.out.println("S - Change state");
        System.out.println("P - Change priority");
        System.out.println("T - Change taken by");
        System.out.println("X - Exit task menu");
        System.out.println("----------");
    }

    private Prio searchPrio(){
        System.out.println("Priority (L)ow, (M)edium or (H)igh?");
        char choice = InputUtils.scanAndReturnFirstChar(scan);
        switch(choice){
            case 'L':
                return Prio.Low;
            case 'M':
                return Prio.Medium;
            case 'H':
                return Prio.High;
            default:
                System.out.println("Invalid Character");
                return null;
        }
    }
}
