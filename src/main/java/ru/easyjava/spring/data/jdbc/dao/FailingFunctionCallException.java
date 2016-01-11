package ru.easyjava.spring.data.jdbc.dao;

import org.springframework.dao.NonTransientDataAccessException;

/**
 * Custom DataAccessException which should be thrown
 * when fail_me SQL procedure is called.
 */
public class FailingFunctionCallException
        extends NonTransientDataAccessException {
    /**
     * Obviously a constructor.
     * @param msg Error message.
     */
    public FailingFunctionCallException(final String msg) {
        super(msg);
    }
}
