package Excepciones;

import java.sql.SQLException;

public class DBException extends SQLException {

    public DBException(){
    }
    public DBException(String s) {
        super(s);
    }

    public DBException(String s, Throwable throwable) {
        super(s, throwable);
    }

}
