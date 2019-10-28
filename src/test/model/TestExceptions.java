package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestExceptions {

    private ToDoList todoTest;
    private Task s1;

    @BeforeEach
    void run(){
         s1 = new RegularTask("study");
         todoTest = new ToDoList();
    }
    @Test
    void testNotTooManyTasksNotExpectException(){
        try {

            for (int i = 0; i < ToDoList.TOO_MANY_THINGS - 1; i++) {
                todoTest.insert(s1);
                todoTest.notTooManyTasks();
            }
        }
        catch (TooManyThingsToDoException e) {
            fail("Should not have thrown Exception");
        }
    }

    @Test
    void testNotTooManyTasksExpectException(){
        try {

            for (int i = 0; i < ToDoList.TOO_MANY_THINGS - 1; i++) {
                todoTest.insert(s1);
                todoTest.notTooManyTasks();
            }
        }
        catch (TooManyThingsToDoException e) {
            fail("Should not have thrown Exception");
        }
        try {
            todoTest.insert(s1);
            todoTest.notTooManyTasks();
            fail("Should have thrown exception");
        }
        catch (TooManyThingsToDoException e) {}

    }
}
