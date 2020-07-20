package ba.unsa.etf.rs.projekat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartyTest {

    @Test
    void testToString() {
        Party p = new Party(1, "kingsParty");
        String s = "" + p;
        assertEquals("kingsParty", s);
    }

    @Test
    void getId() {
        Party p = new Party(1, "kingsParty");
        assertEquals(1,p.getId());
    }

    @Test
    void setId() {
        Party p = new Party(1, "kingsParty");
        p.setId(8);
        assertEquals(8,p.getId());
    }

    @Test
    void getName_party() {
        Party p = new Party(1, "kingsParty");
        assertEquals("kingsParty",p.getName_party());
    }

    @Test
    void setName_party() {
        Party p = new Party(1, "kingsParty");
        p.setName_party("newParty");
        assertEquals("newParty",p.getName_party());
    }

}