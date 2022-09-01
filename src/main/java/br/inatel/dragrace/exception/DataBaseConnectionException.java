package br.inatel.dragrace.exception;

import org.hibernate.exception.JDBCConnectionException;

public class DataBaseConnectionException extends RuntimeException {
    public DataBaseConnectionException(JDBCConnectionException jdbcConnectionException) {
        super("DataBase connection failed: " + jdbcConnectionException.getMessage());
    }
}
