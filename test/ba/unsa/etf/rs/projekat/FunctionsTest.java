package ba.unsa.etf.rs.projekat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FunctionsTest {

    @Test
    void testToString() {
        Functions f = new Functions(1, "king");
        String s = "" + f;
        assertEquals("king", s);
    }

    @Test
    void getId() {
        Functions f = new Functions(1, "king");
        assertEquals(1,f.getId());
    }

    @Test
    void setId() {
        Functions f = new Functions(1, "king");
        f.setId(3);
        assertEquals(3,f.getId());
    }

    @Test
    void getFunction_name() {
        Functions f = new Functions(1, "king");
        assertEquals("king",f.getFunction_name());
    }

    @Test
    void setFunction_name() {
        Functions f = new Functions(1, "king");
        f.setFunction_name("president");
        assertEquals("president",f.getFunction_name());
    }

}