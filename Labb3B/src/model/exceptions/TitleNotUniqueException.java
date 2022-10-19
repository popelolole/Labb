package model.exceptions;

/**
 * This class represents an exception for titles that are not unique.
 *
 * @author Pelle Berggren, pellebe@kth.se
 * @author Elias Abraham, eabraham@kth.se
 */
public class TitleNotUniqueException extends RuntimeException{
    public TitleNotUniqueException(String message){
        super(message);
    }

    public TitleNotUniqueException(){
        super();
    }
}
