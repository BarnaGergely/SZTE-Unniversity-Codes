import Egyseg.Egyseg;
import Egyseg.Kepesseg.GyogyitasKepesseg;
import Egyseg.Kepesseg.LovesKepesseg;
import Egyseg.Kepesseg.VegtelenVisszatamadasKepesseg;
import Public.Csateter;
import Public.Hos;
import Public.Koordinata;
import Varazslat.KedvetlenitesVarazslat;
import Varazslat.RandomVarazslat;
import org.junit.jupiter.api.*;

public class testPortal {
    @Test
    void testEgysegKiirat() {
        LovesKepesseg lovesKepesseg = new LovesKepesseg("Lövés");
        Egyseg ijaszEgyseg = new Egyseg("Íjász", 6, 2, 4, 7, 4, 9, null);
        Portal.egysegKiirat(ijaszEgyseg);
    }

    @Test
    void testPortalKiirat() {
        float[] egysegek = new float[5];
        egysegek[2] = 3;
        egysegek[1] = 4;
        Csateter csatater = new Csateter();
        LovesKepesseg lovesKepesseg = new LovesKepesseg("Lövés");
        Egyseg ijaszEgyseg = new Egyseg("Íjász", 6, 2, 4, 7, 4, 9, null);
        Egyseg foldmuvesEgyseg = new Egyseg("Földműves", 2, 1, 4, 3, 4, 9, null);
        Koordinata koordinata = new Koordinata(2, 2);
        csatater.setMatrix(koordinata, ijaszEgyseg, 2, false);
        koordinata = new Koordinata(3, 4);
        csatater.setMatrix(koordinata, foldmuvesEgyseg, 10, true);
        Portal.csataterKiirat(csatater);
    }

    @Test
    void testPortalVarazslatKiirat() {


        Hos hos = new Hos("ember");
        hos.setTamadas(7);
        hos.setVedekezes(10);

        RandomVarazslat randomVarazslat = new RandomVarazslat();
        hos.addVarazslatok("Random", randomVarazslat);
        KedvetlenitesVarazslat kedvetlenitesVarazslat = new KedvetlenitesVarazslat();
        hos.addVarazslatok("Kedvetlenítés", kedvetlenitesVarazslat);

        Portal.varazslatKiirat(hos);

    }

}
