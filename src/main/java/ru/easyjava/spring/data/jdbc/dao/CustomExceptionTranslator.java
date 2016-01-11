package ru.easyjava.spring.data.jdbc.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

import java.sql.SQLException;

/**
 * Example of custom ExceptionTranslator.
 */
public class CustomExceptionTranslator
        extends SQLErrorCodeSQLExceptionTranslator {
    @Override
    protected final DataAccessException customTranslate(
            final String task,
            final String sql,
            final SQLException ex) {
        if (ex.getSQLState().equals("SJ001")) {
            return new FailingFunctionCallException(
                    "Called function that intended to fail");
        }

        return null;
    }
}
