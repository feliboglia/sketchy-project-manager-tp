package Excepciones;

public class ServiceException extends Exception{
    public ServiceException() {
    }
    public ServiceException(String reason) {
        super(reason);
    }

    public ServiceException(String reason, DAOException e) {
        super(e.getMessage());
    }

    public ServiceException(String reason, String message) {
    }
}