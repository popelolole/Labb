import IO.*;
import UI.*;
import model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class ProjectApp {

    private static final String FILE_NAME = "projects.ser";

    public void run() throws Exception { // we do not catch all exceptions
        File projectsFile = new File(FILE_NAME);
        ProjectsManager projectsManager = new ProjectsManager();
        boolean couldReadFile = false;

        try {

            if (projectsFile.exists()) {
                List<Project> projects = ProjectsFileIO.deSerializeFromFile(projectsFile);
                projectsManager.setProjects(projects);
                couldReadFile = true;
            }

            MainUI ui = new MainUI(projectsManager);
            ui.mainLoop();

        } catch (FileNotFoundException | ClassNotFoundException e) {
            System.out.println("Could not load projects from file, please check the data file.");
            System.out.println("Continuing with empty manager.");
        } catch(IOException io){
            throw new IOException(io);
        }

        // run method about to exit - save data
        if(couldReadFile || !projectsFile.exists()) {
            List<Project> projectsToSave = projectsManager.getProjects();
            ProjectsFileIO.serializeToFile(projectsFile, projectsToSave);
        }
        System.out.println("Application exits");
    }

    public static void main(String[] args) throws Exception {

        ProjectApp app = new ProjectApp();
        app.run();
    }
}
