package ba.unsa.etf.rs.projekat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {

    @Test
    void getId() {
        Admin admin = new Admin(1,"email@email.com","password");
        assertEquals(1,admin.getId());
    }

    @Test
    void setId() {
        Admin admin = new Admin(1,"email@email.com","password");
        admin.setId(2);
        assertEquals(2,admin.getId());
    }

    @Test
    void getE_mail() {
        Admin admin = new Admin(1,"email@email.com","password");
        assertEquals("email@email.com",admin.getE_mail());
    }

    @Test
    void setE_mail() {
        Admin admin = new Admin(1,"email@email.com","password");
        admin.setE_mail("meho@mehic.com");
        assertEquals("meho@mehic.com",admin.getE_mail());
    }

    @Test
    void getPassword() {
        Admin admin = new Admin(1,"email@email.com","password");
        assertEquals("password", admin.getPassword());
    }

    @Test
    void setPassword() {
        Admin admin = new Admin(1,"email@email.com","password");
        admin.setPassword("newpassword");
        assertEquals("newpassword",admin.getPassword());
    }
}