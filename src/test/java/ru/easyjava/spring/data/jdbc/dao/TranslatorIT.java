package ru.easyjava.spring.data.jdbc.dao;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;

@ContextConfiguration("/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TranslatorIT {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Inject
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testCustomException() {
        exception.expect(FailingFunctionCallException.class);
        exception.expectMessage(is("Called function that intended to fail"));

        CustomExceptionTranslator cet = new CustomExceptionTranslator();
        jdbcTemplate.setExceptionTranslator(cet);

        jdbcTemplate.queryForObject("select fail_me()", Integer.class);
    }

    @Test
    public void testDefaultException() {
        exception.expect(BadSqlGrammarException.class);
        exception.expectMessage(containsString("bad SQL grammar"));

        CustomExceptionTranslator cet = new CustomExceptionTranslator();
        jdbcTemplate.setExceptionTranslator(cet);

        jdbcTemplate.queryForObject("select * from nonexistent", Integer.class);
    }
}
