package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;

public class ToDoListTest {


    @BeforeEach
    public void runBefore() {
    }

    @Test
    public void testLoad() throws IOException {
        Task s1 = new Task("do laundry");
        Task s2 = new Task("buy milk");
        Task s3 = new Task("study spanish");
        ToDoList todoTest = new ToDoList();
        todoTest.insert(s1);
        todoTest.insert(s2);
        todoTest.insert(s3);
        todoTest.findAndCross(2);
        ToDoList testLoadTodoList = new ToDoList();
        testLoadTodoList.load("testLoad1");
        assertEquals(todoTest.size(),testLoadTodoList.size());
        int size = todoTest.size();
        for (int i = 0 ; i < size ; i++){
           Task expectTask = todoTest.getTask(i);
           Task testTask = testLoadTodoList.getTask(i);
            assertEquals(expectTask.getName(),testTask.getName());
            assertEquals(expectTask.getStatus(),testTask.getStatus());
        }
    }

    @Test
    public void testSave() {

    }
}




