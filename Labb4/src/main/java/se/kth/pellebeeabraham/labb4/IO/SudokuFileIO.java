package se.kth.pellebeeabraham.labb4.IO;

import se.kth.pellebeeabraham.labb4.model.MatrixHandler;

import java.io.*;

public class SudokuFileIO {

    public static void serializeToFile(File file, MatrixHandler data) throws IOException{
        FileOutputStream fout = null;

        try{
            fout = new FileOutputStream(file);
            ObjectOutputStream ous = new ObjectOutputStream(fout);

            ous.writeObject(data);
        }
        finally{
            if(fout!= null) fout.close();
        }
    }

    public static MatrixHandler deSerializeFromFile(File file) throws IOException, ClassNotFoundException{
        FileInputStream fin = null;
        MatrixHandler matrixHandler;

        try{
            fin = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fin);

            matrixHandler = (MatrixHandler) ois.readObject();
        }
        finally{
            if(fin != null) fin.close();
        }

        return matrixHandler;
    }
}
