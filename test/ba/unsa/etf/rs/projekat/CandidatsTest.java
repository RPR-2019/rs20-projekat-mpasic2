package ba.unsa.etf.rs.projekat;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CandidatsTest {

    @Test
    void getId() {
        Party part = new Party(5,"aaa");
        Functions fun = new Functions(4,"kralj");
        Candidats cand = new Candidats(1,part,"1","2", LocalDate.now(),"1",fun,3);
        assertEquals(1,cand.getId());
    }

    @Test
    void setId() {
        Party part = new Party(5,"aaa");
        Functions fun = new Functions(4,"king");
        Candidats cand = new Candidats(1,part,"1","2", LocalDate.now(),"1",fun,3);
        cand.setId(2);
        assertEquals(2,cand.getId());
    }

    @Test
    void getParty_id() {
        Party part = new Party(5,"aaa");
        Functions fun = new Functions(4,"king");
        Candidats cand = new Candidats(1,part,"1","2", LocalDate.now(),"1",fun,3);
        assertEquals(5,cand.getPartyId().getId());
    }

    @Test
    void setParty_id() {
        Party part = new Party(5,"aaa");
        Party newParty = new Party(2,"newParty");
        Functions fun = new Functions(4,"king");
        Candidats cand = new Candidats(1,part,"1","2", LocalDate.now(),"1",fun,3);
        cand.setPartyId(newParty);
        assertEquals(2,cand.getPartyId().getId());
    }

    @Test
    void getName() {
        Party part = new Party(5,"aaa");
        Functions fun = new Functions(4,"king");
        Candidats cand = new Candidats(1,part,"name","2", LocalDate.now(),"1",fun,3);
        assertEquals("name",cand.getName());
    }

    @Test
    void setName() {
        Party part = new Party(5,"aaa");
        Functions fun = new Functions(4,"king");
        Candidats cand = new Candidats(1,part,"1","2", LocalDate.now(),"1",fun,3);
        cand.setName("name");
        assertEquals("name",cand.getName());
    }

    @Test
    void getLastname() {
        Party part = new Party(5,"aaa");
        Functions fun = new Functions(4,"king");
        Candidats cand = new Candidats(1,part,"1","lastName", LocalDate.now(),"1",fun,3);
        assertEquals("lastName",cand.getLastname());
    }

    @Test
    void setLastname() {
        Party part = new Party(5,"aaa");
        Functions fun = new Functions(4,"king");
        Candidats cand = new Candidats(1,part,"1","2", LocalDate.now(),"1",fun,3);
        cand.setLastname("newLastName");
        assertEquals("newLastName",cand.getLastname());
    }

    @Test
    void getBirth_date() {
        Party part = new Party(5,"aaa");
        Functions fun = new Functions(4,"king");
        Candidats cand = new Candidats(1,part,"1","2", LocalDate.now(),"1",fun,3);
        assertEquals(LocalDate.now(),cand.getBirthDate());
    }

    @Test
    void setBirth_date() {
        Party part = new Party(5,"aaa");
        Functions fun = new Functions(4,"king");
        Candidats cand = new Candidats(1,part,"1","2", LocalDate.now(),"1",fun,3);
        cand.setBirthDate(LocalDate.of(2018, 11, 17));
        assertEquals(LocalDate.of(2018, 11, 17),cand.getBirthDate());
    }

    @Test
    void getLiving_place() {
        Party part = new Party(5,"aaa");
        Functions fun = new Functions(4,"king");
        Candidats cand = new Candidats(1,part,"1","2", LocalDate.now(),"place",fun,3);
        assertEquals("place",cand.getLivingPlace());
    }

    @Test
    void setLiving_place() {
        Party part = new Party(5,"aaa");
        Functions fun = new Functions(4,"king");
        Candidats cand = new Candidats(1,part,"1","2", LocalDate.now(),"1",fun,3);
        cand.setLivingPlace("Visoko");
        assertEquals("Visoko",cand.getLivingPlace());
    }

    @Test
    void getFunctions() {
        Party part = new Party(5,"aaa");
        Functions fun = new Functions(4,"king");
        Candidats cand = new Candidats(1,part,"1","2", LocalDate.now(),"1",fun,3);
        assertEquals(4,cand.getFunctions().getId());
    }

    @Test
    void setFunctions() {
        Party part = new Party(5,"aaa");
        Functions fun = new Functions(4,"king");
        Functions newFun = new Functions(5,"president");
        Candidats cand = new Candidats(1,part,"1","2", LocalDate.now(),"1",fun,3);
        cand.setFunctions(newFun);
        assertEquals(5,cand.getFunctions().getId());
    }

    @Test
    void getVote_number() {
        Party part = new Party(5,"aaa");
        Functions fun = new Functions(4,"king");
        Candidats cand = new Candidats(1,part,"1","2", LocalDate.now(),"1",fun,3);
        assertEquals(3,cand.getVoteNumber());
    }

    @Test
    void setVote_number() {
        Party part = new Party(5,"aaa");
        Functions fun = new Functions(4,"king");
        Candidats cand = new Candidats(1,part,"1","2", LocalDate.now(),"1",fun,3);
        cand.setVoteNumber(0);
        assertEquals(0,cand.getVoteNumber());
    }
}