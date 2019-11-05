package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ToDoListTest {


    @BeforeEach
    public void runBefore() {
    }


    @Test
    public void testFindAndCross(){
        Task s1 = new RegularTask("do laundry");
        Task s2 = new RegularTask("buy milk");
        Task s3 = new RegularTask("study spanish");
        ToDoList todoTest = new ToDoList();
        todoTest.insert(s1);
        todoTest.insert(s2);
        todoTest.insert(s3);
        assertFalse(s2.getStatus());
        todoTest.findAndCross(2);
        assertTrue(s2.getStatus());
        todoTest.findAndCross(2);
        assertTrue(s2.getStatus());
    }

    @Test
    public void testLoad() throws IOException {
        Task s1 = new RegularTask("laundry");
        Task s2 = new PriorityTask("study", true , false);
        ToDoList todoTest = new ToDoList();
        todoTest.insert(s1);
        todoTest.insert(s2);
        todoTest.findAndCross(1);
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
    public void testSave() throws IOException {
        Task s1 = new RegularTask("laundry");
        s1.crossOff();
        Task s2 = new PriorityTask("study", true , false);
        Task s3 = new PriorityTask("sleep", false, true);
        Task s4 = new PriorityTask("shopping", false,false);
        s4.crossOff();
        ToDoList todoTest = new ToDoList();
        todoTest.insert(s1);
        todoTest.insert(s2);
        todoTest.insert(s3);
        todoTest.insert(s4);
        todoTest.save("testSave");
        String expected = "R true laundry\nP false false true study\nP false true false sleep\n" +
                "P true false false shopping\n";
        List<String> lines = Files.readAllLines(Paths.get("./data/" + "testSave"));
        String actual ="";
        for (String s : lines){
            actual = actual + s + "\n";
        }
        assertEquals(expected,actual);

    }


    @Test
    public void testSize() {
        Task s1 = new RegularTask("do laundry");
        Task s2 = new RegularTask("buy milk");
        Task s3 = new RegularTask("study spanish");
        ToDoList todoTest = new ToDoList();
        assertEquals(0, todoTest.size());
            todoTest.insert(s1);
        assertEquals(1, todoTest.size());
        todoTest.insert(s2);
        todoTest.insert(s3);
        assertEquals(3, todoTest.size());
    }

    @Test
    void testPrintAll(){
        RegularTask s1 = new RegularTask("do laundry");
        PriorityTask p1 = new PriorityTask("code", true,true);
        ToDoList todoTest = new ToDoList();
        todoTest.insert(s1);
        todoTest.insert(p1);
        String expectedPrintAll = s1.getName() + s1.doneOrNot() + "\n"
                + p1.getName() + p1.doneOrNot() + "\n";
        assertEquals(expectedPrintAll,todoTest.printAll());

    }
}




