package ba.unsa.etf.rs.projekat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VotersTest {

    @Test
    void getId(){
        Voters voter = new Voters(1,"4KOM542","0201000170025");
        assertEquals(1,voter.getId());
    }

    @Test
    void getCardNumber(){
        Voters voter = new Voters(1,"4KOM542","0201000170025");
        assertEquals("4KOM542",voter.getCardNumber());
    }

    @Test
    void setId(){
        Voters voter = new Voters(1,"4KOM542","0201000170025");
        voter.setId(8);
        assertEquals(8,voter.getId());
    }

    @Test
    void setCardNumber(){
        Voters voter = new Voters(1,"4KOM542","0201000170025");
        voter.setCardNumber("555");
        assertEquals("555",voter.getCardNumber());
    }

    @Test
    void setJMBG(){
        Voters voter = new Voters(1,"4KOM542","0201000170025");
        voter.setJmbg("1231231231231");
        assertEquals("1231231231231",voter.getJmbg());
    }
}
