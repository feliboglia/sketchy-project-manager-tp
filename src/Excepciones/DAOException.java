package Excepciones;

public class DAOException extends Exception{
    public DAOException() {}
    public DAOException(String reason) {
        super(reason);
    }
    public DAOException(String reason, Throwable throwable) {
        super(reason, throwable);
    }
}