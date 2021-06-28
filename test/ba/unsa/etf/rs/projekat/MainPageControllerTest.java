package ba.unsa.etf.rs.projekat;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MainPageControllerTest {

    @Test
    void isJMBGOk() throws IOException {
        assertTrue(MainPageController.isJMBGOkSERVER("1212929138156"));
    }

    @Test
    void isJMBGOk2() throws IOException {
        assertTrue(MainPageController.isJMBGOkSERVER("0101942179139"));
    }

    @Test
    void isJMBGOk3() throws IOException {
        assertFalse(MainPageController.isJMBGOkSERVER("3102005462351"));
    }

    @Test
    void isJMBGOk4() throws IOException {
        assertFalse(MainPageController.isJMBGOkSERVER("3201555216235"));
    }
}