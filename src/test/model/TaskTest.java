package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Task;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {

 //   @BeforeEach
    Task s = new Task("do laundry");

    @Test
    void testCrossOffWhenFalse() {
        assertFalse(s.getStatus());
        s.crossOff();
        assertTrue(s.getStatus());
    }

    @Test
    void testCrossOffWhenTrueAlready() {
        assertFalse(s.getStatus());
        s.crossOff();
        assertTrue(s.getStatus());
        s.crossOff();
        assertTrue(s.getStatus());

    }

@Test
    void testDoneOrNotWhenNotDone(){
        assertEquals("not done yet!",s.doneOrNot());
    }
    @Test
    void testDoneOrNOtWhenDone(){
        s.crossOff();
        assertEquals("done!",s.doneOrNot());
    }

}
