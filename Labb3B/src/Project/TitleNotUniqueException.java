package Project;

public class TitleNotUniqueException extends RuntimeException{
    public TitleNotUniqueException(String message){
        super(message);
    }

    public TitleNotUniqueException(){
        super();
    }
}
