package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PriorityTaskTest {
    PriorityTask s1 = new PriorityTask("task1",true,true);
    PriorityTask s2 = new PriorityTask("task2",true,false);
    PriorityTask s3 = new PriorityTask("task3",false,true);
    PriorityTask s4 = new PriorityTask("task4",false,false);

    @Test
    void testChangeUrgencyWhenFalse() {
        assertFalse(s3.getUrgency());
        s3.changeUrgency();
        assertTrue(s3.getUrgency());
    }

    @Test
    void testChangeUrgencyWhenTrue() {
        assertTrue(s1.getUrgency());
        s1.changeUrgency();
        assertFalse(s1.getUrgency());
    }

    @Test
    void testChangeImportanceWhenFalse() {
        assertFalse(s2.getImportance());
        s2.changeImportance();
        assertTrue(s2.getImportance());
    }

    @Test
    void testChangeImportanceWhenTrue() {
        assertTrue(s1.getImportance());
        s1.changeImportance();
        assertFalse(s1.getImportance());
    }

    @Test
    void testDoneOrNotWhenDone() {
        s1.crossOff();
        assertEquals(" ☑",s1.doneOrNot());
    }
    @Test
    void testDoneOrNotWhenNotDone() {
        assertEquals(" O  -- Do it now!!!", s1.doneOrNot());
        assertEquals(" O  -- get someone to do it for you", s2.doneOrNot());
        assertEquals(" O  -- plan to do it soon!", s3.doneOrNot());
        assertEquals(" O  -- don't worry about it for now", s4.doneOrNot());
    }

}
