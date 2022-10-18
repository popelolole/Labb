package IO;

import Project.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Hints on how to implement serialization and deserialization
 * of lists of projects and users.
 */
public class ProjectsFileIO {

    /**
     * Call this method before the application exits, to store the users and projects,
     * in serialized form.
     */
    public static void serializeToFile(File file, List<Project> data) throws IOException {
        FileOutputStream fout = null;

        try{
            fout = new FileOutputStream(file);
            ObjectOutputStream ous = new ObjectOutputStream(fout);

            ous.writeObject(data);
        }
        finally{
            if (fout != null) fout.close();
        }
    }

    /**
     * Call this method at startup of the application, to deserialize the users and
     * from file the specified file.
     */
    @SuppressWarnings("unchecked")
    public static List<Project> deSerializeFromFile(File file) throws IOException, ClassNotFoundException {
        FileInputStream fin = null;
        List<Project> projects;

        try{
            fin = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fin);

            projects = (List<Project>) ois.readObject();
        }
        finally{
            if (fin != null) fin.close();
        }

        return projects;
    }

    private ProjectsFileIO() {}
}
