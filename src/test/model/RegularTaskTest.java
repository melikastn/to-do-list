package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegularTaskTest {
    Task s = new RegularTask("do laundry");

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
    void testDoneOrNotWhenNotDone() {
        assertEquals( " O", s.doneOrNot());
    }

    @Test
    void testDoneOrNOtWhenDone() {
        s.crossOff();
        assertEquals(" ☑", s.doneOrNot());
    }

    @Test
    void testGetName() {
        assertEquals("do laundry",s.getName());
    }
}

