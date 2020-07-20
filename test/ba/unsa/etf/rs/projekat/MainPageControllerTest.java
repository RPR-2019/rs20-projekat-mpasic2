package ba.unsa.etf.rs.projekat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainPageControllerTest {

    @Test
    void isJMBGOk() { assertTrue(MainPageController.isJMBGOk("1212929138156")); }

    @Test
    void isJMBGOk2() {
        assertTrue(MainPageController.isJMBGOk("0101942179139"));
    }

    @Test
    void isJMBGOk3() {
        assertFalse(MainPageController.isJMBGOk("3102005462351"));
    }

    @Test
    void isJMBGOk4() {
        assertFalse(MainPageController.isJMBGOk("3201555216235"));
    }
}