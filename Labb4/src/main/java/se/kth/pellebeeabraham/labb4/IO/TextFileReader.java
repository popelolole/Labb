package se.kth.pellebeeabraham.labb4.IO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TextFileReader {
    String file;

    public TextFileReader(String file){
        this.file = file;
    }

    public String readTextFromFile(){
        BufferedReader fin = null;
        String text = "";
        try{
            fin = new BufferedReader(new FileReader(file));

            String line = fin.readLine();
            while(line != null){
                line = fin.readLine();
                text += line;
            }
        }
        catch(FileNotFoundException f){}
        catch(IOException ie){}
        finally{
            try{
                if(fin != null) fin.close();
            }
            catch(IOException ie){}
            finally{}
        }

        return text;
    }
}
