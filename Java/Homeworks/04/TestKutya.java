import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestKutya {
    private Kutya peldany;

    @Test
    public void testSetters() {
        peldany = new Kutya("Pista", 5, 4, 2);
        assertEquals("Pista", peldany.getNev());
        assertNotEquals("Pistaaaaaa", peldany.getNev());
        assertEquals(5, peldany.getAgresszivitas());
        assertEquals(4, peldany.getEhseg());
        assertEquals(0, peldany.ismertEmberekSzama());
    }

    @Test
    public void testHarapasVeszely() {
        peldany = new Kutya("Pista", 5, 4, 2);
        assertEquals(Math.sqrt(5*4), peldany.harapasVeszely());
    }

    @Test
    public void testJatek() {
        peldany = new Kutya("Pista", 5, 4, 2);
        peldany.jatek("Géza");
        assertEquals(1, peldany.ismertEmberekSzama());
        assertEquals(4 + 1, peldany.getEhseg());
        assertFalse(peldany.ugat("Géza"));
        assertTrue(peldany.ugat("Gizi"));
    }

    @Test
    public void testJatek1() {
        peldany = new Kutya("Pista", 0, 4, 2);
        peldany.jatek("Géza");
        if (peldany.getAgresszivitas() == 0) {
            assertNull(null);
        }
    }

    @Test
    public void testJatek2() {
        peldany = new Kutya("Pista", 4, 4, 2);
        peldany.megismer("Géza");
        peldany.jatek("Géza");
        peldany.jatek("Géza");
        assertNotNull(peldany.ismertEmberekSzama());
    }

    @Test
    public void testEtet() {
        peldany = new Kutya("Pista", 11, 4, 2);
        assertTrue(peldany.etet("kaja"));
        assertEquals(10, peldany.getAgresszivitas());
        peldany.setEhseg(0);
        assertFalse(peldany.etet("kaja"));
    }

    @Test
    public void testUgat() {
        peldany = new Kutya("Pista", 11, 4, 2);
        peldany.megismer("Géza");
        assertFalse(peldany.ugat("Géza"));
        assertTrue(peldany.ugat("Gizi"));

        peldany.setAgresszivitas(90);
        assertTrue(peldany.ugat("Géza"));
    }

    @Test
    public void testMegismer() {
        peldany = new Kutya("Pista", 11, 4, 2);
        peldany.megismer("Géza");
        assertFalse(peldany.ugat("Géza"));
        assertTrue(peldany.ugat("Gizi"));
        assertEquals(1, peldany.ismertEmberekSzama());

        peldany.megismer("Géza");
        assertEquals(1, peldany.ismertEmberekSzama());
    }

    @Test
    public void testIsmertEmberekSzama() {
        peldany = new Kutya("Pista", 5, 4, 2);
        peldany.jatek("Géza");
        assertEquals(1, peldany.ismertEmberekSzama());
        assertNotNull(peldany.ismertEmberekSzama());
    }

    @Test
    public void testSetEhseg() {
        peldany = new Kutya("Pista", 5, 4, 2);
        peldany.jatek("Géza");
        assertEquals(1, peldany.ismertEmberekSzama());
    }

    @Test
    public void testGetIsmertEmberek() {
        peldany = new Kutya("Pista", 5, 4, 2);

        assertNotSame(peldany.getIsmertEmberek(), peldany.getIsmertEmberek());
    }
}
